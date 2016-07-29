package xyz.lionfish.edge.service.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.annotation.Unindex;

/**
 *
 * @author Kevendra Patidar
 */
@Entity
@Unindex
@Cache
public class Person {

	/* ************************************ Instance Fields ************************************ */
	@Id
	@GeneratedValue
	private Long id;
	@Index
	private String deviceId;

	@Index
	private String nameFirst;
	private String nameMiddle;
	private String nameLast;
//	@Past
//	private Date doB;
//	@Index
//	private Gender gender;
	@Index
	private Long isd;//International Subscriber Dialing
	@Index
	private Long mobile1;
	@Index
	private Long mobile2;

	private String blobKey;

	private Date modified;
	private Date created  = new Date();

	/* ************************************ Public Methods ************************************ */
	@OnSave
	public void onPersist() {
		this.modified = new Date();
	}

	/* ************************************ Getters and Setters ************************************ */
	public Long getId() {
		return this.id;
	}
	public void setId(final Long id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getNameFirst() {
		return nameFirst;
	}
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}
	public String getNameMiddle() {
		return nameMiddle;
	}
	public void setNameMiddle(String nameMiddle) {
		this.nameMiddle = nameMiddle;
	}
	public String getNameLast() {
		return nameLast;
	}
	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}
//	public Date getDoB() {
//		return doB;
//	}
//	public void setDoB(Date doB) {
//		this.doB = doB;
//	}
//	public Gender getGender() {
//		return gender;
//	}
//	public void setGender(Gender gender) {
//		this.gender = gender;
//	}
	public Long getIsd() {
		return isd;
	}
	public void setIsd(Long isd) {
		this.isd = isd;
	}
	public Long getMobile1() {
		return mobile1;
	}
	public void setMobile1(Long mobile1) {
		this.mobile1 = mobile1;
	}
	public Long getMobile2() {
		return mobile2;
	}
	public void setMobile2(Long mobile2) {
		this.mobile2 = mobile2;
	}
	public String getBlobKey() {
		return blobKey;
	}
	public void setBlobKey(String blobKey) {
		this.blobKey = blobKey;
	}
	public Date getModified() {
		return this.modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getCreated() {
		return this.created;
	}

}
