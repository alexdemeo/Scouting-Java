package org.fullmetalfalcons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * @author Dan
 * @author Demooph
 */
public class Excel {
	
	private static File file = new File("results.xlsx");
	private static XSSFWorkbook workbook;
	private static Sheet sheet;
	
	public static void init(){
		if (file.exists()){
			try {
				workbook = new XSSFWorkbook(file);
			} catch (InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
		} else {
			setupWorkbook();
		}
	}
	
	public static void setupWorkbook(){
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Results");
		Row r = sheet.createRow(0);
		
		CellStyle topLabel = workbook.createCellStyle();
		topLabel.setBorderBottom(CellStyle.BORDER_THIN);
		topLabel.setBorderRight(CellStyle.BORDER_THIN);
		topLabel.setAlignment(CellStyle.ALIGN_CENTER);
		topLabel.setWrapText(true);
		
		Cell c = r.createCell(0);
		c.setCellValue("General");
		c.setCellStyle(topLabel);
		
		//Have to apply style to all cells in merged region or else it won't work correctly
		for (int i = 1; i<5;i++){
			c = r.createCell(i);
			c.setCellStyle(topLabel);
		}
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		
		c = r.createCell(5);
		c.setCellValue("Autonomous");
		c.setCellStyle(topLabel);
		
		//Have to apply style to all cells in merged region or else it won't work correctly
		for (int i = 6; i<11;i++){
			c = r.createCell(i);
			c.setCellStyle(topLabel);
		}
		sheet.addMergedRegion(new CellRangeAddress(0,0,5,10));
		
		c = r.createCell(11);
		c.setCellValue("Teleop");
		c.setCellStyle(topLabel);
		
		//Have to apply style to all cells in merged region or else it won't work correctly
		for (int i = 12; i<21;i++){
			c = r.createCell(i);
			c.setCellStyle(topLabel);
		}
		sheet.addMergedRegion(new CellRangeAddress(0,0,11,20));
		
		c = r.createCell(21);
		c.setCellValue("Human");
		c.setCellStyle(topLabel);
		
		//Have to apply style to all cells in merged region or else it won't work correctly
		for (int i = 22; i<24;i++){
			c = r.createCell(i);
			c.setCellStyle(topLabel);
		}
		sheet.addMergedRegion(new CellRangeAddress(0,0,21,23));
		
		c = r.createCell(24);
		c.setCellValue("Totals");
		c.setCellStyle(topLabel);
		
		//Have to apply style to all cells in merged region or else it won't work correctly
		for (int i = 25; i<28;i++){
			c = r.createCell(i);
			c.setCellStyle(topLabel);
		}
		sheet.addMergedRegion(new CellRangeAddress(0,0,24,27));
		
		CellStyle bottomLabel = workbook.createCellStyle();
		bottomLabel.setBorderBottom(CellStyle.BORDER_THIN);
		bottomLabel.setWrapText(true);
		
		r = sheet.createRow(1);
		
		c = r.createCell(0);
		c.setCellValue("Team Number");
		c.setCellStyle(bottomLabel);
		
		c = r.createCell(1);
		c.setCellValue("Team Name");
		c.setCellStyle(bottomLabel);
		
		c = r.createCell(2);
		c.setCellValue("Team Location");
		c.setCellStyle(bottomLabel);
		
		c = r.createCell(3);
		c.setCellValue("Match Number");
		c.setCellStyle(bottomLabel);
		
		c = r.createCell(4);
		c.setCellValue("Alliance Color");
		c.setCellStyle(topLabel);
		
		
		try(FileOutputStream fileOut = new FileOutputStream("results.xlsx");){
			workbook.write(fileOut);
		    fileOut.close();
		} catch(Exception e){
			
		}
	    
	}
	
	public static void export(){
		
	}

}
