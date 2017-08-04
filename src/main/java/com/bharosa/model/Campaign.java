package com.bharosa.model;

/**
 * Created by gshah on 7/31/17.
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private  String content;
    private  String name;
    private  String goal;
    private  String reason;
    private  String details;
    private  String image;
    private  String video;
    private  Date createdAt;
    private  Date updatedAt;
    @OneToMany(mappedBy = "campaign")
    private List<PaymentRequest> paymentRequests;

//    {"content":"content2",
//            "name":"name2",
//            "goal":"goal2",
//            "reason":"reason2",
//            "details":"details",
//            "image":"details",
//            "video":"details"}


    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
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

//    private final createdBy//: this.createdBy.view(full),


//    public Campaign(long id, String content) {
//        this.id = id;
//        this.content = content;
//    }
//    public long getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
}
