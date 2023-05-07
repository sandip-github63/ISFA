package com.isfa.promoter.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.promoter.dao.StockBalanceRepository;
import com.isfa.promoter.entities.StockBalance;
import com.isfa.promoter.model.StockBalanceRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StockBalanceResponse;

@Service
public class StockBalanceServiceImpl implements StockBalanceService{

	@Autowired
	private StockBalanceRepository repository;
	
	@Override
	public ResponseEntity<?> getAllStockBalance() {

		List<StockBalance> list = repository.findAll();
		BaseResponse<StockBalanceResponse> response = StockBalanceResponse.convertList(list);
		
		if(response.getDataList().isEmpty()) {
			response.setMessage("No records to Fetch");
			response.setStatus("404");
			
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		response.setMessage("Record Successfully Fetched");
		response.setStatus("200");
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getStockBalanceById(Long id) {
		
		Optional<StockBalance> existStock = repository.findById(id);
		
		if(existStock.isPresent()) {
			BaseResponse<StockBalanceResponse> response = StockBalanceResponse.convert(existStock.get());
			response.setMessage("Record successfully fetched");
			response.setStatus("200");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
		BaseResponse<StockBalanceResponse> response = new BaseResponse<>();
		response.setMessage("Record not exists to fetch");
		response.setStatus("404");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	
	}

	@Override
	public BaseResponse<StockBalanceResponse> createStockBalance(StockBalanceRequest request) {
		
	LocalDateTime now = LocalDateTime.now();
		
		request.setCreatedDate(now);
		request.setModifiedDate(now);
		
		StockBalance balance = StockBalanceRequest.convertInto(request);
		balance = repository.save(balance);
		
		BaseResponse<StockBalanceResponse> response = StockBalanceResponse.convert(balance);
		response.setMessage("Record successfully inserted");
		response.setStatus("200");
		return response;
	}

	@Override
	public ResponseEntity<?> updateStockBalance(Long id, StockBalanceRequest request) {
		
		Optional<StockBalance> existStock = repository.findById(id);
		
		if(existStock.isPresent()) {
			request.setId(id);
			
			BaseResponse<StockBalanceResponse> response = createStockBalance(request);
			
			response.setMessage("Record Successfully Updated");
			response.setStatus("200");
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
		BaseResponse<StockBalanceResponse> response = new BaseResponse<>();
		response.setMessage("Record not found to update");
		response.setStatus("404");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	
	}

	@Override
	public ResponseEntity<?> deleteStockBalance(Long id) {
		BaseResponse<StockBalanceResponse> response = new BaseResponse<>();
		Optional<StockBalance> existStock = repository.findById(id);
		
		if(existStock.isPresent()) {
			repository.deleteById(id);
			
			response.setMessage("Record successfully deleted");
			response.setStatus("200");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		response.setMessage("Record not found to delete");
		response.setStatus("404");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	

}
