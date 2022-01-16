package com.excel.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.project.helper.Helper;
import com.excel.project.model.Product;
import com.excel.project.service.ProductService;

@RestController
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		if(Helper.checkExcelFormat(file)) {
			this.productService.save(file);
			return ResponseEntity.ok(Map.of("message","File is uploaded and data is save to db."));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file.");
	}
	
	@GetMapping("/product")
	public List<Product> getAllProduct(){
		return this.productService.getAllProducts();
	}
}
