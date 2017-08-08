package com.bharosa.model;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@NamedEntityGraphs({
@NamedEntityGraph(name = "Campaign.paymentRequests", 
attributeNodes = @NamedAttributeNode("paymentRequests")),
@NamedEntityGraph(name = "Campaign.paymentResponse", 
attributeNodes = @NamedAttributeNode("paymentRequests")),

})
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private  String name;
    private  String goal;
    private  String reason;
    private  String details;
    private  String image;
    private  String video;
    private  Date createdAt;
    private  Date updatedAt;
    @OneToMany(mappedBy = "campaign", fetch=FetchType.LAZY)
    private List<PaymentRequest> paymentRequests;
    
    @OneToMany(mappedBy = "campaign", fetch=FetchType.LAZY)
    private List<PaymentResponse> paymentResponses;

    
//    { "name":"Gopal's Campaign", "goal":"10000 USD", "reason":"illness", "details":"Plese Donate for the illness",  "image":"www.xyz.com/image","video":"www.youtube.com/video" }
    
    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getGoal() {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }


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


	public void setGoal(String goal) {
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
    

}
