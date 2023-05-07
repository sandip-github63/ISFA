package com.isfa.leave.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.leave.dao.LeaveTypeRepository;
import com.isfa.clientadminpanel.leave.dao.MasterLeaveRepository;
import com.isfa.clientadminpanel.leave.entities.LeaveType;
import com.isfa.clientadminpanel.leave.entities.MasterLeave;
import com.isfa.leave.dao.LeaveBalanceInfoRepository;
import com.isfa.leave.dao.LeaveRequestInfoRepository;
import com.isfa.leave.entities.LeaveBalanceInfo;
import com.isfa.leave.entities.LeaveRequestInfo;
import com.isfa.leave.entities.ReporteeRequestedLeaveAdmin;
import com.isfa.leave.model.LeaveRequestInfoRequest;
import com.isfa.leave.response.BaseResponse;
import com.isfa.leave.response.LeaveRequestInfoResponse;
import com.isfa.models.User;
import com.isfa.repository.UserRepository;

@Service
public class LeaveRequestInfoServiceImpl implements LeaveRequestInfoService {

	public static final Logger logger = LoggerFactory.getLogger(LeaveRequestInfoServiceImpl.class);

	// LeaveRequestInfoResponse response = new LeaveRequestInfoResponse();
	// LeaveRequestInfo globeLeaveRequest = new LeaveRequestInfo();
	@Autowired
	private LeaveRequestInfoRepository leaveRequestRepository;

	@Autowired
	private LeaveBalanceInfoRepository balanceInfoRepository;

	@Autowired
	private LeaveTypeRepository leaveTypeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MasterLeaveRepository masterLeaveRepository;

