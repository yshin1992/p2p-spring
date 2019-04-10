package org.background.support;

import org.springframework.context.annotation.Description;
import org.util.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ForPdfUtilTool {
    private static ForPdfUtilTool forPdfUtilTool = null;

    private ForPdfUtilTool(){}

    public static ForPdfUtilTool getInstance(){
        if(forPdfUtilTool == null){
            forPdfUtilTool = new ForPdfUtilTool();
        }
        return forPdfUtilTool;
    }
    /**
     * 日期格式换
     * @param obj
     * @param srcPattern  obj类型为String时必填,其他情况不填
     * @param targetPattern
     * @return
     * @throws Exception
     */
    @Description("日期格式化 args0类型为String时必填,其他情况不填")
    public String dateFormat(Object obj,String srcPattern,String targetPattern) throws Exception{
        if(StringUtil.isEmpty(obj)){
            return "";
        }
        SimpleDateFormat targetSdf = new SimpleDateFormat(targetPattern);
        if(obj instanceof Date || obj instanceof Long){
            return targetSdf.format(obj);
        }
        if(obj instanceof String){
            SimpleDateFormat srcSdf = new SimpleDateFormat(srcPattern);
            return targetSdf.format(srcSdf.parse(obj.toString()));
        }
        return "";
    }
    /**
     * 数字格式化
     * @param obj
     * @param pattern
     * @return
     */
    @Description("数字格式化 args1:如 #,##0.00")
    public String numberFormat(Object obj,String pattern){
        if(StringUtil.isEmpty(obj)){
            return "";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        if(obj instanceof String){
            return df.format(new BigDecimal(obj.toString()));
        }
        return df.format(obj);
    }
    /**
     * 金额转大写
     * @param obj
     * @return
     */
    @Description("金额转大写")
    public String amtBigType(Object obj){
        if(StringUtil.isEmpty(obj)){
            return "";
        }
        if(obj instanceof BigDecimal){
            return MoneyUtil.toCNString((BigDecimal)obj);
        }
        return MoneyUtil.toCNString(new BigDecimal(obj.toString()));
    }
    /**
     * 字符串加密显示
     * @param str
     * @param headCnt 头部明文个数
     * @param endCnt  尾部明文个数
     * @return
     */
    @Description("字符串加密显示 args1:头部明文个数  args2:尾部明文个数")
    public String encryption(String str,int headCnt,int endCnt){
        if(StringUtil.isEmpty(str)){
            return "";
        }
        if(str.length() == 1){
            return "*";
        }
        StringBuffer resultBuf = new StringBuffer();
        if(str.length() <= headCnt + endCnt){
            for (int i = 0; i < str.length(); i++) {
                resultBuf.append((i==0?str.charAt(i)+"":"*"));
            }
        }else{
            for (int i = 0; i < str.length(); i++) {
                resultBuf.append((i<headCnt||str.length()-1-i<endCnt?str.charAt(i)+"":"*"));
            }
        }
        return resultBuf.toString();
    }


    public String switchCase(Object key,Object... values){
        for (int i = 0; i < values.length; i = i + 2) {
            if(key.equals(values[i])){
                return values[i+1] == null?"":values[i+1].toString();
            }
        }
        return "";
    }


    public String ifElse(String defaultVal,Object... values){
        for (int i = 0; i < values.length; i = i + 2) {
            Boolean flag = (Boolean)values[i];
            if(flag){
                return values[i+1] == null?"":values[i+1].toString();
            }
        }
        return defaultVal;
    }

}
