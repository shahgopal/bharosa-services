//package com.bharosa.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
////
//@Entity
//@Table(name = "app_user_roles")
//public class AppUserRole  {
//
//	@ManyToOne
//	@JoinColumn(name = "app_user_id")
//	private AppUser appUser;
//	
//	
//    @Column(name = "roles")
//    private String roles;
////
//    /**
//     * Roles are being eagerly loaded here because
//     * they are a fairly small collection of items for this example.
//     */
//
//
//	public AppUser getAppUser() {
//		return appUser;
//	}
//
//
//	public void setAppUser(AppUser appUser) {
//		this.appUser = appUser;
//	}
//
//
//	public String getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}
//
//
//
//
//	
//
//}