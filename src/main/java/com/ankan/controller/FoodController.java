package com.ankan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankan.model.Food;
import com.ankan.service.FoodService;

@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "http://localhost:3000/")
public class FoodController {
	@Autowired
	private FoodService fservice;
	@PostMapping("/add")
	public ResponseEntity<String> addData(@RequestBody Food f){
		String msg = "DATA INSERTED";
		fservice.addData(f);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	@GetMapping("/fetch")
	public ResponseEntity<List<Food>> getData(){
		List<Food> flist=fservice.getData();
		return new ResponseEntity<List<Food>>(flist,HttpStatus.OK);
	}
	@GetMapping("/fetch/{fid}")
	public ResponseEntity<Food> getFidDetails(@PathVariable String fid){
		Food fs = fservice.getFidDetails(fid);
		return new ResponseEntity<Food>(fs,HttpStatus.OK);
	}
	@DeleteMapping("/del/{fid}")
	public ResponseEntity<String> deleteData(@PathVariable String fid){
		String fs = "FOOD ITEM DELETED";
		fservice.deleteData(fid);
		return new ResponseEntity<String>(fs,HttpStatus.OK);
	}
	@PutMapping("/upd/{fid}")
	public ResponseEntity<String> updata(@PathVariable String fid, @RequestBody Food f){
		Food fs = fservice.updateData(fid,f);
		String msg="";
		if(fs!=null)
		{
			msg="FOOD ITEM UPDATED UPDATED";
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		else
		{
			msg="UPDATION FAILED";
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
	}
}
