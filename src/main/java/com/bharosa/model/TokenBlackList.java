package com.bharosa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity

@Table(name = "token_black_list")
public class TokenBlackList {

    @Id
	@Column(name = "jti")    
    private String jti;
    
	@Column(name = "user_id")    
    private Long userId;
    
	@Column(name = "expires")    
    private Long expires;
    
	@Column(name = "is_black_listed")    
    private Boolean isBlackListed;

    public TokenBlackList() {
    }

    public TokenBlackList(Long userId, String jti, Long expires) {
        this.jti = jti;
        this.userId = userId;
        this.expires = expires;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public boolean isBlackListed() {
        return isBlackListed;
    }

    public void setBlackListed(boolean blackListed) {
        isBlackListed = blackListed;
    }
}