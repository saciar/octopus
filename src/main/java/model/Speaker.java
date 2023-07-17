package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Speaker {
	
	private int id;
	private String name;
	private String role;
	private String email;
	private String picture;
	private String bio;
	private String sponsorName;
	private String sponsorImage;
	private int idPresenation;
	private String namePresentation;
	private LocalDateTime update;
	private boolean isVirtual;
	private String extension;
	private String title;
	private String facebookLink;
	private String instagramLink;
	private String youtubeLink;
	private String linkedinLink;
	private String twitterLink;
	private String qr;
	
	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getInstagramLink() {
		return instagramLink;
	}

	public void setInstagramLink(String instagramLink) {
		this.instagramLink = instagramLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public String getLinkedinLink() {
		return linkedinLink;
	}

	public void setLinkedinLink(String linkedinLink) {
		this.linkedinLink = linkedinLink;
	}
	
	public String getTwitterLink() {
		return twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}

	public Speaker(int id, String name, String role, String email, String picture, String bio, String sponsorName,
			String sponsorImage, int idPresenation, String namePresentation, LocalDateTime update, boolean isVirtual,
			String extension, String title, String facebookLink, String instagramLink, String youtubeLink,
			String linkedinLink) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.email = email;
		this.picture = picture;
		this.bio = bio;
		this.sponsorName = sponsorName;
		this.sponsorImage = sponsorImage;
		this.idPresenation = idPresenation;
		this.namePresentation = namePresentation;
		this.update = update;
		this.isVirtual = isVirtual;
		this.extension = extension;
		this.title = title;
		this.facebookLink = facebookLink;
		this.instagramLink = instagramLink;
		this.youtubeLink = youtubeLink;
		this.linkedinLink = linkedinLink;
	}

	public Speaker() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getSponsorImage() {
		return sponsorImage;
	}
	public void setSponsorImage(String sponsorImage) {
		this.sponsorImage = sponsorImage;
	}
	public int getIdPresenation() {
		return idPresenation;
	}
	public void setIdPresenation(int idPresenation) {
		this.idPresenation = idPresenation;
	}
	public String getNamePresentation() {
		return namePresentation;
	}
	public void setNamePresentation(String namePresentation) {
		this.namePresentation = namePresentation;
	}
	public LocalDateTime getUpdate() {
		return update;
	}
	public void setUpdate(LocalDateTime update) {
		this.update = update;
	}
	public boolean isVirtual() {
		return isVirtual;
	}
	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return name;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

}
