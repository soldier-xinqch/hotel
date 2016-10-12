package org.hotel.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportPdfUtil<T> {
	
	private static final float CELLHIGHT = 30;
	private Logger logger = LogManager.getLogger(ExportPdfUtil.class);
	 /**
     * sheetName:导出的pdf文件名
     * headers:表格列头
     * columns:列
     * lists:数据源（数据集合）
     */
    public void exportPdf(String sheetName,String[] headers, String[] columns, List<T> lists, HttpServletRequest request,String title,String destPath,
            HttpServletResponse response,String realPath) throws Exception{
        logger.info("zsl==========开始导出pdf");
        Document document = new Document(PageSize.A2,50,50,50,50);
//      PdfWriter pw = PdfWriter.getInstance(document,new FileOutputStream("D:\\Test.pdf"));
        String fileName = sheetName + ".pdf";
//        String destPath = UploadFileUtil.getFilePath(request)+ SystemConfig.readValue("pdfPath") + File.separator;
        logger.info("zsl==========pdfPath:" + destPath);
        mkDirs(destPath);
        String filePath = destPath + fileName;
        logger.info("zsl==========filePath:" + filePath);
        //必须紧跟在document创建的时候创建PdfWriter,否则导出无数据
        PdfWriter pw = PdfWriter.getInstance(document,new FileOutputStream(filePath));

        document.open();
        //中文字体显示，直接用下载的字体jar包也可以
//      BaseFont baseFont = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",false);
        //绝对路径
        String fontPath ="D:\\SIMYOU.TTF";
        BaseFont baseFont = BaseFont.createFont(fontPath,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        Font font = new Font(baseFont,10,Font.NORMAL);

        Paragraph p = new Paragraph(title,font);
        document.add(p);

        PdfPTable table = new PdfPTable(headers.length);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        //创建表头
        for (int i = 0; i < headers.length; i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(headers[i]));
            table.addCell(cell);
        }



        for (int i = 0; i < lists.size(); i++) {
            String value = "";
            if(null == lists.get(i)){
                value = "";
            }else{
                value = lists.get(i).toString();
            }
            table.addCell(new Paragraph(value));
        }


        document.add(table);
        document.close();

        pw.flush();
        pw.close();
        downloadPdf(filePath, response);
        logger.info("zsl==========导出pdf成功");
    }
    
    /**
     * sheetName:导出的pdf文件名
     * headers:表格列头
     * columns:列
     * lists:数据源（数据集合）
     */
    public void exportPdf(String[] headers, String[] columns, List<T> lists,String title,
    		ServletOutputStream outputStream) throws Exception{
        logger.info("zsl==========开始导出pdf");
        Document document = new Document(PageSize.A4);
        //必须紧跟在document创建的时候创建PdfWriter,否则导出无数据
        PdfWriter pw = PdfWriter.getInstance(document,outputStream);
        document.open();
        //中文字体显示，直接用下载的字体jar包也可以
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
        Font fontChinese = new Font(bfChinese, 14, Font.NORMAL);  

        Paragraph pagetitle = new Paragraph(title,fontChinese);
        // 设置页面格式  
        pagetitle.setSpacingBefore(8);  
        pagetitle.setSpacingAfter(2);  
        pagetitle.setAlignment(1);  
        // 设置PDF标题  
        pagetitle.add(new Chunk("付款申请书",new Font(bfChinese, 16,Font.BOLD)));  
        document.add(pagetitle);  
        // 抬头  
        Paragraph head = new Paragraph("123132",fontChinese);
        head.setSpacingBefore(30);  
        document.add(head); 
        //表格数据  
        PdfPTable table = new PdfPTable(headers.length);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        //创建表头
        for (int i = 0; i < headers.length; i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(headers[i]));
            table.addCell(cell);
        }
        //
        Class<? extends Object> clazz = null;
        if (lists.size() > 0)
            clazz = lists.get(0).getClass();
 
        String content = null;
        for (T t : lists) {
            for (int i = 0; i < columns.length; i++) {
                Method method = clazz.getMethod(columns[i], null);
                Object object = method.invoke(t, null);
                object = object == null ? "" : object;
                if (object.getClass().equals(Date.class)) {// 对日期格式进行特殊处理
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    content = sdf.format((Date) object);
                    table.addCell(new Paragraph(content));
                } else {
                    content = object.toString();
                    table.addCell(new Paragraph(content));
                }
            }
        }
        document.add(table);
        document.close();

        pw.flush();
        pw.close();
        logger.info("zsl==========导出pdf成功");
    }

    public static void mkDirs(String filepath){
        File fileDir = new File(filepath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
    }
    
//    public void exportPDF(String[] headers, String[] columns, List<T> lists,String title,ServletOutputStream outputStream){
//    	Document document = new Document(PageSize.A4);
//    	 //必须紧跟在document创建的时候创建PdfWriter,否则导出无数据
//        try {
//			PdfWriter pw = PdfWriter.getInstance(document,outputStream);
//			//文档属性  
//			document.addTitle("Title@sample");  
//			document.addAuthor("Author@rensanning");  
//			document.addSubject("Subject@iText sample");  
//			document.addKeywords("Keywords@iText");  
//			document.addCreator("Creator@iText"); 
//			
//			document.open();
//			//插入标题
//			String fontPath ="D:\\SIMYOU.TTF";
//	        BaseFont baseFont = BaseFont.createFont(fontPath,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
//	        Font font = new Font(baseFont,10,Font.NORMAL);
//	        Paragraph pagetitle = new Paragraph(title,font);  
//	        pagetitle.setAlignment(Element.ALIGN_CENTER);  
//            document.add(pagetitle);  
//            Paragraph title1 = new Paragraph("\n");  
//            document.add(title1);  
//            
//            //创建表格
//            PdfPTable table = new PdfPTable(headers.length);  
//			
////			int headerwidths[] = { 9, 4, 8, 10}; // percentage  
////			table.setWidths(headerwidths);  
//			table.setWidthPercentage(100);  
//			table.getDefaultCell().setPadding(3);  
//			table.getDefaultCell().setBorderWidth(1);  
//			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);  
//			
//			for (String headname : headers) {
//				table.addCell(headname);  
//			}
//			  
//			table.setHeaderRows(1);  
//			//边框  
//			table.getDefaultCell().setBorderWidth(1);
//            //添加表格内容
////			int NumColumns = 4; 
////			String[] bogusData = { "M0065920", "SL", "FR86000P", "PCGOLD"};  
////			
////			for (int i = 1; i < 10; i++) {  
////			    for (int x = 0; x < NumColumns; x++) {  
////			    	table.addCell(bogusData[x]);  
////			    }  
////			}  
//			
//			 Class<? extends Object> clazz = null;
//		        if (lists.size() > 0)
//		            clazz = lists.get(0).getClass();
//		 
//		        String content = null;
//		        for (T t : lists) {
//		            for (int i = 0; i < columns.length; i++) {
//		                Method method = clazz.getMethod(columns[i], null);
//		                Object object = method.invoke(t, null);
//		                object = object == null ? "" : object;
//		                if (object.getClass().equals(Date.class)) {// 对日期格式进行特殊处理
//		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		                    content = sdf.format((Date) object);
//		                    table.addCell(new Paragraph(content));
//		                } else {
//		                    content = object.toString();
//		                    table.addCell(new Paragraph(content));
//		                }
//		            }
//		        }
//            
//           //将表格对象添加到小节对象中  
//           document.add(table);  
//           document.close();  
//           pw.flush();
//           pw.close();
//           logger.info("zsl==========导出pdf成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
    	ExportPdfUtil pdf = new ExportPdfUtil();
//		try {
////			pdf.openDocument();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
    public void exportPDF(String[] headers, String[] columns, List<T> lists,String title,ServletOutputStream outputStream) throws FileNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	//页面大小  
		Rectangle rect = new Rectangle(PageSize.A4.rotate());  
		//页面背景色  
//    			rect.setBackgroundColor(BaseColor.ORANGE);  
		Document document = createDocument(rect);
		try {
			PdfWriter writer = PdfWriter.getInstance(document,outputStream);
			//PDF版本(默认1.4)  
//    				writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2); 
			//文档属性  
			document.addTitle("Title@sample");  
			document.addAuthor("Author@rensanning");  
			document.addSubject("Subject@iText sample");  
			document.addKeywords("Keywords@iText");  
			document.addCreator("Creator@iText"); 
			//页边空白  
			document.setMargins(10, 20, 30, 40);  
			document.open();  
			
			BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体  
		    Font font = new Font(bfChinese,10,Font.NORMAL);
			Paragraph pagetitle = new Paragraph("XX标题",font);  
	        pagetitle.setAlignment(Element.ALIGN_CENTER);
	        document.newPage();  
            document.add(pagetitle);  
            Paragraph title1 = new Paragraph("\n");  
            document.add(title1);  
            
            
			  
//    				document.newPage();  
//    				document.add(new Paragraph("New page"));  
            PdfPTable table = new PdfPTable(headers.length);  
			
//			int headerwidths[] = { 9, 4, 8, 10}; // percentage  
//			table.setWidths(headerwidths);  
			table.setWidthPercentage(100);  
			table.getDefaultCell().setPadding(3);  
			table.getDefaultCell().setBorderWidth(1);  
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);  
			
			for (String headname : headers) {
				table.addCell(new Paragraph(headname,font));  
			}
			  
			table.setHeaderRows(1);  
			//边框  
			table.getDefaultCell().setBorderWidth(1);  
			Class<? extends Object> clazz = null;
	        if (lists.size() > 0)
	            clazz = lists.get(0).getClass();
	 
	        String content = null;
	        for (T t : lists) {
	            for (int i = 0; i < columns.length; i++) {
	                Method method = clazz.getMethod(columns[i], null);
	                Object object = method.invoke(t, null);
	                object = object == null ? "" : object;
	                if (object.getClass().equals(Date.class)) {// 对日期格式进行特殊处理
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                    content = sdf.format((Date) object);
	                    table.addCell(new Paragraph(content,font));
	                } else {
	                    content = object.toString();
	                    table.addCell(new Paragraph(content,font));
	                }
	            }
	        }
			document.add(table);
			
            document.close(); 
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private Document createDocument(Rectangle pageType){
		Document document = new Document(pageType);
		return document;
	}
	
	public void openDocument(ServletOutputStream outputStream) throws IOException{
		//页面大小  
		Rectangle rect = new Rectangle(PageSize.A4.rotate());  
		//页面背景色  
//		rect.setBackgroundColor(BaseColor.ORANGE);  
		Document document = createDocument(rect);
		try {
			PdfWriter writer = PdfWriter.getInstance(document,outputStream);
			//PDF版本(默认1.4)  
//			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2); 
			//文档属性  
			document.addTitle("Title@sample");  
			document.addAuthor("Author@rensanning");  
			document.addSubject("Subject@iText sample");  
			document.addKeywords("Keywords@iText");  
			document.addCreator("Creator@iText"); 
			//页边空白  
			document.setMargins(10, 20, 30, 40);  
			document.open();  
			
			BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体  
		    Font font = new Font(bfChinese,10,Font.NORMAL);
			Paragraph pagetitle = new Paragraph("XX标题",font);  
	        pagetitle.setAlignment(Element.ALIGN_CENTER);
	        document.newPage();  
            document.add(pagetitle);  
            Paragraph title1 = new Paragraph("\n");  
            document.add(title1);  
            
            
			  
//			document.newPage();  
//			document.add(new Paragraph("New page"));  
			setTables(document);
			document.close(); 
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setTables(Document document){
		try {
			PdfPTable table = new PdfPTable(4);  
			
			int headerwidths[] = { 9, 4, 8, 10}; // percentage  
			table.setWidths(headerwidths);  
			table.setWidthPercentage(100);  
			table.getDefaultCell().setPadding(3);  
			table.getDefaultCell().setBorderWidth(1);  
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);  
			  
			table.addCell("Clock #");  
			table.addCell("Cusip");  
			table.addCell("Trans Type");  
			table.addCell("Trans Type1");  
			  
			table.setHeaderRows(1);  
			//边框  
			table.getDefaultCell().setBorderWidth(1);  
			int NumColumns = 4; 
			String[] bogusData = { "M0065920", "SL", "FR86000P", "PCGOLD"};  
			
			for (int i = 1; i < 10; i++) {  
			    for (int x = 0; x < NumColumns; x++) {  
			    	table.addCell(bogusData[x]);  
			    }  
			}  
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
	}
    
    
    
    //下载
    public static void downloadPdf(String filepath, HttpServletResponse response)
            throws IOException {
        File file = new File(filepath);
        String fileName = file.getName();
        response.setContentType("application/pdf;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
        response.setCharacterEncoding("utf-8");
        InputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] b = new byte[fis.available()];
        fis.read(b);
        response.getOutputStream().write(b);
        response.getOutputStream().flush();
        response.getOutputStream().close();
        response.flushBuffer();
        fis.close();
    }

}
