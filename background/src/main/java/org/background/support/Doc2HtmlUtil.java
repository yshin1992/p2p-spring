package org.background.support;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;
import java.util.regex.Pattern;

public class Doc2HtmlUtil {

    /**
     * 根据操作系统的名称，获取OpenOffice.org 4的安装目录<br>
     * @return OpenOffice.org 4的安装目录    */
    //这里需要你自己改，你自己的openoffice.org4 的安装目录
    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        System.out.println("操作系统名称:" + osName);
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice.org4";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "C:/Program Files (x86)/OpenOffice 4";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Applications/OpenOffice.app/Contents/";
        }
        return null;
    }

    // 将word格式的文件转换为pdf格式
    public static void Word2Pdf(String srcPath, String desPath) {
        File tmpOfficeFile = new File(srcPath);
        File pdfFile = new File(desPath);

        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        config.setOfficeHome(getOfficeHome());
        config.setPortNumber(8100);

        OfficeManager officeManager = config.buildOfficeManager();

        System.out.println("开始启动OpenOffice服务....");
        officeManager.start();
        System.out.println("OpenOffice服务启动成功....");

        OfficeDocumentConverter officeConvertor = new OfficeDocumentConverter(officeManager);
        try{
            System.out.println("开始转换....");
            officeConvertor.convert(tmpOfficeFile,pdfFile);
            System.out.println("转换完成....");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(officeManager != null){
                officeManager.stop();
            }
        }
    }


    public static void main(String[] args) {
        String src="/Users/yanshuai/Documents/first.doc";
        String dest = "/Users/yanshuai/Documents/first.pdf";
        Word2Pdf(src,dest);
    }
}
