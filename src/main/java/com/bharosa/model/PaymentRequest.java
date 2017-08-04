package com.bharosa.model;
/**
 * Created by gshah on 7/31/17.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gshah on 8/1/17.
 */
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class PaymentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @ManyToOne
    @JoinColumn(name="campaign_id")
    private Campaign campaign;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private BigDecimal txnAmount;
    private  Date createdAt;
    private  Date updatedAt;


    //    {"content":"content2",
//            "name":"name2",
//            "goal":"goal2",
//            "reason":"reason2",
//            "details":"details",
//            "image":"details",
//            "video":"details"}


    //    {"campaign_id":1,
//            "orderId":"123",
//            "txnAmount":"1234"
//}


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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal gettxnAmount() {
        return txnAmount;
    }

    public void txnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
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
}
