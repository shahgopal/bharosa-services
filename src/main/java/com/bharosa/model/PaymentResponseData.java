package com.bharosa.model;
/**
 * Created by gshah on 7/31/17.
 */

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author gshah Sample Content // "goal":"goal2", "reason":"reason2",
 *         "details":"details", // "image":"details","video":"details"}
 *         {"campaign_id":1, "orderId":"123", "txnAmount":"1234" }
 * 
 */

@Entity
@Table(name = "payment_response")
public class PaymentResponseData {

    @Column(name = "payment_response_id")
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "campaign_id")
	private CampaignData campaignData;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "payment_request_id" )
	private PaymentRequestData paymentRequestData;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
	

	
//	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
	private UUID orderId;

    @Column(name = "txn_amount")
    private BigDecimal txnAmount;

    @Column(name = "transaction_id")
	private String transactionId;
	
    @Column(name = "bank_transaction_id")
	private String bankTransactionId;

    @Column(name = "currency")
	private String currency;

    @Column(name = "status")
    private String status;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "customer_id")
	private String customerId;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "order_details")
	private String orderDetails;

    @Column(name = "response_message")
	private String responseMessage;

    @Column(name = "transaction_date")
	private Date transactionDate;

    @Column(name = "transaction_type")
	private String transactionType;

    @Column(name = "gate_way_name")
	private String gateWayName;

    @Column(name = "bank_name")
	private String bankName;

    @Column(name = "payment_mode")
	private String paymentMode;

    @Column(name = "created_at")
	private Date createdAt;

    @Column(name = "updated_at")
	private Date updatedAt;

    @Column(name = "checksum_hash")
	private String checksumHash;

    @Column(name = "is_validchecksum_hash")
	private Boolean isValidchecksumHash;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CampaignData getCampaignData() {
		return campaignData;
	}
	public void setCampaign(CampaignData campaignData) {
		this.campaignData = campaignData;
	}
	public PaymentRequestData getPaymentRequestData() {
		return paymentRequestData;
	}
	public void setPaymentRequestData(PaymentRequestData paymentRequestData) {
		this.paymentRequestData = paymentRequestData;
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
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getBankTransactionId() {
		return bankTransactionId;
	}
	public void setBankTransactionId(String bankTransactionId) {
		this.bankTransactionId = bankTransactionId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
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
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getGateWayName() {
		return gateWayName;
	}
	public void setGateWayName(String gateWayName) {
		this.gateWayName = gateWayName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getChecksumHash() {
		return checksumHash;
	}
	public void setChecksumHash(String checksumHash) {
		this.checksumHash = checksumHash;
	}
	public Boolean getIsValidchecksumHash() {
		return isValidchecksumHash;
	}
	public void setIsValidchecksumHash(Boolean isValidchecksumHash) {
		this.isValidchecksumHash = isValidchecksumHash;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
//	{ "campaign_id":1, "paymentRequest_id":1,"txnAmount":"100","transactionId":"transaction1","bankTransactionId":"bankTransaction1","currency":"USD","status":"SUCCESS", "customerId":"customer2", "mobileNo":"831-214-6646",  "emailId":"timepass2@gmail.com","checksumHash":"not shared"}
//	@ManyToOne
//	@JoinColumn(name = "name")
//	private User user;


}





//index=
//lcapp host="uw2-data-verification-*"source="*application*""Received BizOp event for dispatching:"|
//timechart span=1d
//count as INCOMING_EVENT|appendcols[
//search host="uw2-data-verification-*"source="*application*""Method successfully handle BizOp event"|
//timechart span=1d
//count as SUCCESS_EVENT]|appendcols[
//search host="uw2-data-verification-*"source="*application*""Running W2 verification"OR"Running Paystub verification"|
//timechart span=1d
//count as OCR_START]|appendcols[
//search host="uw2-data-verification-*"source="*application*""Run Paystub verification success*"OR"Run W2 verification success*"|
//timechart span=1d
//count as OCR_COMPLETE]