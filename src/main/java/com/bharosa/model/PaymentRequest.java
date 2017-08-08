package com.bharosa.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by gshah on 7/31/17.
 */
import com.bharosa.security.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author gshah Sample Content { "goal":"goal2", "reason":"reason2",
 *         "details":"details",  "image":"details","video":"details"}
 *         {"campaign_id":1, "orderId":"123", "txnAmount":"1234" }
 * 
 */

@Entity
public class PaymentRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	private BigDecimal txnAmount;
	private String customerId;
	private String mobileNo;
	private String emailId;
	private String orderDetails;
	private String checksumHash;
	private Date createdAt;
	private Date updatedAt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getChecksumHash() {
		return checksumHash;
	}
	public void setChecksumHash(String checksumHash) {
		this.checksumHash = checksumHash;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

//	{ "campaign_id":1, "txnAmount":"200", "customerId":"customer2", "mobileNo":"831-214-6646",  "emailId":"timepass1@gmail.com","checksumHash":"not shared"}
	
	
//	@ManyToOne
//	@JoinColumn(name = "name")
//	private User user;

	// private String transactionId;
	// private String bankTransactionId;
	// private String currency;
	// private String status;
	// private String responseCode;
	// private String responseMessage;
	// private Date transactionDate;
	// private String gateWayName;
	// private String bankName;
	// private String paymentMode;



}
