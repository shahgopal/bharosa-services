package com.bharosa.model;

import java.util.Date;

import javax.persistence.Column;
/**
 * Created by gshah on 7/31/17.
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "campaign_supporters")
public class CampaignSupporters {
    @Column(name = "campaign_supporters_id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private  String comment;
    private  String likes;
    @Column(name = "created_at")
    private  Date createdAt;
    @Column(name = "updated_at")
    private  Date updatedAt;

    
    
    
    @JsonBackReference
	@ManyToOne
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;
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
	public void setLike(String likes) {
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
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

    
	
    
    
    
//    { "name":"Gopal's Campaign", "goal":"10000 USD", "reason":"illness", "details":"Plese Donate for the illness",  "image":"www.xyz.com/image","video":"www.youtube.com/video" }
    


}
