package org.fullmetalfalcons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Handles the setup and writing of the Excel workbook containing the results
 * 
 * @author Dan
 * 
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
	
	/**
	 * Sets up workbook based on the sections.txt and headers.txt files
	 * Iterates along the headers and when it reaches one that starts with an !, it retroactively adds the next section in the 
	 * cells above it
	 * 
	 * Don't touch
	 */
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
		
		try (InputStream headIn = Excel.class.getResourceAsStream("/org/fullmetalfalcons/files/headings.txt");
			BufferedReader headReader = new BufferedReader(new InputStreamReader(headIn));
				InputStream sectionIn = Excel.class.getResourceAsStream("/org/fullmetalfalcons/files/sections.txt");
				BufferedReader sectionReader = new BufferedReader(new InputStreamReader(sectionIn));
				){
			
			//Current cell
			int counter = 0;
			//Column number where this section began
			int lastSection = 0;
			//For every header:
			String header;
			while ((header=headReader.readLine())!=null){
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
					topCell.setCellValue(sectionReader.readLine());
					//Merge the section labels
					sheet.addMergedRegion(new CellRangeAddress(0,0,lastSection,counter));
					
					lastSection = counter+1;
					
				} else {
					bottomCell.setCellValue(header);
					bottomCell.setCellStyle(bottomLabel);
				}
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	    write();
		
	}
	
	public static void export(){
		int rowNum = sheet.getLastRowNum()+1;
		Row r;
		Cell c;
		CellStyle sectionEnd = workbook.createCellStyle();
		sectionEnd.setBorderRight(CellStyle.BORDER_THIN);
		int cellnum;
		for (Team t: TeamUtils.TEAMS.values()){
			cellnum = 0;
			if (t.hasData()){
				
				//The following code is abysmal, however I don't feel like rewriting the entire team class
				//TODO stop being lazy
				r = sheet.createRow(rowNum);
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getNumber());
				
				c = r.createCell(cellnum++);
				if (t.getName()==null){
					c.setCellValue("");
				} else {
					c.setCellValue(t.getName());
				}
				
				c = r.createCell(cellnum++);
				if (t.getLocation()==null){
					c.setCellValue("");
				} else {
					c.setCellValue(t.getLocation());
				}
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getMatchNum());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getColor());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getStart());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getAutoZone());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getToteInteraction());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getAutoZone());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getBinAutozone());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getStackSize());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getTotesScored());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getLitterScored());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getLitterStack());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getLitterLandfill());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getUnprocessedLitter());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getRecycleStack());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getCoopPlatform());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getCoopStack());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getTotesKnocked());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getCanNoodles());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getFouls());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getTelTotalPoints());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getHumanFouls());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getHumanBins());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getHumanNoodlesOwn());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getHumanNoodlesOpponent());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getHumanNoodlesOther());
				c.setCellStyle(sectionEnd);
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getAutTotalScore());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getTelTotalScore());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getHumanTotalScore());
				
				c = r.createCell(cellnum++);
				c.setCellValue(t.getAutTotalScore()+t.getTelTotalScore()+t.getHumanTotalScore());
				c.setCellStyle(sectionEnd);
				
				rowNum++;
			}
		}
		
		write();
		
	}
	
	
	/**
	 * Writes the excel workbook out to "results.xlsx" in the Directory specified
	 */
	private static void write(){
		
		try {
		    FileOutputStream out = new FileOutputStream(ScoutingMain.directoryPath + "/results.xlsx");
		    workbook.write(out);
		    out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
