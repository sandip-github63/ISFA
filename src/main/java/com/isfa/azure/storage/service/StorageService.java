//package com.isfa.azure.storage.service;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.annotation.PostConstruct;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.azure.storage.blob.BlobClient;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobServiceClient;
//import com.azure.storage.blob.BlobServiceClientBuilder;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Component
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class StorageService {
//	
//	private static final Logger logger = LoggerFactory.getLogger(StorageService.class);
//
//  private BlobServiceClient blobServiceClient;
//
//  @Value("${isfa.myConnectionString}")
//  private String connectionString;
//  
//  @Value("${isfa.containerName}")
//  private String containerName;
//  
//  @Value("${hekki}")
//  private Long hikki;
//  
//  
//  @PostConstruct
//  public void init() {
//	  logger.info("Initializing StorageService...");
//	     System.out.println("\n\n\n"+hikki);
//	     System.out.println("connectionString = " + connectionString);
//	    blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
//	  
//  }
//  
//  
//  public String uploadImage(String imageName, InputStream imageStream) throws IOException {
//    BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
//
//    if (!containerClient.exists()) {
//        containerClient.create();
//    }
//    
//    BlobClient blobClient = containerClient.getBlobClient(imageName);
//    blobClient.upload(imageStream, imageStream.available());
//    return blobClient.getBlobUrl();
//  }
//
//}
