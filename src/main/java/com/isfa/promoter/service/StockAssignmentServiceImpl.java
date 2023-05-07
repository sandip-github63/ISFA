package com.isfa.promoter.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.promoter.dao.ProductRepository;
import com.isfa.clientadminpanel.promoter.entities.Product;
import com.isfa.models.User;
import com.isfa.promoter.dao.StockAssignmentRepository;
import com.isfa.promoter.dao.StockBalanceRepository;
import com.isfa.promoter.entities.StockAssignment;
import com.isfa.promoter.entities.StockBalance;
import com.isfa.promoter.model.StockAssignmentRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.StockAssignmentResponse;
import com.isfa.repository.UserRepository;

@Service
public class StockAssignmentServiceImpl implements StockAssignmentService {

	@Autowired
	StockAssignmentRepository repository;

	@Autowired
	StockBalanceRepository balanceRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseEntity<?> getAllStockAssignment() {

		List<StockAssignment> list = repository.findAll();
		BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convertList(list);

		if (response.getDataList().isEmpty()) {
			response.setMessage("No records to Fetch");
			response.setStatus("404");

			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("Record Successfully Fetched");
		response.setStatus("200");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getStockAssignment(Long transId) {

		Optional<StockAssignment> existAssignment = repository.findById(transId);

		if (existAssignment.isPresent()) {
			BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convert(existAssignment.get());
			response.setMessage("Record successfully fetched");
			response.setStatus("200");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		BaseResponse<StockAssignmentResponse> response = new BaseResponse<>();
		response.setMessage("Record not exists to fetch");
		response.setStatus("404");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

//	@Override
//	public BaseResponse<StockAssignmentResponse> createStockAssignment(StockAssignmentRequest request) {
//		LocalDateTime now = LocalDateTime.now();
//
//		request.setCreatedDate(now);
//		request.setModifiedDate(now);
//
//		StockAssignment assignment = request.convertInto();
//
//		// Setting values in Stock Balance
//		Optional<StockBalance> existBalance = balanceRepository.findByProductIdAndStoreId(assignment.getProductId(),
//				assignment.getStoreId());
//
//		if (existBalance.isPresent()) {
//
//			StockBalance balance = existBalance.get();
//
//			if (balance.getBalance() < assignment.getTransUnit() && "DR".equalsIgnoreCase(assignment.getTransType())) {
//				BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convert(assignment);
//				response.setMessage("Record cannot be inserted because stock is not in the balance");
//				response.setStatus("200");
//				return response;
//			} else {
//				if ("CR".equalsIgnoreCase(assignment.getTransType())) {
//					balance.setBalance((balance.getBalance() + assignment.getTransUnit()));
//				} else {
//					balance.setBalance((balance.getBalance() - assignment.getTransUnit()));
//				}
//				balance.setTotalPrice((balance.getBalance() * assignment.getPrice()));
//				System.out.println("if " + balance.getTotalPrice());
//				balance.setModifiedDate(now);
//				balance.setModifiedBy(assignment.getModifiedBy());
//
//				balanceRepository.save(balance);
//				assignment = repository.save(assignment);
//			}
//		} else {
//
//			if ("CR".equalsIgnoreCase(assignment.getTransType())) {
//
//				System.out.println("In else - if");
//
//				StockBalance balance = new StockBalance();
//				balance.setStoreId(assignment.getStoreId());
//				balance.setProductId(assignment.getProductId());
//
//				balance.setBalance(assignment.getTransUnit());
//
//				balance.setTotalPrice((balance.getBalance() * assignment.getPrice()));
//				// System.out.println("else "+balance.getTotalPrice());
//
//				balance.setModifiedDate(now);
//				balance.setCreatedDate(now);
//				balance.setCreatedBy(assignment.getModifiedBy());
//				balance.setModifiedBy(assignment.getModifiedBy());
//
//				balance = balanceRepository.save(balance);
//				assignment = repository.save(assignment);
//			} else {
//				BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convert(assignment);
//				response.setMessage("Record cannot be inserted because stock is not available");
//				response.setStatus("200");
//				return response;
//			}
//		}
//
//		BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convert(assignment);
//		response.setMessage("Record successfully inserted");
//		response.setStatus("200");
//		return response;
//	}

//	@Override
//	public ResponseEntity<?> updateStockAssignment(Long transId, StockAssignmentRequest request) {
//
//		Optional<StockAssignment> existAssignment = repository.findById(transId);
//
//		if (existAssignment.isPresent()) {
//			request.setTransId(transId);
//
//			BaseResponse<StockAssignmentResponse> response = createStockAssignment(request);
//
//			response.setMessage("Record Successfully Updated");
//			response.setStatus("200");
//
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} else {
//			BaseResponse<StockAssignmentResponse> response = new BaseResponse<>();
//			response.setMessage("Record not found to Update");
//			response.setStatus("404");
//			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//		}
//	}

	@Override
	public ResponseEntity<?> deleteStockAssignment(Long transId) {

		BaseResponse<StockAssignmentResponse> response = new BaseResponse<>();
		Optional<StockAssignment> existAssignment = repository.findById(transId);

		if (existAssignment.isPresent()) {
			repository.deleteById(transId);

			response.setMessage("Record Successfully Deleted");
			response.setStatus("200");

			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		response.setMessage("Record not found to update");
		response.setStatus("404");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> addInventory(Long userId, StockAssignmentRequest request) {
		// TODO Auto-generated method stub

		// Check if there is a stock balance for this product and store
		StockAssignment assignment = request.convertInto();

		StockBalance balance = balanceRepository
				.findByProductIdAndStoreId(assignment.getProductId(), assignment.getStoreId()).orElse(null);
		Product product = productRepository.findById(assignment.getProductId()).orElse(null);

		if (balance == null) {
			// If no stock balance exists, create a new one
			System.out.println("I'm inside if");
			balance = new StockBalance();
			balance.setStoreId(assignment.getStoreId());
			balance.setProductId(assignment.getProductId());
			balance.setBalance(assignment.getTransUnit());
			if (product != null) {
				System.out.println("I'm inside if-if");
				balance.setTotalPrice(product.getPrice() * assignment.getTransUnit());
			}
//			if (product != null) {
//				System.out.println("I'm inside if-if");
//				balance.setTotalPrice(assignment.getPrice() * assignment.getTransUnit());
//			}

			LocalDateTime now = LocalDateTime.now();
			balance.setCreatedDate(now);
			balance.setModifiedDate(now);

			User user = userRepository.findById(userId).orElse(null);
			if (user != null) {
				System.out.println("I'm inside user if-if");
				balance.setCreatedBy(user.getUsername());
				balance.setModifiedBy(user.getUsername());
			}
			// Save the updated stock balance to the database
			balanceRepository.save(balance);

		} else {
			System.out.println("I'm inside else");
			// If a stock balance exists, update it

			System.out.println(balance);

			balance.setBalance(balance.getBalance() + assignment.getTransUnit());
			if (product != null) {
				balance.setTotalPrice(balance.getTotalPrice() + (product.getPrice() * assignment.getTransUnit()));
			}
//			if (product != null) {
//				balance.setTotalPrice(balance.getTotalPrice() + (assignment.getPrice() * assignment.getTransUnit()));
//			}
			LocalDateTime now = LocalDateTime.now();
			balance.setCreatedDate(now);
			balance.setModifiedDate(now);
			System.out.println("-----------^^^^^^^^^");
			User user = userRepository.findById(userId).orElse(null);
			if (user != null) {
				System.out.println("I'm inside else-if");
				balance.setCreatedBy(user.getUsername());
				balance.setModifiedBy(user.getUsername());
			}
			// Save the updated stock balance to the database
			balanceRepository.save(balance);
		}

		// Save the stock assignment to the database
		LocalDateTime now = LocalDateTime.now();

		assignment.setCreatedDate(now);
		assignment.setModifiedDate(now);

		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			System.out.println("I'm inside user-if");
			assignment.setCreatedBy(user.getUsername());
			assignment.setModifiedBy(user.getUsername());
		}

		if (product != null) {
			System.out.println("I'm inside product-if");
			assignment.setPrice(product.getPrice());
		}
		assignment.setTransactionType("RECEIVE");
		assignment.setTransType("CR");
		assignment = repository.save(assignment);
		BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convert(assignment);
		response.setMessage("Record successfully inserted");
		response.setStatus("200");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> saleProduct(Long userId, StockAssignmentRequest request) {

		Optional<StockBalance> existBalance = balanceRepository.findByProductIdAndStoreId(request.getProductId(),
				request.getStoreId());

		if (existBalance.isPresent()) {
			StockBalance balance = existBalance.get();
			Long availableUnits = balance.getBalance();

			if (availableUnits >= request.getTransUnit()) {

//				Product product = productRepository.findById(request.getProductId()).orElse(null);
//				StockAssignment existAssignment = repository.findByProductIdAndStoreId(request.getProductId(),request.getStoreId()).orElse(null);

				StockAssignment assignment = request.convertInto();

				assignment.setStoreId(request.getStoreId());
				assignment.setProductId(request.getProductId());
				assignment.setTransUnit(request.getTransUnit());
				assignment.setTransType("DR"); // Set transaction type to debit
				assignment.setTransactionType("SALE");
//				if (product != null) {
//					assignment.setPrice(product.getPrice());
//				}
				LocalDateTime now = LocalDateTime.now();
				assignment.setCreatedDate(now);
				assignment.setModifiedDate(now);

				User user = userRepository.findById(userId).orElse(null);
				if (user != null) {
					assignment.setCreatedBy(user.getUsername());
					assignment.setModifiedBy(user.getUsername());
					balance.setModifiedBy(user.getUsername());
				}

				// Update stock balance
				balance.setBalance(availableUnits - assignment.getTransUnit());
//				if (product != null) {
//					balance.setTotalPrice(balance.getBalance() * product.getPrice());
//				}
				balance.setTotalPrice(balance.getBalance() * assignment.getPrice());
				
				LocalDateTime now2 = LocalDateTime.now();
				balance.setCreatedDate(now2);
				balance.setModifiedDate(now2);

				
				balanceRepository.save(balance);

				// Save stock assignment
				assignment = repository.save(assignment);

				BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convert(assignment);
				response.setMessage("Product sold successfully");
				response.setStatus("200");
				return ResponseEntity.ok(response);
			} else {
				// Insufficient stock
				BaseResponse<StockAssignmentResponse> response = StockAssignmentResponse.convert(request.convertInto());
				response.setMessage("Sorry, there is not enough stock available");
				response.setStatus("200");
				return ResponseEntity.ok(response);
			}
		} else {
			// Item is not in stock
			BaseResponse<StockAssignmentResponse> response = new BaseResponse<>();
			response.setMessage("Sorry, the item is not in stock");
			response.setStatus("200");
			return ResponseEntity.ok(response);
		}
	}
}
