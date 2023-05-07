package com.isfa.leave.response;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.leave.entities.EmpAppliedLeave;
import com.isfa.leave.entities.LeaveTypeBalance;
import com.isfa.leave.entities.ReporteeRequestedLeave;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@JsonInclude(Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class EmpLeaveDetailResponse extends BaseResponse<EmpLeaveDetailResponse> {
    private Long totalLeave;
    private Double leaveBalance;
    private Double usedLeave;
    private List<LeaveTypeBalance> leaveTypeBalance;
    private List<EmpAppliedLeave> empAppliedLeave;
    private List<ReporteeRequestedLeave> reporteeRequestedLeave;
    
}