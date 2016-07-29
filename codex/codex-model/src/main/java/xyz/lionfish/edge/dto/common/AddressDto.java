package xyz.lionfish.edge.dto.common;

import java.util.Date;

import xyz.lionfish.edge.model.type.AddressType;

/**
 * 
 * @author Kevendra Patidar
 */
public class AddressDto {

	/* ************************************ Instance Fields ************************************ */
	private Long id;
	private String street1;
	private String street2;
	private String landmark;
	private String city;
	private Integer pinZip;
	private String state;
	private String country;

	private String mobile1;
	private String mobile2;
	private String phone1;
	private String phone2;
	private String fax;
	private String emailId;
	private String web;
	private AddressType type;
	private Date created;

	/* ************************************ Public Methods ************************************ */
	@Override
	public String toString() {
		return "AddressVo [id=" + id + ", street1=" + street1 + ", street2="
				+ street2 + ", landmark=" + landmark + ", city=" + city
				+ ", pinZip=" + pinZip + ", state=" + state + ", country="
				+ country + ", mobile1=" + mobile1 + ", mobile2=" + mobile2
				+ ", phone1=" + phone1 + ", phone2=" + phone2 + ", fax=" + fax
				+ ", emailId=" + emailId + ", web=" + web + ", type=" + type
				+ ", created=" + created + "]";
	}

	/* ************************************ Getters and Setters ************************************ */
	public Long getId() {
		return this.id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
	public String getStreet1() {
		return this.street1;
	}
	public void setStreet1(final String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return this.street2;
	}
	public void setStreet2(final String street2) {
		this.street2 = street2;
	}
	public String getLandmark() {
		return this.landmark;
	}
	public void setLandmark(final String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(final String city) {
		this.city = city;
	}
	public Integer getPinZip() {
		return this.pinZip;
	}
	public void setPinZip(final Integer pinZip) {
		this.pinZip = pinZip;
	}
	public String getState() {
		return this.state;
	}
	public void setState(final String state) {
		this.state = state;
	}
	public String getCountry() {
		return this.country;
	}
	public void setCountry(final String country) {
		this.country = country;
	}
	public String getMobile1() {
		return this.mobile1;
	}
	public void setMobile1(final String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return this.mobile2;
	}
	public void setMobile2(final String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getPhone1() {
		return this.phone1;
	}
	public void setPhone1(final String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return this.phone2;
	}
	public void setPhone2(final String phone2) {
		this.phone2 = phone2;
	}
	public String getFax() {
		return this.fax;
	}
	public void setFax(final String fax) {
		this.fax = fax;
	}
	public String getEmailId() {
		return this.emailId;
	}
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}
	public String getWeb() {
		return this.web;
	}
	public void setWeb(final String web) {
		this.web = web;
	}
	public AddressType getType() {
		return this.type;
	}
	public void setType(final AddressType type) {
		this.type = type;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

}
