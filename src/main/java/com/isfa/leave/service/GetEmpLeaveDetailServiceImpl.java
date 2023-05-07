package com.isfa.leave.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.leave.dao.LeaveTypeRepository;
import com.isfa.clientadminpanel.leave.entities.Color;
import com.isfa.clientadminpanel.leave.entities.Icon;
import com.isfa.clientadminpanel.leave.entities.LeaveType;
import com.isfa.leave.dao.LeaveBalanceInfoRepository;
import com.isfa.leave.dao.LeaveRequestInfoRepository;
import com.isfa.leave.entities.EmpAppliedLeave;
import com.isfa.leave.entities.LeaveBalanceInfo;
import com.isfa.leave.entities.LeaveRequestInfo;
import com.isfa.leave.entities.LeaveTypeBalance;
import com.isfa.leave.entities.ReporteeRequestedLeave;
import com.isfa.leave.response.EmpLeaveDetailResponse;
import com.isfa.models.User;
import com.isfa.repository.UserRepository;

@Service
public class GetEmpLeaveDetailServiceImpl implements GetEmpLeaveDetailService {

	@Autowired
	LeaveTypeRepository leaveTypeRepo;

	@Autowired
	LeaveBalanceInfoRepository leaveBalanceInfoRepo;

	@Autowired
	LeaveRequestInfoRepository leaveRequestInfoRepo;

//	@Autowired
//	ColorRepository colorRepository;
//
//	@Autowired
//	IconRepository iconRepository;

	@Autowired
	UserRepository userRepository;

	Color color;
	Icon icon;

	EmpLeaveDetailResponse response = new EmpLeaveDetailResponse();

