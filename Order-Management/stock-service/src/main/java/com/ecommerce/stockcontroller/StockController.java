package com.ecommerce.stockcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.StockDetails;
import com.ecommerce.model.StockDto;
import com.ecommerce.repository.stockrepo;
@RestController
@RequestMapping("/api/v2")
public class StockController {
	@Autowired
	private  stockrepo repo ;
	
	@PostMapping("/inventory")
	public ResponseEntity<String> Postorder(@RequestBody StockDto stockdto) {
		Optional<StockDetails> exist=repo.findByName(stockdto.getName());
		if(exist.isPresent()) {
			return new  ResponseEntity<>("product exist",HttpStatus.CONFLICT);
		}
		else {
			StockDetails stock=new StockDetails();
			
			
			stock.setName(stockdto.getName());
			stock.setAvailablequantity(stockdto.getAvailablequantity());
			stock.setCost(stockdto.getCost());
			repo.save(stock);
			return new ResponseEntity<>( "Product added succesfully",HttpStatus.CREATED);
		}
		
		
		
		
	}
}
