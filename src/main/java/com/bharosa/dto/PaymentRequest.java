package com.bharosa.dto;

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
import javax.persistence.Table;

/**
 * Created by gshah on 7/31/17.
 */
import com.fasterxml.jackson.annotation.JsonBackReference;

public class PaymentRequest {

    @Id
    protected long id;
//    protected Campaign campaign;
    protected AppUser appUser;
    protected UUID orderId;
    protected BigDecimal txnAmount;
    protected String customerId;
    protected String mobileNo;
    protected String emailId;
    protected String orderDetails;
    protected String checksumHash;
    protected Date createdAt;
    protected Date updatedAt;
    protected String callbackUrl;
    protected String channelId;
    protected String industryTypeId;
    protected String mid;
    protected String website;
	

	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
//	public Campaign getCampaign() {
//		return campaign;
//	}
//	public void setCampaign(Campaign campaign) {
//		this.campaign = campaign;
//	}
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
		return "PaymentRequest [id=" + id + ", campaignData="  + ", orderId=" + orderId + ", txnAmount="
				+ txnAmount + ", customerId=" + customerId + ", mobileNo=" + mobileNo + ", emailId=" + emailId
				+ ", orderDetails=" + orderDetails + ", checksumHash=" + checksumHash + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", callbackUrl=" + callbackUrl + ", channelId=" + channelId
				+ ", industryTypeId=" + industryTypeId + ", mid=" + mid + ", website=" + website + "]";
	}
	
	

	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}


}
