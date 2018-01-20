package com.bharosa.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by gshah on 7/31/17.
 */
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author gshah Sample Content { "goal":"goal2", "reason":"reason2",
 *         "details":"details",  "image":"details","video":"details"}
 *         {"campaign_id":1, "orderId":"123", "txnAmount":"1234" }
 * 
 */

@Entity
@Table(name = "payment_request")
public class PaymentRequest {

    @Column(name = "payment_request_id")
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;


	
	
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "order_id")
	private UUID orderId;
    @Column(name = "txn_amount")
	private BigDecimal txnAmount;
    @Column(name = "customer_id")
	private String customerId;
    @Column(name = "mobile_no")
	private String mobileNo;
    @Column(name = "email_id")
	private String emailId;
    @Column(name = "order_details")
	private String orderDetails;
    @Column(name = "checksum_hash")
	private String checksumHash;
    @Column(name = "created_at")
	private Date createdAt;
    @Column(name = "updated_at")
	private Date updatedAt;
    @Column(name = "callback_url")
	private String callbackUrl;
    @Column(name = "channel_id")
	private String channelId;
    @Column(name = "industry_type_id")
	private String industryTypeId;
	private String mid;
	private String website;
	

	
	
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
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
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
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getIndustryTypeId() {
		return industryTypeId;
	}
	public void setIndustryTypeId(String industryTypeId) {
		this.industryTypeId = industryTypeId;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	@Override
	public String toString() {
		return "PaymentRequest [id=" + id + ", campaign=" + campaign + ", orderId=" + orderId + ", txnAmount="
				+ txnAmount + ", customerId=" + customerId + ", mobileNo=" + mobileNo + ", emailId=" + emailId
				+ ", orderDetails=" + orderDetails + ", checksumHash=" + checksumHash + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", callbackUrl=" + callbackUrl + ", channelId=" + channelId
				+ ", industryTypeId=" + industryTypeId + ", mid=" + mid + ", website=" + website + "]";
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
