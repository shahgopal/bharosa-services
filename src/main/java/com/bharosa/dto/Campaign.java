package com.bharosa.dto;

import java.util.Date;
import java.util.List;


public class Campaign {
	protected  long id;
	protected  String name;
	protected  int goal;
	protected  String reason;
	protected  String details;
	protected  String image;
	protected  String video;
	protected  Date createdAt;
	protected  Date updatedAt;
	protected List<PaymentRequest> paymentRequestsData;
	protected List<PaymentResponse> paymentResponsesData;
	protected List<CampaignImage> campaignImagesData;
	protected List<CampaignSupporters> campaignSupportersData;
	protected AppUser user;
	protected int achievedGoal;
	protected String status;
    
//    { "name":"Gopal's Campaign", "goal":"10000 USD", "reason":"illness", "details":"Plese Donate for the illness",  "image":"www.xyz.com/image","video":"www.youtube.com/video" }
    

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getGoal() {
		return goal;
	}


	public void setGoal(int goal) {
		this.goal = goal;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
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






	public List<PaymentRequest> getPaymentRequestsData() {
		return paymentRequestsData;
	}


	public void setPaymentRequestsData(List<PaymentRequest> paymentRequestsData) {
		this.paymentRequestsData = paymentRequestsData;
	}


	public List<PaymentResponse> getPaymentResponsesData() {
		return paymentResponsesData;
	}


	public void setPaymentResponsesData(List<PaymentResponse> paymentResponsesData) {
		this.paymentResponsesData = paymentResponsesData;
	}


	public List<CampaignImage> getCampaignImagesData() {
		return campaignImagesData;
	}


	public void setCampaignImagesData(List<CampaignImage> campaignImagesData) {
		this.campaignImagesData = campaignImagesData;
	}


	public List<CampaignSupporters> getCampaignSupportersData() {
		return campaignSupportersData;
	}


	public void setCampaignSupportersData(List<CampaignSupporters> campaignSupportersData) {
		this.campaignSupportersData = campaignSupportersData;
	}


	public AppUser getUser() {
		return user;
	}


	public int getAchievedGoal() {
		return achievedGoal;
	}


	public void setAchievedGoal(int achievedGoal) {
		this.achievedGoal = achievedGoal;
	}


	public void setUser(AppUser user) {
		this.user = user;
	}






	public static final class Builder {
		Campaign obj = null;

		public Builder() {
			this(null);
		}

		public Builder(Campaign src) {
			this.obj = src;
			if (obj == null) {
				this.obj = new Campaign();
			}
		}

		public Builder withId(long id) {
			this.obj.setId(id);
			return this;
		}

		public Builder withName(String name) {
			this.obj.setName(name);
			return this;
		}
		public Builder withReason(String reason) {
			this.obj.setReason(reason);
			return this;
		}
		public Builder withDetails(String details) {
			this.obj.setDetails(details);
			return this;
		}
		public Builder withImage(String image) {
			this.obj.setImage(image);
			return this;
		}
		public Builder withVideo(String video) {
			this.obj.setVideo(video);
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.obj.setCreatedAt(createdAt);
			return this;
		}
		public Builder withUpdatedAt(Date updatedAt) {
			this.obj.setUpdatedAt(updatedAt);
			return this;
		}
		public Builder withPaymentRequests(List<PaymentRequest> paymentRequests) {
			this.obj.setPaymentRequestsData(paymentRequests);
			return this;
		}
		public Builder withPaymentResponses(List<PaymentResponse> paymentResponses) {
			this.obj.setPaymentResponsesData(paymentResponses);
			return this;
		}
		public Builder withCampaignImages(List<CampaignImage> campaignImages) {
			this.obj.setCampaignImagesData(campaignImages);
			return this;
		}
		public Builder withCampaignSupporters(List<CampaignSupporters> campaignSupporters) {
			this.obj.setCampaignSupportersData(campaignSupporters);
			return this;
		}
		public Builder withAppUser(AppUser appUser) {
			this.obj.setUser(appUser);
			return this;
		}

		public Campaign build() {
			return this.obj;
		}

		
		
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", name=" + name + ", goal=" + goal + ", reason=" + reason + ", details="
				+ details + ", image=" + image + ", video=" + video + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", paymentRequests=" + paymentRequestsData + ", paymentResponses=" + paymentResponsesData
				+ ", campaignImages=" + campaignImagesData + "]";
	}
    

}
