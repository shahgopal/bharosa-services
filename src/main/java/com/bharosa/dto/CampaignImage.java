package com.bharosa.dto;

import java.sql.Blob;


public class CampaignImage {

	protected long id;
	protected Campaign campaignData;
	protected Blob imageData;
	protected String contentType;
	protected  String name;
	protected String originalFilename;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Campaign getCampaignData() {
		return campaignData;
	}
	public void setCampaignData(Campaign campaignData) {
		this.campaignData = campaignData;
	}
	public Blob getImageData() {
		return imageData;
	}
	public void setImageData(Blob imageData) {
		this.imageData = imageData;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public static final class Builder {
		CampaignImage obj = null;

		public Builder() {
			this(null);
		}

		public Builder(CampaignImage src) {
			this.obj = src;
			if (obj == null) {
				this.obj = new CampaignImage();
			}
		}

		public Builder withId(long id) {
			this.obj.setId(id);
			return this;
		}
		public Builder withCampaign(Campaign campaign) {
			this.obj.setCampaignData(campaign);
			return this;
		}
		public Builder withImageData(Blob imageData) {
			this.obj.setImageData(imageData);
			return this;
		}
		public Builder withContentType(String contentType) {
			this.obj.setContentType(contentType);
			return this;
		}
		public Builder withName(String name) {
			this.obj.setName(name);
			return this;
		}

		public Builder withOriginalFilename(String originalFilename) {
			this.obj.setOriginalFilename(originalFilename);
			return this;
		}


		public CampaignImage build() {
			return this.obj;
		}

	}


    
    
}
