package org.background.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.StringUtil;

/**
 * 根据office模板生成pdf工具类(基于ZDSPdfUtil的升级版)
 * 升级说明:
 * 1,模板中变量的写法几乎完全支持Java代码
 * 2,提供处理date格式化,金额格式化,字符串加密的工具方法(参见ForPdfUtilTool)
 *
 *
 * 模板文件说明:
 *
 * 1,map的value放入的是  单个对象时    模板写法如:   1,{mapKey.getMethodName(0).getMethodName()}
 * 										  2,{mapKey.getStatus() == 0?'成功':'失败'}
 * 2,map的value放入的是  List<Object>时, 模板写法如：1,[mapKey.get(0).getMethodName(0).getMethodName()]
 * 											  2,[mapKey.get(0).index]  记录当前行序号
 * 3,模板的段落或表格中如非必要,请不要填写如  {非键值}  [非键值] 等带有大括号或中括号字样,避免异常情况无法解析。
 *
 * 4,编辑模板文件时,如果敲击Enter键导致     “{”及“}” 或者  “[”及“]”不在同一行将会导致无法解析键值(word文档自动挤到下一行不影响)
 */
public class PDFUtil {

    private static final Logger logger = LoggerFactory.getLogger(PDFUtil.class);

    private static final PDFUtil instance = new PDFUtil();

    private PDFUtil(){

    }

    public static PDFUtil getInstance(){
        return instance;
    }

