package com.cappuccino.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cappuccino.util.ExcleUtil;

@Controller
@RequestMapping("file")
public class FileUploadController {

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void fileUpolad(@RequestParam MultipartFile filename, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String fileName = filename.getOriginalFilename();
			if (fileName.endsWith("xls")) {
				System.out.println("*******>>> xls");
			} else if (fileName.endsWith("xlsx")) {
				System.out.println("*******>>>xlsx");
			}
			InputStream fileInput = filename.getInputStream();
			HSSFWorkbook hssWorkBook = new HSSFWorkbook(fileInput);
			List<List<String>> result = new ArrayList<List<String>>();
			// 处理每一页
			for (int page = 0; page < hssWorkBook.getNumberOfSheets(); page++) {
				// 某一页数据
				HSSFSheet hssFsheet = hssWorkBook.getSheetAt(page);
				if (null == hssFsheet) {
					continue;
				}
				for (int rowNum = 0; rowNum <= hssFsheet.getLastRowNum(); rowNum++) {
					// 某一行数据
					HSSFRow hssfRow = hssFsheet.getRow(rowNum);

					int minColIx = hssfRow.getFirstCellNum();
					int maxCollx = hssfRow.getLastCellNum();

					List<String> rowList = new ArrayList<String>();
					for (int colix = minColIx; colix < maxCollx; colix++) {
						HSSFCell cell = hssfRow.getCell(colix);
						if (null == cell) {
							continue;
						}
						rowList.add(ExcleUtil.getStringVal(cell));
					}
					result.add(rowList);
				}

			}
			for (List<String> info : result) {
				System.out.println(info.toString());
				// for (String message : info) {
				// System.out.println("=====>>" + message);
				// }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
