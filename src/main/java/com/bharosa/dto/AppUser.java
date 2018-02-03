package com.bharosa.dto;

public class AppUser  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String username;
    private String firstName;
    private String lastName;
	private String email;
    private String biography;



    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
    
    
	public static final class Builder {
		AppUser obj = null;

		public Builder() {
			this(null);
		}

		public Builder(AppUser src) {
			this.obj = src;
			if (obj == null) {
				this.obj = new AppUser();
			}
		}
		public Builder withName(String username) {
			this.obj.setUsername(username);
			return this;
		}
		public Builder withFirstName(String firstName) {
			this.obj.setFirstName(firstName);
			return this;
		}
		public Builder withLastName(String lastName) {
			this.obj.setLastName(lastName);
			return this;
		}
		public Builder withEmail(String email) {
			this.obj.setEmail(email);
			return this;
		}
		public AppUser build() {
			return this.obj;
		}

	}



}