package com.isfa.promoter.entities;



import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_detail")
public class StoreDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cluster_id")
    private Long clusterId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_code")
    private String storeCode;

    @Column(name = "store_name")
    private String storeName;

    @Column(name="address")
    private String address;

    @Column(name = "store_category")
    private String storeCategory;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @Column(name = "activestatus")
    private Boolean activeStatus;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "store_image")
    private String storeImage1;

    @Column(name = "store_branch")
    private String storeBranch;

    @Column(name = "store_type")
    private String storeType;

    @Column(name="location")
    private String location;

    @Column(name = "phoneno")
    private String phoneNo;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDateTime updatedDate;

    @Column(name = "modified_by")
    private String updatedBy;

    @Column(name="zipcode")
    private Long zipcode;

    @Column(name = "flg_user_zipcode")
    private Boolean flgUserZipcode;

    @Column(name = "contact_name")
    private String contactName;

    
}
