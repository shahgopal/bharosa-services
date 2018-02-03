package com.bharosa.dto;

import java.util.Date;
import java.util.List;

import com.bharosa.dto.CampaignSupporters;
import com.bharosa.dto.AppUser;
import com.bharosa.dto.CampaignImage.Builder;
import com.bharosa.dto.Campaign;

public class CampaignSupporters {
	protected  long id;
	protected  String comment;
	protected  String likes;
	protected  Date createdAt;
	protected  Date updatedAt;
	protected AppUser appUser;
//	protected Campaign campaignData;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
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
//	public Campaign getCampaignData() {
//		return campaignData;
//	}
//	public void setCampaignData(Campaign campaignData) {
//		this.campaignData = campaignData;
//	}

	public AppUser getAppUser() {
		return appUser;
	}


	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
    
	public static final class Builder {

		
		
		
		CampaignSupporters obj = null;

		public Builder() {
			this(null);
		}

		public Builder(CampaignSupporters src) {
			this.obj = src;
			if (obj == null) {
				this.obj = new CampaignSupporters();
			}
		}
		

		public Builder withId(long id) {
			this.obj.setId(id);
			return this;
		}
		public Builder withComment(String comment) {
			this.obj.setComment(comment);
			return this;
		}
		public Builder withLikes(String likes) {
			this.obj.setLikes(likes);
			return this;
		}
//		public Builder withCampaign(Campaign campaign) {
//			this.obj.setCampaignData(campaign);
//			return this;
//		}
		public Builder withCreatedAt(Date createdAt) {
			this.obj.setCreatedAt(createdAt);
			return this;
		}
		public Builder withUpdatedAt(Date updatedAt) {
			this.obj.setUpdatedAt(updatedAt);
			return this;
		}
		public Builder withUser(AppUser appUser) {
			this.obj.setAppUser(appUser);
			return this;
		}

		public CampaignSupporters build() {
			return this.obj;
		}

	}

    
    
    
//    { "name":"Gopal's Campaign", "goal":"10000 USD", "reason":"illness", "details":"Plese Donate for the illness",  "image":"www.xyz.com/image","video":"www.youtube.com/video" }
    


}