    /**
     * 根据操作系统的名称，获取OpenOffice.org 4的安装目录<br>
     * @return OpenOffice.org 4的安装目录    */
    //这里需要你自己改，你自己的openoffice.org4 的安装目录
    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        logger.error("操作系统名称:" + osName);
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice.org4";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "C:/Program Files (x86)/OpenOffice 4";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Applications/OpenOffice.app/Contents/";
        }
        return null;
    }

    /**
     * 【入口方法】
     * 根据模板office文件生成PDF文件
     * 工作流程:  根据模板office文件   -> 临时office文件 -> pdf文件 -> 删除临时office文件
     * @param templateOfficePath  office模板文件绝对路径(含文件名)
     * @param pdfPath             输出pdf文件绝对路径(含文件名)
     * @param param               value值支持类型：1,单个对象   如  productDto 《模板中写法见 类的头部说明》
     * 										    2,List<Object>  如 List<productDto>	 《模板中写法见 类的头部说明》
     * 											3,普通基础数据类型  如int,String,long,Integer等等
     */
    public void templateOfficeToPdf(final String templateOfficePath,final String pdfPath,Map<String, Object> param) {
        final Map<String, Object> newParam = new HashMap<String, Object>(param);
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (instance){
                    String tmpOfficePath = templateOfficePath.substring(0, templateOfficePath.lastIndexOf("."))+"_temp"
                            + templateOfficePath.substring(templateOfficePath.lastIndexOf("."));
                    String tmp1OfficePath = tmpOfficePath.substring(0, tmpOfficePath.lastIndexOf("."))+"_1"
                            + tmpOfficePath.substring(tmpOfficePath.lastIndexOf("."));

                    try{
                        logger.error("============开始生成合同 模板文件:{}",templateOfficePath);
                        logger.error("模板office文件   => 临时office(1)文件 =>  临时office文件");

                        templateOfficeToTmpOffice(templateOfficePath,tmp1OfficePath, tmpOfficePath,newParam);

                        tmpOfficeToPdf(tmpOfficePath, pdfPath);

                        logger.debug("删除 临时office(1)文件");
                        deleteTmpFile(tmp1OfficePath);
                        logger.debug("删除 临时office文件");
                        deleteTmpFile(tmpOfficePath);

                        logger.error("============生产合同结束=============================={}",pdfPath);
                    }catch (Exception e){
                        logger.error("生成合同异常",e);
                    }
                }
            }
        }).start();

    }

    /**
     * 临时office文件 -> pdf文件
     * @param tmpOfficePath
     * @param pdfPath
     */
    private void tmpOfficeToPdf(String tmpOfficePath,String pdfPath) throws Exception{
        File pdfFile = new File(pdfPath);
        File tmpOfficeFile = new File(tmpOfficePath);
        if(!pdfFile.getParentFile().exists()){
            pdfFile.getParentFile().mkdirs();
        }
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        config.setOfficeHome(getOfficeHome());
        config.setPortNumber(8100);

        OfficeManager officeManager = config.buildOfficeManager();

        logger.error("开始启动OpenOffice服务....");
        officeManager.start();
        logger.error("OpenOffice服务启动成功....");

        OfficeDocumentConverter officeConvertor = new OfficeDocumentConverter(officeManager);
        try{
            logger.error("开始转换....");
            officeConvertor.convert(tmpOfficeFile,pdfFile);
            logger.error("转换完成....");
        }catch (Exception e){
            logger.error("合同转换异常!{}",e);
        }finally {
            if(officeManager != null){
                officeManager.stop();
            }
        }
    }

    /**
     * 删除临时文件
     * @param filePath
     */
    private void deleteTmpFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }


    private JexlContext globalParam = null;

    /**
     * 根据模板office文件   -> 临时office文件
     * @param templateOfficePath
     * @param tmp1OfficePath
     * @param tmpOfficePath
     * @param param
     */
    private void templateOfficeToTmpOffice(String templateOfficePath,String tmp1OfficePath,String tmpOfficePath,
                                           Map<String, Object> param) throws Exception{
        globalParam = new MapContext();
        globalParam.set("pdfTool", ForPdfUtilTool.getInstance());
        for(String key:param.keySet()){
            globalParam.set(key, param.get(key));
        }
        Map<String, String> replaceParamMap = new HashMap<String, String>();
        templateOfficeToTmp1Office(templateOfficePath,tmp1OfficePath,param,replaceParamMap);
        tmp1OfficeToTmpOffice(tmp1OfficePath,tmpOfficePath,replaceParamMap);

        logger.error("模板键值替换详细情况:{}",replaceParamMap.toString());

    }

    /**
     * 根据模板office文件   -> 临时office(1)文件
     * @param templateOfficePath
     * @param tmp1OfficePath
     * @param param
     * @param replaceParamMap
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void templateOfficeToTmp1Office(String templateOfficePath,String tmp1OfficePath,Map<String, Object> param,
                                            Map<String, String> replaceParamMap)
            throws Exception{
        OPCPackage opcPackage = POIXMLDocument.openPackage(templateOfficePath);
        XWPFDocument doc = new XWPFDocument(opcPackage);
        insertValueToParagraphs(doc.getParagraphs(),replaceParamMap,false,null);

        List<XWPFTable> tables = doc.getTables();

        for (int i = 0; i < tables.size(); i++) {
            XWPFTable table = tables.get(i);

            List<XWPFTableRow> rows = table.getRows();
            for (int j = 0; j < rows.size(); j++) {
                XWPFTableRow row = rows.get(j);
                List<XWPFTableCell> cells = row.getTableCells();

                for (int k = 0; k < cells.size(); k++) {
                    XWPFTableCell cell = cells.get(k);
                    List<String> needReplaceList = new ArrayList<String>();
                    matcheNeedReplaceList(cell.getText().trim(),needReplaceList,true);

                    if(needReplaceList.size() > 0){
                        String listKey = getListKeyString(needReplaceList.get(0));
                        if(listKey == null || param.get(listKey) == null
                                || ((List<Object>)param.get(listKey)).size() == 0){
                            table.removeRow(j);
                            j = j - 1;
                        }else{
                            int needAddNewrowsSize = ((List<Object>)param.get(listKey)).size() - 1;
                            for (int l = 0; l < needAddNewrowsSize; l++) {
                                table.addRow(row, j+1);
                            }
                            j = j + needAddNewrowsSize;
                        }
                        break;
                    }
                }

            }
        }

        OutputStream os = new FileOutputStream(tmp1OfficePath);
        doc.write(os);
        os.close();

    }


    /**
     *  临时office(1)文件 -> 临时office文件
     * @param tmp1OfficePath
     * @param tmpOfficePath
     * @param replaceParamMap
     * @throws Exception
     */
    private void tmp1OfficeToTmpOffice(String tmp1OfficePath,String tmpOfficePath,
                                       Map<String, String> replaceParamMap) throws Exception{
        OPCPackage opcPackage = POIXMLDocument.openPackage(tmp1OfficePath);
        XWPFDocument doc = new XWPFDocument(opcPackage);
        List<XWPFTable> tables = doc.getTables();
        for (int i = 0; i < tables.size(); i++) {
            XWPFTable table = tables.get(i);

            List<XWPFTableRow> rows = table.getRows();
            for (int j = 0; j < rows.size(); j++) {
                XWPFTableRow row = rows.get(j);
                List<XWPFTableCell> cells = row.getTableCells();

                for (int k = 0; k < cells.size(); k++) {
                    XWPFTableCell cell = cells.get(k);
                    List<String> needReplaceList = new ArrayList<String>();
                    matcheNeedReplaceList(cell.getText().trim(),needReplaceList,true);

                    if(needReplaceList.size() > 0){
                        if(j+1 < rows.size() && rows.get(j+1).getTableCells().size() == cells.size()){
                            insertValueToParagraphs(rows.get(j+1).getCell(k).getParagraphs(),
                                    replaceParamMap, true,"["+getAfterAddListString(needReplaceList.get(0))+"]");
                        }
                        insertValueToParagraphs(cell.getParagraphs(), replaceParamMap, true,null);
                    }
                    if(matcheNeedReplaceList(cell.getText().trim(),new ArrayList<String>(),false)){
                        insertValueToParagraphs(cell.getParagraphs(), replaceParamMap, false,null);
                    }
                }

            }
        }

        OutputStream os = new FileOutputStream(tmpOfficePath);
        doc.write(os);
        os.close();
        opcPackage.close();
    }


    /**
     * 动态   替换 XWPFParagraph 集合类型值
     * @param paragraphs
     * @param replaceParamMap
     * @param isList
     * @param replaceValue
     * @throws Exception
     */
    private void insertValueToParagraphs(List<XWPFParagraph> paragraphs,
                                         Map<String, String> replaceParamMap,boolean isList,String replaceValue) throws Exception{
        for (int i = 0; i < paragraphs.size(); i++) {
            XWPFParagraph paragraph = paragraphs.get(i);
            List<XWPFRun> runs = paragraph.getRuns();

            for (int j = 0; j < runs.size(); j++) {
                XWPFRun run = runs.get(j);
                String tmpRunStr = run.toString().trim();
                List<String> needReplaceList = new ArrayList<String>();
                String prefixBrackets = isList?"[":"{";
                String suffixBrackets = isList?"]":"}";
                matcheNeedReplaceList(tmpRunStr,needReplaceList,isList);

                boolean isDoReplace = false;
                for (String replaceString : needReplaceList) {
                    isDoReplace = true;
                    tmpRunStr = tmpRunStr.replace(prefixBrackets+replaceString+suffixBrackets, setReplaceParamMap(replaceString,replaceParamMap,replaceValue,isList));
                }

                if(tmpRunStr.lastIndexOf(prefixBrackets) >= 0 && tmpRunStr.lastIndexOf(prefixBrackets) > tmpRunStr.lastIndexOf(suffixBrackets)){
                    isDoReplace = true;
                    StringBuffer nextStringBuf = new StringBuffer();
                    for (int k = j+1; k < runs.size();) {
                        XWPFRun nextRun = runs.get(k);
                        String nextTmpRunStr = nextRun.toString();
                        if(nextTmpRunStr.indexOf(suffixBrackets) == -1){
                            nextStringBuf.append(nextTmpRunStr);
                            paragraph.removeRun(k);
                        }else{
                            nextStringBuf.append(nextTmpRunStr.substring(0, nextTmpRunStr.indexOf(suffixBrackets)));
                            nextRun.setText(nextTmpRunStr.substring(nextTmpRunStr.indexOf(suffixBrackets)+1), 0);
                            break;
                        }
                    }
                    String replaceString = tmpRunStr.substring(tmpRunStr.lastIndexOf(prefixBrackets)+1) + nextStringBuf.toString();
                    tmpRunStr = tmpRunStr.substring(0, tmpRunStr.lastIndexOf(prefixBrackets));
                    tmpRunStr = tmpRunStr + setReplaceParamMap(replaceString,replaceParamMap,replaceValue,isList);
                }
                if(isDoReplace){
                    run.setText(tmpRunStr, 0);
                }
            }

        }
    }

    /**
     * 匹配需要替换的list集合
     * @param str
     * @param list
     */
    private boolean matcheNeedReplaceList(String str,List<String> list,boolean isList){
        String regex = isList?".*?\\[(.+?)\\]":".*?\\{(.+?)\\}";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matchers = pattern.matcher(str);
        while (matchers.find()) {
            String tmpStr = matchers.group(1);
            if(list.contains(tmpStr)){
                continue;
            }
            list.add(tmpStr);
        }
        return list.size() > 0;
    }

    /**
     * 替换param -> replaceParamMap
     * @param replaceString
     * @param replaceParamMap
     * @param isList 是否是list集合
     * @param replaceValue 固定替换值
     */
    private String setReplaceParamMap(String replaceString,Map<String, String> replaceParamMap,String replaceValue,boolean isList)
            throws Exception{
        if(StringUtils.isNotEmpty(replaceValue)){
            return replaceValue;
        }
        if(replaceParamMap.containsKey(replaceString)){
            return replaceParamMap.get(replaceString);
        }
        if(isList){
            String listIndex = getListIndex(replaceString);
            if(StringUtil.isNotEmpty(listIndex)){
                return listIndex;
            }
        }
        String resultString = invokeMethod(characterControl(replaceString));
        replaceParamMap.put(replaceString, resultString);
        return resultString;
    }


    /**
     * 调用表达式
     * @param jexlExp
     * @return
     */
    private String invokeMethod(String jexlExp){
        Object obj;
        try {
            JexlEngine jexl = new JexlEngine();
            Expression e = jexl.createExpression(jexlExp);
            obj = e.evaluate(globalParam);
            if(null == obj){
                return "";
            }
        } catch (Exception e) {
            logger.error("执行表达式异常:{}",jexlExp);
            return "";
        }
        return obj.toString();
    }

    /**
     * 获取当前list的index
     * @param str
     * @return
     */
    private String getListIndex(String str){
        String resultStr = null;
        String regex = ".+?\\.get\\(([0-9]+)\\)\\.index";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matchers = pattern.matcher(str);
        while (matchers.find()) {
            resultStr = matchers.group(1);
            break;
        }
        return resultStr;
    }


    /**
     * 特殊字符处理
     * @param str
     * @return
     */
    private String characterControl(String str){
        if(str.indexOf("”") >= 0){
            str = str.replace("”", "'");
        }
        if(str.indexOf("“") >= 0){
            str = str.replace("“", "'");
        }
        if(str.indexOf("’") >= 0){
            str = str.replace("’", "'");
        }
        if(str.indexOf("‘") >= 0){
            str = str.replace("‘", "'");
        }
        return str;
    }
    /**
     * 获取list的key值
     * @param str
     * @return
     */
    private String getListKeyString(String str){
        String resultStr = null;
        String regex = "([0-9a-zA-Z]+)\\.get\\([0-9]+\\)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matchers = pattern.matcher(str);
        while (matchers.find()) {
            resultStr = matchers.group(1);
            break;
        }
        return resultStr;
    }

    /**
     * 获取累加后的list字符串
     * @param str
     * @return str
     */
    private String getAfterAddListString(String str){
        String regex = ".+?\\.get\\(([0-9]+)\\)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matchers = pattern.matcher(str);
        while (matchers.find()) {
            str = str.replace(".get("+matchers.group(1)+")", ".get("+(Integer.valueOf( matchers.group(1)) + 1)+")");
            break;
        }
        return str;
    }

}
