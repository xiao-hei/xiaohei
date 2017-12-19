package com.baizhi.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;


@Controller
@RequestMapping("/poi")
public class POIController {
	
	@Resource(name="userService")
	UserService us;
	
	@RequestMapping("export")
	public void export(HttpServletResponse response){
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("用户信息");
			HSSFRow row = sheet.createRow(0);
			String[] st = {"编号","姓名"};
			HSSFCellStyle cell = workbook.createCellStyle();
			short format2 = workbook.createDataFormat().getFormat("yyyy年MM月dd日");
			cell.setDataFormat(format2);
			for (int i = 0; i < st.length; i++) {
				//设置表头
				row.createCell(i).setCellValue(st[i]);
			}
			for (int i = 1; i <= 10; i++) {
				row = sheet.createRow(i);
				row.createCell(0).setCellValue(i);
				row.createCell(1).setCellValue("zs" + i);
				row.createCell(2).setCellValue(new Date());
				HSSFCell hcell = row.createCell(2);
				hcell.setCellValue(new Date());
				hcell.setCellStyle(cell);
			}
			String format = new SimpleDateFormat("MM-dd").format(new Date());
			
			//设置文件名
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode("用户信息", "utf-8") + "-" + format
					+ ".xls");
			
			response.setContentType("application/vnd.ms-excel");
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@RequestMapping("exportUser")
	public void exportUser(HttpServletResponse response){
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("用户信息");
			HSSFRow row = sheet.createRow(0);
			String[] st = {"编号","姓名","生日"};
			HSSFCellStyle cell = workbook.createCellStyle();
			short format2 = workbook.createDataFormat().getFormat("yyyy年MM月dd日");
			cell.setDataFormat(format2);
			List<User> all = us.findAll();
			for (int i = 0; i < st.length; i++) {
				//设置表头
				row.createCell(i).setCellValue(st[i]);
			}
			System.out.println(all.size());
			for (int i = 0; i < all.size(); i++) {
				row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(all.get(i).getId());
				row.createCell(1).setCellValue(all.get(i).getName());
				row.createCell(2).setCellValue(all.get(i).getBirthday());
				HSSFCell hcell = row.createCell(2);
				hcell.setCellValue(all.get(i).getBirthday());
				hcell.setCellStyle(cell);
			}
			String format = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
			//设置文件名
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode("用户信息", "utf-8") + "-" + format
					+ ".xls");
			response.setContentType("application/vnd.ms-excel");
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@RequestMapping("import")
	public void importUser(MultipartFile fe){
		HSSFWorkbook workbook;
		User u;
		try {
			u = new User();
			//创建workbook
			workbook = new HSSFWorkbook(fe.getInputStream());
			
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = sheet.getRow(0);
			for (int i = 1; i < sheet.getLastRowNum(); i++) {
				//根据行坐标填充数据
				row = sheet.getRow(i);
				u.setName(row.getCell(1).getStringCellValue());
				u.setBirthday(row.getCell(2).getDateCellValue());
				us.addUser(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
