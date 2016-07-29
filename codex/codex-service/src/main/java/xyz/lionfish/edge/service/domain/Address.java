package xyz.lionfish.edge.service.domain;


import java.util.Date;

import javax.persistence.GeneratedValue;

import xyz.lionfish.edge.model.type.AddressType;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Unindex;

/**
 * <p>Address entity.</p>
 * @author Kevendra Patidar <kevendra.patidar@gmail.com>
 *
 */
@Entity
@Unindex
@Cache
public class Address {

	/* ************************************ Instance Fields ************************************ */
	@Id
	@GeneratedValue
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
	private boolean sameAddress = true;

	private Date created = new Date();


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
	public AddressType getType() {
		return this.type;
	}
	public String getWeb() {
		return this.web;
	}
	public void setWeb(final String web) {
		this.web = web;
	}
	public void setType(final AddressType type) {
		this.type = type;
	}
	public boolean isSameAddress() {
		return this.sameAddress;
	}
	public void setSameAddress(final boolean sameAddress) {
		this.sameAddress = sameAddress;
	}
	public Date getCreated() {
		return this.created;
	}

}