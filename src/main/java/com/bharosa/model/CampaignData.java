package com.bharosa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
/**
 * Created by gshah on 7/31/17.
 */
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;



@SqlResultSetMapping(
        name = "CampaignDataMapping",
        entities = @EntityResult(
                entityClass = CampaignData.class,
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
@NamedNativeQuery(name = "CampaignData.findByMostLikedCampaignData",
query="SELECT * FROM (select campaign_id, count(campaign_id) from campaign_supporters where likes is not NULL group by campaign_id order by count(campaign_id) desc) CampaignData limit 3"//, resultClass=Object.class
//resultSetMapping="CampaignDataMapping"
),
@NamedNativeQuery(name = "CampaignData.findByCampaignDataByMostComments",
query="SELECT * FROM (select campaign_id, count(campaign_id) from campaign_supporters  where comments is not null group by campaign_id order by count(campaign_id) desc) CampaignData limit 3"//,resultClass=Object.class
//resultSetMapping="CampaignDataMapping"
),
@NamedNativeQuery(name = "CampaignData.findByCampaignDataByPopularity",
query="SELECT * FROM (select campaign_id, count(campaign_id) from campaign_supporters  where campaign_id is not null group by campaign_id order by count(campaign_id) desc) CampaignData limit 3"//,resultClass=Object.class
//resultSetMapping="CampaignDataMapping"
),
@NamedNativeQuery(name = "CampaignData.findByMostPaymentRequestData",
query="SELECT * FROM (select campaign_id, count(campaign_id) from payment_request where id is not null group by campaign_id order by count(campaign_id) desc) CampaignData limit 3"//, resultClass=Object.class
//resultSetMapping="CampaignDataMapping"
)})



@NamedEntityGraphs({
@NamedEntityGraph(name = "CampaignData.paymentRequestsData", 
attributeNodes = @NamedAttributeNode("paymentRequestsData")),
@NamedEntityGraph(name = "CampaignData.paymentResponsesData", 
attributeNodes = @NamedAttributeNode("paymentResponsesData")),
@NamedEntityGraph(name = "CampaignData.campaignImagesData", 
attributeNodes = @NamedAttributeNode("campaignImagesData")),
@NamedEntityGraph(name = "CampaignData.campaignSupportersData", 
attributeNodes = @NamedAttributeNode("campaignSupportersData")),


})
@Table(name = "campaign_data")
public class CampaignData {
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
    @OneToMany(mappedBy = "campaignData", fetch=FetchType.LAZY)
    private List<PaymentRequestData> paymentRequestsData;
    @OneToMany(mappedBy = "campaignData", fetch=FetchType.LAZY)
    private List<PaymentResponseData> paymentResponsesData;
    @OneToMany(mappedBy = "campaignData", fetch=FetchType.LAZY)
    private List<CampaignImageData> campaignImagesData;
    @OneToMany(mappedBy = "campaignData", fetch=FetchType.LAZY)
    private List<CampaignSupportersData> campaignSupportersData;
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    
    
//    { "name":"Gopal's CampaignData", "goal":"10000 USD", "reason":"illness", "details":"Plese Donate for the illness",  "image":"www.xyz.com/image","video":"www.youtube.com/video" }
    
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


	public List<PaymentRequestData> getPaymentRequestsData() {
		return paymentRequestsData;
	}


	public void setPaymentRequestsData(List<PaymentRequestData> paymentRequestsData) {
		this.paymentRequestsData = paymentRequestsData;
	}


	public List<PaymentResponseData> getPaymentResponsesData() {
		return paymentResponsesData;
	}


	public void setPaymentResponsesData(List<PaymentResponseData> paymentResponsesData) {
		this.paymentResponsesData = paymentResponsesData;
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


	public List<CampaignImageData> getCampaignImagesData() {
		return campaignImagesData;
	}


	public void setCampaignDataImages(List<CampaignImageData> campaignImagesData) {
		this.campaignImagesData = campaignImagesData;
	}

	

	public List<CampaignSupportersData> getCampaignSupportersData() {
		return campaignSupportersData;
	}


	public void setCampaignSupportersData(List<CampaignSupportersData> campaignSupportersData) {
		this.campaignSupportersData = campaignSupportersData;
	}


	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "CampaignData [id=" + id + ", name=" + name + ", goal=" + goal + ", reason=" + reason + ", details="
				+ details + ", image=" + image + ", video=" + video + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", paymentRequests=" + paymentRequestsData + ", paymentResponses=" + paymentResponsesData
				+ ", CampaignImageData=" + campaignImagesData + "]";
	}
    

}
