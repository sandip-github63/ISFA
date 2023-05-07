package com.isfa.promoter.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.isfa.models.User;
import com.isfa.promoter.dao.FeedbackPurposeRepository;
import com.isfa.promoter.dao.FeedbackRepository;
import com.isfa.promoter.entities.Feedback;
import com.isfa.promoter.entities.FeedbackPurpose;
import com.isfa.promoter.model.FeedbackRequest;
import com.isfa.promoter.response.BaseResponse;
import com.isfa.promoter.response.FeedbackAdminResponse;
import com.isfa.promoter.response.FeedbackPurposeResponse;
import com.isfa.promoter.response.FeedbackResponse;
import com.isfa.repository.UserRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	public static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Value("${project.image}")
	private String path;

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private FeedbackPurposeRepository feedbackPurposeRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<?> submitFeedback(Long userId, FeedbackRequest request, MultipartFile image)
			throws IOException {

		Feedback feedback = FeedbackRequest.convertInto(request);

		// image name
		String name = image.getOriginalFilename();
		logger.info("File name executed");

		// Full path
		String randID = UUID.randomUUID().toString().concat(LocalDateTime.now().toString());
		String originalFileName = (randID.concat(name.substring(name.lastIndexOf("."))).replace(":", "_"));

		String filePath = path + File.separator + originalFileName;

		logger.info("File full path executed");

		// create folder if not exists
		File f = new File(path);
		if (!f.exists()) {
			logger.info("Make directory");
			f.mkdir();
		}

		// file copy
		Files.copy(image.getInputStream(), Paths.get(filePath));
		logger.info("File copied");
		feedback.setImageUrl(originalFileName);

		User user = userRepository.findById(userId).orElse(null);

		if (user != null) {
			feedback.setCreatedBy(user.getUsername());
			feedback.setModifiedBy(user.getUsername());
		}

		LocalDateTime now = LocalDateTime.now();
		feedback.setCreatedDate(now);
		feedback.setModifiedDate(now);

		logger.info("LeaveType Repository Saving New LeaveType executing");
		System.out.println(feedback);

		feedback = feedbackRepository.save(feedback);

		logger.info("LeaveType Repository Saving New LeaveType executing");
		BaseResponse<FeedbackResponse> response = FeedbackResponse.convert(feedback);
		response.setMessage("Successfully Inserted");
		response.setStatus("200");
		logger.info("LeaveType Service Saving New LeaveType completed");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getPurposes() {
		List<FeedbackPurpose> purposeList = feedbackPurposeRepository.findAll();
		BaseResponse<FeedbackPurposeResponse> response = FeedbackPurposeResponse.convertList(purposeList);

		if (!response.getDataList().isEmpty()) {
			response.setMessage("No records to Fetch");
			response.setStatus("200");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setMessage("Record Successfully Fetched");
		response.setStatus("200");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllFeedback() {

		List<Feedback> feedbackList = feedbackRepository.findAll();
		List<FeedbackAdminResponse> respList = new ArrayList<>();
		BaseResponse<FeedbackAdminResponse> bResp = new BaseResponse<>();

		if (feedbackList.isEmpty()) {
			bResp.setMessage("No records to Fetch");
			bResp.setStatus("200");
			bResp.setDataList(respList);
			return new ResponseEntity<>(bResp, HttpStatus.OK);
		}

		for (Feedback feedback : feedbackList) {

			FeedbackAdminResponse admin = new FeedbackAdminResponse();

			admin.setImage(feedback.getImageUrl());
			admin.setCreatedDate(feedback.getModifiedDate().toString());
			admin.setReason(feedback.getReason());
			admin.setUserName(feedback.getModifiedBy());

			if (feedback.getPurposeId() != null) {
				FeedbackPurpose purpose = feedbackPurposeRepository.findById(feedback.getPurposeId()).orElse(null);
				if (purpose != null)
					admin.setPurpose(purpose.getName());
			}

			if (!(admin.getCreatedDate() == null && admin.getImage() == null && admin.getPurpose() == null
					&& admin.getReason() == null && admin.getUserName() == null))
				respList.add(admin);
		}
		
		bResp.setMessage("Records successfully Fetched");
		bResp.setStatus("200");
		bResp.setDataList(respList);
		return new ResponseEntity<>(bResp, HttpStatus.OK);
	}

}
