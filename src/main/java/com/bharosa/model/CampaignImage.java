package com.bharosa.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "campaign_image")
public class CampaignImage {
    @Column(name = "campaign_image_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;
    @Column(name = "image_data")
    private Blob imageData;

    @Column(name = "content_type")
    private String contentType;

    private  String name;
    @Column(name = "original_filename")
	private String originalFilename;

    
    
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


    
    
}