	@Override
	public ResponseEntity<?> getAllLeaveRequests(Long userId, Long companyId) {
		logger.info("LeaveRequestInfo Service getting All LeaveRequestInfo executing");
		logger.info("LeaveRequestInfo Repository getting All LeaveRequestInfo executing");
		List<ReporteeRequestedLeaveAdmin> reporteeRequestedLeaveList = new ArrayList<>();
		logger.info("LeaveRequestInfo Repository getting All LeaveRequestInfo completed");
//		LeaveRequestInfoResponse infoResp = new LeaveRequestInfoResponse(userRepository);
		BaseResponse<ReporteeRequestedLeaveAdmin> response = new BaseResponse<>();

		List<User> userList = userRepository.findByCompanyIdAndIdNot(companyId, userId);
//		List<User> userList = userRepository.findByCompanyId(companyId);
		System.out.println(userList);

		if (!userList.isEmpty()) {
			for (User user : userList) {

				List<LeaveRequestInfo> leaveRequestList = leaveRequestRepository
						.findAllByUserIdAndCompanyIdOrderByModifiedDateDesc(user.getId(), user.getCompanyId());
				System.out.println(leaveRequestList);
				if (!leaveRequestList.isEmpty()) {
					for (LeaveRequestInfo info : leaveRequestList) {

						ReporteeRequestedLeaveAdmin requestedLeave = new ReporteeRequestedLeaveAdmin();

						LeaveType leaveType = leaveTypeRepository.findById(info.getLeaveId()).orElse(null);
						MasterLeave masterLeave = masterLeaveRepository.findById(info.getDayId()).orElse(null);

//						if ("pending".equalsIgnoreCase(info.getLeaveStatus())) {
						requestedLeave.setLeaveRequestId(info.getLeaveRequestId());
						requestedLeave.setUserId(user.getId());
						requestedLeave.setUserName(user.getUsername());
						requestedLeave.setNumberOfDays(info.getNumberOfDays());
						requestedLeave.setLeaveRemark(info.getLeaveRemark());
						requestedLeave.setReason(info.getReason());
						System.out.println("\n\n\nHiiiiii");
						requestedLeave.setLeaveStatus(info.getLeaveStatus());
						requestedLeave.setFromDate(info.getDateFrom().toString());
						requestedLeave.setToDate(info.getDateTo().toString());
						if (leaveType != null) {
							requestedLeave.setLeaveType(leaveType.getLeaveType());
						}
						if (masterLeave != null) {
							requestedLeave.setDayPart(masterLeave.getDescription());
						}
//						}
						if (!(requestedLeave.getLeaveRequestId() == null && requestedLeave.getUserId() == null
								&& requestedLeave.getUserName() == null && requestedLeave.getDayPart() == null
								&& requestedLeave.getNumberOfDays() == null && requestedLeave.getLeaveStatus() == null
								&& requestedLeave.getLeaveType() == null && requestedLeave.getReason() == null
								&& requestedLeave.getFromDate() == null && requestedLeave.getToDate() == null
								&& requestedLeave.getLeaveRemark() == null)) {
							reporteeRequestedLeaveList.add(requestedLeave);
						}
					}
				}

			}
		}

		response.setDataList(reporteeRequestedLeaveList);

		if (response.getDataList().isEmpty()) {
			logger.info("LeaveRequestInfo List is empty");
			String message = "No leaves found";
			response.setMessage(message);
			response.setStatus("200");
			logger.info("LeaveRequestInfo Service getting All LeaveRequestInfo completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		logger.info("LeaveRequestInfo Service getting All LeaveRequestInfo completed");
		response.setMessage("LeaveRequest List successfully fetched");
		response.setStatus("200");
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@Override
	public ResponseEntity<?> getLeaveRequestById(Long id) {
		logger.info("LeaveRequestInfo Service Getting LeaveRequestInfo By Id executing");
		BaseResponse<LeaveRequestInfoResponse> response = new BaseResponse<>();
		LeaveRequestInfo globeLeaveRequest = new LeaveRequestInfo();
		logger.info("LeaveRequestInfo Repository Getting LeaveRequestInfo By Id executing");
		Optional<LeaveRequestInfo> leaveRequest = leaveRequestRepository.findById(id);
		logger.info("LeaveRequestInfo Repository Getting LeaveRequestInfo By Id completed");

		if (leaveRequest.isPresent()) {
			logger.info("LeaveRequestInfo at provided id exists ");
			globeLeaveRequest = leaveRequest.get();
			LeaveRequestInfoResponse infoResp = new LeaveRequestInfoResponse(userRepository);
			response = infoResp.convert(globeLeaveRequest);
			response.setMessage("Record has been successfully fetched");
			response.setStatus("200");
			logger.info("LeaveRequestInfo Service Getting LeaveRequestInfo By Id completed");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			logger.info("LeaveRequestInfo at provided id doesn't exists ");
			String message = "Icon with ID " + id + " not found";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("LeaveRequestInfo Service Getting LeaveRequestInfo By Id completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@Override
	public BaseResponse<LeaveRequestInfoResponse> addLeaveRequest(LeaveRequestInfoRequest leaveRequest) {
		logger.info("LeaveRequestInfo Service Saving New LeaveRequestInfo executing");
		BaseResponse<LeaveRequestInfoResponse> response = new BaseResponse<>();
		LeaveBalanceInfo info = balanceInfoRepository.findByUserIdAndCompanyIdAndLeaveId(leaveRequest.getUserId(),
				leaveRequest.getCompanyId(), leaveRequest.getLeaveId()).orElse(null);
		System.out.println(info);
		LeaveType leaveType = leaveTypeRepository.findById(leaveRequest.getLeaveId()).orElse(null);

		if (leaveType != null && leaveType.getActive() == false) {
			response.setMessage("Sorry!! You cann't apply for this leave");
			response.setStatus("400");
			logger.info("LeaveRequestInfo Service Saving New LeaveRequestInfo completed");
			return response;
		}

		LocalDateTime now = LocalDateTime.now();
		leaveRequest.setCreatedDate(now);
		leaveRequest.setModifiedDate(now);
		info.setModifiedDate(now);
		Double numberDays = 0D;

		LocalDate startDate = leaveRequest.getDateFrom();
		LocalDate lastDate = leaveRequest.getDateTo();
		for (LocalDate date = startDate; date.isBefore(lastDate.plusDays(1)); date = date.plusDays(1)) {
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
				numberDays++;
			}
		}

//		leaveRequest.setNumberOfDays(numberDays);
		
		if (info != null && info.getBalance() < numberDays) {
			response.setMessage("Sorry!! You cann't apply for this leave");
			response.setStatus("400");
			logger.info("LeaveRequestInfo Service Saving New LeaveRequestInfo completed");
			return response;
		}

		if (info != null && leaveRequest.getLeaveStatus() != null) {

			if ("rejected".equalsIgnoreCase(leaveRequest.getLeaveStatus())) {
//				info.setBalance(info.getBalance() + 1);
				MasterLeave masterLeave = masterLeaveRepository.findById(leaveRequest.getDayId()).orElse(null);
				if (masterLeave != null) {
					info.setBalance(info.getBalance() + (numberDays*masterLeave.getQuantity()));
				}
				
			} else if("pending".equalsIgnoreCase(leaveRequest.getLeaveStatus())) {
				MasterLeave masterLeave = masterLeaveRepository.findById(leaveRequest.getDayId()).orElse(null);
				System.out.println(masterLeave);
				if (masterLeave != null) {
					info.setBalance(info.getBalance() - (numberDays*masterLeave.getQuantity()));
					leaveRequest.setNumberOfDays(numberDays*masterLeave.getQuantity());
				}
			}

		}

		response = new LeaveRequestInfoResponse(userRepository);

		User user = userRepository.findById(leaveRequest.getUserId()).orElse(null);
		if (user != null) {
			leaveRequest.setModifiedBy(user.getUsername());
			info.setModifiedBy(user.getUsername());
		}

		LeaveRequestInfo leave = leaveRequest.convertInto();
		logger.info("LeaveRequestInfo Repository Saving New LeaveRequestInfo executing");
		
		info = balanceInfoRepository.save(info);
		LeaveRequestInfo globeLeaveRequest = leaveRequestRepository.save(leave);
		logger.info("LeaveRequestInfo Repository Saving New LeaveRequestInfo completed");
		LeaveRequestInfoResponse infoResp = new LeaveRequestInfoResponse(userRepository);
		response = infoResp.convert(globeLeaveRequest);
		response.setMessage("Successfully Added");
		response.setStatus("200");
		logger.info("LeaveRequestInfo Service Saving New LeaveRequestInfo completed");
		return response;
	}

	@Override
	public ResponseEntity<?> deleteLeaveRequest(Long id) {
		logger.info("LeaveRequestInfo Service Deleting LeaveRequestInfo By Id executing");
		BaseResponse<LeaveRequestInfoResponse> response = new LeaveRequestInfoResponse(userRepository);
		logger.info("LeaveRequestInfo Repository Deleting LeaveRequestInfo By Id executing");
		Optional<LeaveRequestInfo> leaveRequest = leaveRequestRepository.findById(id);
		logger.info("LeaveRequestInfo Repository Deleting LeaveRequestInfo By Id completed");
		if (leaveRequest.isPresent()) {
			logger.info("LeaveRequestInfo exists at provided id...in order to delete");
			logger.info("LeaveRequestInfo Repository Deleting LeaveRequestInfo By Id executing");
			this.leaveRequestRepository.deleteById(id);
			logger.info("LeaveRequestInfo Repository Deleting LeaveRequestInfo By Id completed");
			String message = "Icon with ID " + id + " deleted successfully";
			response.setMessage(message);
			response.setStatus("200");
			logger.info("LeaveRequestInfo Service Deleting LeaveRequestInfo By Id completed");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		} else {
			logger.info("LeaveRequestInfo does not exists at provided id...in order to delete");
			String message = "Icon with ID " + id + " not found to delete";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("LeaveRequestInfo Service Deleting LeaveRequestInfo By Id completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@Override
	public ResponseEntity<?> updateLeaveRequest(Long id, LeaveRequestInfoRequest leaveRequestInfo) {
		logger.info("LeaveRequestInfo Service Updating Existing LeaveRequestInfo executing");
		BaseResponse<LeaveRequestInfoResponse> response = new BaseResponse<>();
		logger.info("LeaveRequestInfo Repository Updating Existing LeaveRequestInfo executing");
		Optional<LeaveRequestInfo> existLeaveRequest = leaveRequestRepository
				.findById(leaveRequestInfo.getLeaveRequestId());
		logger.info("LeaveRequestInfo Repository Updating Existing LeaveRequestInfo completed");
		if (existLeaveRequest.isPresent()) {
			logger.info("LeaveRequestInfo exists at provided id...in order to update");
			LeaveRequestInfo leaveRequest = existLeaveRequest.get();
			leaveRequestInfo.setLeaveRequestId(leaveRequest.getLeaveRequestId());
			leaveRequestInfo.setCompanyId(leaveRequest.getCompanyId());
			leaveRequestInfo.setUserId(leaveRequest.getUserId());
			leaveRequestInfo.setLeaveId(leaveRequest.getLeaveId());
			leaveRequestInfo.setDayId(leaveRequest.getDayId());
			leaveRequestInfo.setDateFrom(leaveRequest.getDateFrom());
			leaveRequestInfo.setDateTo(leaveRequest.getDateTo());
//			leaveRequestInfo.setNumberOfDays(leaveRequest.getNumberOfDays());
			leaveRequestInfo.setReason(leaveRequest.getReason());
			leaveRequestInfo.setLeaveRemark(leaveRequest.getLeaveRemark());

			// setting createdBy,modifiedBy,approvedBy
			User user = userRepository.findById(id).orElse(null);
			System.out.println("User is " + user + "by id " + id);
			if (user != null) {
				if (("approved".equalsIgnoreCase(leaveRequestInfo.getLeaveStatus()))) {
					System.out.println("I'm inside.................................");
					leaveRequestInfo.setApprovedBy(user.getUsername());
					leaveRequestInfo.setApprovedDate(LocalDate.now());
				}
			}

			response = addLeaveRequest(leaveRequestInfo);
			response.setMessage("Data has been successfully updated");
			response.setStatus("200");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			logger.info("LeaveRequestInfo does not exists at provided id...in order to update");
			String message = "LeaveRequestInfo with ID " + id + " not found to update";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("LeaveRequestInfo Service Updating Existing LeaveRequestInfo completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

	}

}
