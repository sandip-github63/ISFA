
package com.isfa.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "tm_users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {

	public static final Logger logger = LoggerFactory.getLogger(User.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	@NotNull
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@Column(name = "reset_password_otp")
	private String resetPasswordOTP;

	@Column(name = "supervisor")
	private String supervisor;

	@Column(name = "company_id")
	private Long companyId;
	
	@Column(name = "designation")
	private String  designation;
	
	@Column(name = "mobile")
	private String  mobile;
	@Column(name = "i_Role")
	private String  iRole;
	@Column(name = "company_Name")
	private String  companyName;
	@Column(name = "store_id")
	private Long storeId=1L;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getiRole() {
		return iRole;
	}

	public void setiRole(String iRole) {
		this.iRole = iRole;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getResetPasswordOTP() {
		return resetPasswordOTP;
	}

	public void setResetPasswordOTP(String resetPasswordOTP) {
		this.resetPasswordOTP = resetPasswordOTP;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	  logger.info("User no-args cons executing");
  }

	public User(String username, String email, String password) {
		logger.info("Role parameterized cons executing");
		this.username = username;
		this.email = email;
		this.password = password;
		logger.info("Role parameterized cons completed");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", resetPasswordOTP=" + resetPasswordOTP + ", supervisor=" + supervisor + ", companyId=" + companyId
				+ ", roles=" + roles + "]";
	}
	
	 public User(String username, String email, String password, String supervisor,
             Long companyId, String designation, String mobile, String iRole) {
   this.username = username;
   this.email = email;
   this.password = password;
   this.supervisor = supervisor;
   this.companyId = companyId;
   this.designation = designation;
   this.mobile = mobile;
   this.iRole = iRole;
 }
}

