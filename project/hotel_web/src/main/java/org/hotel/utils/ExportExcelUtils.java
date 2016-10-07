package org.hotel.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 公知技术  
 * 导出excel 工具类
 * @author xinqch
 *
 */
public class ExportExcelUtils {
	
	
	/**
     * 根据ExcelEntity等参数生成Workbook
     * @param entity
     * @return
     * @throws Exception
     */
    public static <T> Workbook export2Excel(ExcelEntity<T> entity) throws Exception{
        Workbook workbook = export2Excel(entity.getHeader(), entity.getFooter(),entity.getSheetName(), entity.getColumnNames(), entity.getMethodNames(),
                entity.getEntities());
        return workbook;
    }
 
 
    /**
     * 根据给定参数导出Excel文档
     * 
     * @param headerTitle
     *            题头
     * @param footer 脚注
     * @param sheetName
     * @param columnNames
     *            表头名称
     * @param methodNames
     * @param entities
     * @return
     * @throws Exception
     */
    public static <T> Workbook export2Excel(String headerTitle, String footerTitle, String sheetName, String[] columnNames,
            String[] methodNames, List<T> entities) throws Exception {
        if (methodNames.length != columnNames.length)
            throw new IllegalArgumentException("methodNames.length should be equal to columnNames.length:"
                    + columnNames.length + " " + methodNames.length);
        Workbook newWorkBook2007 = new XSSFWorkbook();
        Sheet sheet = newWorkBook2007.createSheet(sheetName);
         
        //设置题头
        Header header = sheet.getHeader();
        header.setCenter(headerTitle);
        //设置脚注
        Footer footer = sheet.getFooter();
        footer.setCenter(footerTitle);
         
        int[] columnWidths = new int[columnNames.length];
        // 创建表头
        createTableHeader(sheet, 0, headerTitle, columnNames, columnWidths);
        // 填充表内容
        createTableContent(sheet, 1, methodNames, columnWidths, entities);
 
        return newWorkBook2007;
 
    }
 
    /**
     * 创建表头
     * 
     * @param sheet
     * @param index
     *            表头开始的行数
     * @param headerTitle
     *            题头
     * @param columnNames
     * @param columnWidths
     */
    private static void createTableHeader(Sheet sheet, int index, String headerTitle, String[] columnNames,
            int[] columnWidths) {
         
 
        Row headerRow = sheet.createRow(index);
 
        /* 格式设置 */
        // 设置字体
        Font font = sheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
        // 设置背景色
        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(font);
 
        for (int i = 0; i < columnNames.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(style);
            headerCell.setCellValue(columnNames[i]);
        }
 
        for (int i = 0; i < columnNames.length; i++) {
            columnWidths[i] = (columnNames[i].getBytes().length + 2) * 256;
            sheet.setColumnWidth(i, columnWidths[i]);
        }
 
    }
 
    /**
     * 创建表格内容
     * 
     * @param sheet
     * @param rowIndexBegin
     *            表内容开始的行数
     * @param methodNames
     *            T对象的方法名
     * @param columnWidths
     * @param entities
     * @throws Exception
     */
    private static <T> void createTableContent(Sheet sheet, int rowIndexBegin, String[] methodNames, int[] columnWidths,
            List<T> entities) throws Exception {
        Class<? extends Object> clazz = null;
        if (entities.size() > 0)
            clazz = entities.get(0).getClass();
 
        String content = null;
        for (T t : entities) {
            Row row = sheet.createRow(rowIndexBegin++);
            for (int i = 0; i < methodNames.length; i++) {
                Cell cell = row.createCell(i);
                Method method = clazz.getMethod(methodNames[i], null);
                Object object = method.invoke(t, null);
                object = object == null ? "" : object;
                if (object.getClass().equals(Date.class)) {// 对日期格式进行特殊处理
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    content = sdf.format((Date) object);
                    cell.setCellValue(content);
                } else {
                    content = object.toString();
                    cell.setCellValue(content);
                }
                int columnWidth = (content.getBytes().length + 2) * 256;
                if (columnWidth > columnWidths[i]) {// 如果实际内容宽度大于对应的表头宽度，则设置为实际内容宽度
                    columnWidths[i] = columnWidth;
                    sheet.setColumnWidth(i, columnWidths[i]);
                }
 
            }
        }
    }
 
    public static <T> void testPOI(String[] columnNames, String[] methodNames, List<T> entities) throws Exception {
        String sheetName = "Test";
        String title = "标题栏";
        String dstFile = "d:/temp/test.xlsx";
        Workbook newWorkBook2007 = new XSSFWorkbook();
        Sheet sheet = newWorkBook2007.createSheet(sheetName);
        int[] columnWidths = new int[columnNames.length];
        // 创建表头
        createTableHeader(sheet, 0, title, columnNames, columnWidths);
        // 填充表内容
        createTableContent(sheet, 1, methodNames, columnWidths, entities);
        // 保存为文件
        saveWorkBook2007(newWorkBook2007, dstFile);
        System.out.println("end");
 
    }
 
