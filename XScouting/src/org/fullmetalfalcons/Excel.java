package org.fullmetalfalcons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
				workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
				sheet = workbook.getSheet("Results");
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
		
		Row topRow = sheet.createRow(0);
		Row bottomRow = sheet.createRow(1);
		
		//Create Style with a bottom border, right border, and center alligned
		CellStyle topLabel = workbook.createCellStyle();
		topLabel.setBorderBottom(CellStyle.BORDER_THIN);
		topLabel.setBorderRight(CellStyle.BORDER_THIN);
		topLabel.setAlignment(CellStyle.ALIGN_CENTER);
		topLabel.setWrapText(true);
		
		//Create a style with a bottom border
		CellStyle bottomLabel = workbook.createCellStyle();
		bottomLabel.setBorderBottom(CellStyle.BORDER_THIN);
		bottomLabel.setWrapText(true);
		
		Cell topCell;
		Cell bottomCell;
		
		//Locate the headers list
		URL headingsUrl=Excel.class.getResource("/org/fullmetalfalcons/files/headings.txt");
		//locate the sections list
		URL sectionsUrl = Excel.class.getResource("/org/fullmetalfalcons/files/sections.txt");
		try {
			File headingsFile=new File(headingsUrl.toURI());
			File sectionsFile = new File(sectionsUrl.toURI());
			//Get a list of the sections
			List<String> sections = Files.readAllLines(sectionsFile.toPath());
			//Current cell
			int counter = 0;
			//Current Section
			int sectionNumber = 0;
			//Column number where this section began
			int lastSection = 0;
			//For every header:
			for (String header:Files.readAllLines(headingsFile.toPath())){
				bottomCell = bottomRow.createCell(counter);
				//If the line in the file starts with !
				if (header.charAt(0)=='!'){
					bottomCell.setCellValue(header.substring(1));
					//add a side border
					bottomCell.setCellStyle(topLabel);
					
					//Make all of the cells in the top row of this section topStyle
					for(int i = lastSection; i<=counter;i++){
						topCell = topRow.createCell(i);
						topCell.setCellStyle(topLabel);
					}
					
					topCell = topRow.getCell(lastSection);
					//set the section values to the correct value
					topCell.setCellValue(sections.get(sectionNumber));
					//Merge the section labels
					sheet.addMergedRegion(new CellRangeAddress(0,0,lastSection,counter));
					
					sectionNumber++;
					lastSection = counter+1;
					
				} else {
					bottomCell.setCellValue(header);
					bottomCell.setCellStyle(bottomLabel);
				}
				counter++;
			}
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
		    FileOutputStream out = new FileOutputStream("results.xlsx");
		    workbook.write(out);
		    out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	public static void export(){
		int rowNum = sheet.getLastRowNum()+1;
		Row r;
		Cell c;
		CellStyle sectionEnd = workbook.createCellStyle();
		sectionEnd.setBorderRight(CellStyle.BORDER_THIN);
		
		for (Team t: TeamUtils.TEAMS.values()){
			if (t.hasData()){
				r = sheet.createRow(rowNum);
				c = r.createCell(0);
				c.setCellValue(t.getNumber());
				
				c = r.createCell(1);
				if (t.getName()==null){
					c.setCellValue("");
				} else {
					c.setCellValue(t.getName());
				}
				
				c = r.createCell(2);
				if (t.getLocation()==null){
					c.setCellValue("");
				} else {
					c.setCellValue(t.getLocation());
				}
				
				c = r.createCell(3);
				c.setCellValue(t.getMatchNum());
				
				c = r.createCell(4);
				c.setCellValue(t.getColor());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(5);
				c.setCellValue(t.getStart());
				
				c = r.createCell(6);
				if (t.getAutoZone()==2){
					c.setCellValue("Tried but failed");
				} else {
					c.setCellValue(t.getAutoZone());
				}
				
				c = r.createCell(7);
				c.setCellValue(t.getToteInteraction());
				
				c = r.createCell(8);
				c.setCellValue(t.getAutoZone());
				
				c = r.createCell(9);
				c.setCellValue(t.getBinAutozone());
				
				c = r.createCell(10);
				c.setCellValue(t.getStackSize());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(11);
				c.setCellValue(t.getTotesScored());
				
				c = r.createCell(12);
				c.setCellValue(t.getLitterScored());
				
				c = r.createCell(13);
				c.setCellValue(t.getLitterStack());
				
				c = r.createCell(14);
				c.setCellValue(t.getLitterLandfill());
				
				c = r.createCell(15);
				c.setCellValue(t.getRecycleStack());
				
				c = r.createCell(16);
				c.setCellValue(t.getCoopPlatform());
				
				c = r.createCell(17);
				c.setCellValue(t.getCoopStack());
				
				c = r.createCell(18);
				c.setCellValue(t.getTotesKnocked());
				
				c = r.createCell(19);
				c.setCellValue(t.getFouls());
				
				c = r.createCell(20);
				c.setCellValue(t.getTelTotalPoints());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(21);
				c.setCellValue(t.getHumanFouls());
				
				c = r.createCell(22);
				c.setCellValue(t.getHumanBins());
				
				c = r.createCell(23);
				c.setCellValue(t.getHumanNoodles());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(24);
				c.setCellValue(t.getAutTotalScore());
				
				c = r.createCell(25);
				c.setCellValue(t.getTelTotalScore());
				
				c = r.createCell(26);
				c.setCellValue(7.5);
				
				c = r.createCell(27);
				c.setCellValue(t.getAutTotalScore()+t.getTelTotalScore());
				c.setCellStyle(sectionEnd);
				
				rowNum++;
			}
		}
		
		
		
		
		try {
		    FileOutputStream out = new FileOutputStream("results.xlsx");
		    workbook.write(out);
		    out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
