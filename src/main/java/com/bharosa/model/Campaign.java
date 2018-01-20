package com.bharosa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
/**
 * Created by gshah on 7/31/17.
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;



@SqlResultSetMapping(
        name = "CampaignMapping",
        entities = @EntityResult(
                entityClass = Campaign.class,
                fields = {
                    @FieldResult(name = "id", column = "campaign_id"),
                    @FieldResult(name = "goal", column = "goal"),
                    @FieldResult(name = "reason", column = "reason"),
                    @FieldResult(name = "name", column = "name"),
//                    @FieldResult(name = "createdAt", column = "created_at"),
                    @FieldResult(name = "details", column = "details"),
                    @FieldResult(name = "image", column = "image"),
//                    @FieldResult(name = "updatedAt", column = "updated_at"),
                    @FieldResult(name = "video", column = "video")
                    
                    
                    
                    }))




@Entity
@NamedNativeQueries({
@NamedNativeQuery(name = "Campaign.findByMostLikedCampaign",
query="SELECT * FROM (select campaign_id, count(campaign_id) from campaign_supporters where likes is not NULL group by campaign_id order by count(campaign_id) desc) Campaign limit 3"//, resultClass=Object.class
//resultSetMapping="CampaignMapping"
),
@NamedNativeQuery(name = "Campaign.findByCampaignByMostComments",
query="SELECT * FROM (select campaign_id, count(campaign_id) from campaign_supporters  where comments is not null group by campaign_id order by count(campaign_id) desc) Campaign limit 3"//,resultClass=Object.class
//resultSetMapping="CampaignMapping"
),
@NamedNativeQuery(name = "Campaign.findByCampaignByPopularity",
query="SELECT * FROM (select campaign_id, count(campaign_id) from campaign_supporters  where campaign_id is not null group by campaign_id order by count(campaign_id) desc) Campaign limit 3"//,resultClass=Object.class
//resultSetMapping="CampaignMapping"
),
@NamedNativeQuery(name = "Campaign.findByMostPaymentRequest",
query="SELECT * FROM (select campaign_id, count(campaign_id) from payment_request where id is not null group by campaign_id order by count(campaign_id) desc) Campaign limit 3"//, resultClass=Object.class
//resultSetMapping="CampaignMapping"
)})



@NamedEntityGraphs({
@NamedEntityGraph(name = "Campaign.paymentRequests", 
attributeNodes = @NamedAttributeNode("paymentRequests")),
@NamedEntityGraph(name = "Campaign.paymentResponses", 
attributeNodes = @NamedAttributeNode("paymentResponses")),
@NamedEntityGraph(name = "Campaign.campaignImages", 
attributeNodes = @NamedAttributeNode("campaignImages")),
@NamedEntityGraph(name = "Campaign.campaignSupporters", 
attributeNodes = @NamedAttributeNode("campaignSupporters")),


})
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private  long id;
    @Column(name = "name")
    private  String name;
    @Column(name = "goal")
    private  int goal;
    @Column(name = "reason")
    private  String reason;
    @Column(name = "details")
    private  String details;
    @Column(name = "image")
    private  String image;
    @Column(name = "video")
    private  String video;
    @Column(name = "created_at")
    private  Date createdAt;
    @Column(name = "updated_at")
    private  Date updatedAt;
    @OneToMany(mappedBy = "campaign", fetch=FetchType.LAZY)
    private List<PaymentRequest> paymentRequests;
    @OneToMany(mappedBy = "campaign", fetch=FetchType.LAZY)
    private List<PaymentResponse> paymentResponses;
    @OneToMany(mappedBy = "campaign", fetch=FetchType.LAZY)
    private List<CampaignImage> campaignImages;
    @OneToMany(mappedBy = "campaign", fetch=FetchType.LAZY)
    private List<CampaignSupporters> campaignSupporters;

    
    
//    { "name":"Gopal's Campaign", "goal":"10000 USD", "reason":"illness", "details":"Plese Donate for the illness",  "image":"www.xyz.com/image","video":"www.youtube.com/video" }
    
    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getGoal() {
        return goal;
    }

    public String getReason() {
        return reason;
    }

    public String getDetails() {
        return details;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }


	public List<PaymentRequest> getPaymentRequests() {
		return paymentRequests;
	}


	public void setPaymentRequests(List<PaymentRequest> paymentRequests) {
		this.paymentRequests = paymentRequests;
	}


	public List<PaymentResponse> getPaymentResponses() {
		return paymentResponses;
	}


	public void setPaymentResponses(List<PaymentResponse> paymentResponses) {
		this.paymentResponses = paymentResponses;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setGoal(int goal) {
		this.goal = goal;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<CampaignImage> getCampaignImages() {
		return campaignImages;
	}


	public void setCampaignImages(List<CampaignImage> campaignImages) {
		this.campaignImages = campaignImages;
	}

	

	public List<CampaignSupporters> getCampaignSupporters() {
		return campaignSupporters;
	}


	public void setCampaignSupporters(List<CampaignSupporters> campaignSupporters) {
		this.campaignSupporters = campaignSupporters;
	}


	@Override
	public String toString() {
		return "Campaign [id=" + id + ", name=" + name + ", goal=" + goal + ", reason=" + reason + ", details="
				+ details + ", image=" + image + ", video=" + video + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", paymentRequests=" + paymentRequests + ", paymentResponses=" + paymentResponses
				+ ", campaignImages=" + campaignImages + "]";
	}
    

}