    /**
     * @param workbook2007
     * @param dstFile
     */
    public static void saveWorkBook2007(Workbook workbook2007, String dstFile) {
        File file = new File(dstFile);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            workbook2007.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
    /**
     * @param workbook2007
     * @param dstFile
     */
    public static void saveWorkBook2007(Workbook workbook2007, String dstFile,OutputStream os) {
        try {
            workbook2007.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
     
 
    /**
     * 测试方法
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//        // 准备数据
//        List<Wind> winds = new ArrayList<>();// Wind有三个方法:getLocation、getSpeed、getTimestamp
//        for (int i = 0; i < 10; i++) {
//            Wind wind = new Wind();
//            wind.setLocation(i);
//            wind.setSpeed(i * 10);
//            wind.setTimestamp("2016/3/2" + i);
//            winds.add(wind);
//        }
//        String[] columnNames = { "地点", "速度", "时间" };
//        String[] methodNames = { "getLocation", "getSpeed", "getTimestamp" };
////      String fileName = "d:/temp/excel1.xlsx";
//        String fileName = "d:/excel1.xlsx";
//        // 生成ExcelEntity实体，包含4个必备参数
//        ExcelEntity<Wind> excelEntity = new ExcelEntity<>(fileName, columnNames, methodNames, winds);
//        //excelEntity.setHeader("题头");
//        //excelEntity.setFooter("脚注");
//        Workbook excel = ExcelExporter.export2Excel(excelEntity);
//        //ExcelExporter.export2Excel("题头","脚注", "sheet1", columnNames, methodNames, winds);//也可以这样调用,无需新建ExcelEntity对象
//        //将Workbook存为文件
//        ExcelExporter.saveWorkBook2007(excel, excelEntity.getFileName());
//         
//        System.out.println("导出完成！");
// 
//    }
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	
//	/*************************************************************************** 
//	  * @param fileName EXCEL文件名称 
//	  * @param listTitle EXCEL文件第一行列标题集合 
//	  * @param listContent EXCEL文件正文数据集合 
//	  * @return 
//	  */  
//	 public  final static String exportExcel(String fileName,String[] Title, List<Object> listContent) {  
//	  String result="系统提示：Excel文件导出成功！";    
//	  // 以下开始输出到EXCEL  
//	  try {      
//	   //定义输出流，以便打开保存对话框______________________begin  
//	   HttpServletResponse response=ServletActionContext.getResponse();  
//	   OutputStream os = response.getOutputStream();// 取得输出流        
//	   response.reset();// 清空输出流        
//	   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));  
//	// 设定输出文件头        
//	   response.setContentType("application/msexcel");// 定义输出类型      
//	   //定义输出流，以便打开保存对话框_______________________end  
//	  
//	   /** **********创建工作簿************ */  
//	   WritableWorkbook workbook = Workbook.createWorkbook(os);  
//	  
//	   /** **********创建工作表************ */  
//	  
//	   WritableSheet sheet = workbook.createSheet("Sheet1", 0);  
//	  
//	   /** **********设置纵横打印（默认为纵打）、打印纸***************** */  
//	   jxl.SheetSettings sheetset = sheet.getSettings();  
//	   sheetset.setProtected(false);  
//	  
//	  
//	   /** ************设置单元格字体************** */  
//	   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
//	   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
//	  
//	   /** ************以下设置三种单元格样式，灵活备用************ */  
//	   // 用于标题居中  
//	   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
//	   wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
//	   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
//	   wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
//	   wcf_center.setWrap(false); // 文字是否换行  
//	     
//	   // 用于正文居左  
//	   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
//	   wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
//	   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
//	   wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
//	   wcf_left.setWrap(false); // 文字是否换行     
//	   
//	  
//	   /** ***************以下是EXCEL开头大标题，暂时省略********************* */  
//	   //sheet.mergeCells(0, 0, colWidth, 0);  
//	   //sheet.addCell(new Label(0, 0, "XX报表", wcf_center));  
//	   /** ***************以下是EXCEL第一行列标题********************* */  
//	   for (int i = 0; i < Title.length; i++) {  
//	    sheet.addCell(new Label(i, 0,Title[i],wcf_center));  
//	   }     
//	   /** ***************以下是EXCEL正文数据********************* */  
//	   Field[] fields=null;  
//	   int i=1;  
//	   for(Object obj:listContent){  
//	       fields=obj.getClass().getDeclaredFields();  
//	       int j=0;  
//	       for(Field v:fields){  
//	           v.setAccessible(true);  
//	           Object va=v.get(obj);  
//	           if(va==null){  
//	               va="";  
//	           }  
//	           sheet.addCell(new Label(j, i,va.toString(),wcf_left));  
//	           j++;  
//	       }  
//	       i++;  
//	   }  
//	   /** **********将以上缓存中的内容写到EXCEL文件中******** */  
//	   workbook.write();  
//	   /** *********关闭文件************* */  
//	   workbook.close();     
//	  
//	  } catch (Exception e) {  
//	   result="系统提示：Excel文件导出失败，原因："+ e.toString();  
//	   System.out.println(result);   
//	   e.printStackTrace();  
//	  }  
//	  return result;  
//	 }  

}