	public ResponseEntity<?> getEmpLeaveDetails(Long userId, Long companyId) {
		
		 System.out.println("--------------------------Setting upper 3 values----------------------------");
			
		List<LeaveBalanceInfo> balanceList = leaveBalanceInfoRepo.findAllByUserIdAndCompanyId(userId, companyId);
		System.out.println(balanceList);
		Long totalLeave = 0L;
		Double leaveBalace = 0D;

		if (!balanceList.isEmpty()) {

			for (LeaveBalanceInfo info : balanceList) {
				totalLeave += info.getTotalAllocated();
				leaveBalace += info.getBalance();
			}

		}
		response.setTotalLeave(totalLeave);
		response.setLeaveBalance(leaveBalace);
		response.setUsedLeave(totalLeave - leaveBalace);

		List<LeaveTypeBalance> leaveTypeBalanceList = new ArrayList<>();
		List<EmpAppliedLeave> empAppliedLeaveList = new ArrayList<>();
		List<ReporteeRequestedLeave> reporteeRequestedLeaveList = new ArrayList<>();

		
	    System.out.println("--------------------------EmpAppliedLeave----------------------------");

	    List<LeaveRequestInfo> requestInfoList = leaveRequestInfoRepo.findAllByUserIdAndCompanyIdOrderByModifiedDateDesc(userId, companyId);
		System.out.println(requestInfoList); // --------------------

	    
		if (requestInfoList != null) {
			for (LeaveRequestInfo info : requestInfoList) {
				EmpAppliedLeave empAppliedLeave = new EmpAppliedLeave();

				LeaveType leaveType = leaveTypeRepo.findById(info.getLeaveId()).orElse(null);
				System.out.println(leaveType); // --------------------

				empAppliedLeave.setLeaveStatus(info.getLeaveStatus());
				empAppliedLeave.setFromDate(info.getDateFrom().toString());
				empAppliedLeave.setToDate(info.getDateTo().toString());

				if (leaveType != null) {
					empAppliedLeave.setLeaveType(leaveType.getLeaveType());

				}

				if (!(empAppliedLeave.getLeaveStatus() == null && empAppliedLeave.getLeaveType() == null
						&& empAppliedLeave.getFromDate() == null && empAppliedLeave.getToDate() == null)) {
					empAppliedLeaveList.add(empAppliedLeave);
				}

			}
		}

		 System.out.println("--------------------------LeaveTypeBalance----------------------------");
			
		List<LeaveBalanceInfo> leaveBalanceList = leaveBalanceInfoRepo.findAllByUserIdAndCompanyId(userId, companyId);
		System.out.println(leaveBalanceList);

		if (!leaveBalanceList.isEmpty()) {
			for (LeaveBalanceInfo info : leaveBalanceList) {
				
				LeaveTypeBalance balance = new LeaveTypeBalance();

				balance.setLeaveTypeBalance(info.getBalance());

				LeaveType leaveType = leaveTypeRepo.findById(info.getLeaveId()).orElse(null);
				if (leaveType != null) {
					balance.setLeaveTypeName(leaveType.getLeaveType());
					balance.setLeaveTypeColor(leaveType.getColor());
					balance.setLeaveTypeIcon(leaveType.getIcon());
//					color = colorRepository.findById((leaveType.getColor()).longValue()).orElse(null);
//					icon = iconRepository.findById((leaveType.getIcon()).longValue()).orElse(null);
				}
//				if (color != null) {
//					balance.setLeaveTypeColor(color.getType());
//				}
//				if (icon != null) {
//					balance.setLeaveTypeIcon(icon.getIconType());
//				}

				if (!(balance.getLeaveTypeName() == null && balance.getLeaveTypeBalance() == null
						&& balance.getLeaveTypeColor() == null && balance.getLeaveTypeIcon() == null)) {
					leaveTypeBalanceList.add(balance);
				}
			}
		}
		
		 System.out.println("--------------------------ReporteeRequestedLeave----------------------------");
			

		List<User> userList = userRepository.findUsersSupervisedBy(userId.toString());
		System.out.println(userList);

		if (!userList.isEmpty()) {
			for (User user : userList) {
				
//				ReporteeRequestedLeave requestedLeave = new ReporteeRequestedLeave();
//				
				List<LeaveRequestInfo> leaveRequestList = leaveRequestInfoRepo
						.findAllByUserIdAndCompanyIdOrderByModifiedDateDesc(user.getId(), user.getCompanyId());

				if (!leaveRequestList.isEmpty()) {
					for (LeaveRequestInfo info : leaveRequestList) {

						ReporteeRequestedLeave requestedLeave = new ReporteeRequestedLeave();
						LeaveType leaveType = leaveTypeRepo.findById(info.getLeaveId()).orElse(null);

						if ("pending".equalsIgnoreCase(info.getLeaveStatus())) {
							requestedLeave.setLeaveRequestId(info.getLeaveRequestId());
							requestedLeave.setUserName(user.getUsername());
							requestedLeave.setReason(info.getReason());
							requestedLeave.setLeaveStatus(info.getLeaveStatus());
							requestedLeave.setFromDate(info.getDateFrom().toString());
							requestedLeave.setToDate(info.getDateTo().toString());
							if (leaveType != null) {
								requestedLeave.setLeaveType(leaveType.getLeaveType());
							}
						}
						if (!(requestedLeave.getUserName() == null && requestedLeave.getLeaveStatus() == null
								&& requestedLeave.getLeaveType() == null && requestedLeave.getFromDate() == null
								&& requestedLeave.getToDate() == null)) {
							reporteeRequestedLeaveList.add(requestedLeave);
						}
					}
				}

//				if (!(requestedLeave.getUserName() == null && requestedLeave.getLeaveStatus() == null
//						&& requestedLeave.getLeaveType() == null && requestedLeave.getFromDate() == null
//						&& requestedLeave.getToDate() == null)) {
//					reporteeRequestedLeaveList.add(requestedLeave);
//				}
			}
		}

		response.setLeaveTypeBalance(leaveTypeBalanceList);
		response.setEmpAppliedLeave(empAppliedLeaveList);
		response.setReporteeRequestedLeave(reporteeRequestedLeaveList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
