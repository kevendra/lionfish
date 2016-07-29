package xyz.lionfish.edge.dto.common;

import java.util.Date;

/**
 *
 * @author Kevendra Patidar
 */
public class PersonDto {

	/* ************************************ Instance Fields ************************************ */
	private Long id;
	private String deviceId;
	private String nameFirst;
	private String nameMiddle;
	private String nameLast;
	private Long isd;//International Subscriber Dialing
	private Long mobile1;
	private Long mobile2;

	private String blobKey;

	private Date modified;
	private Date created;

	/* ************************************ Public Methods ************************************ */
	@Override
	public String toString() {
		return "PersonVo [id=" + id + ", deviceId=" + deviceId + ", nameFirst="
				+ nameFirst + ", nameMiddle=" + nameMiddle + ", nameLast="
				+ nameLast + ", isd=" + isd + ", mobile1=" + mobile1
				+ ", mobile2=" + mobile2 + ", blobKey=" + blobKey
				+ ", modified=" + modified + ", created=" + created + "]";
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
	public void setModified(final Date modified) {
		this.modified = modified;
	}
	public Date getCreated() {
		return this.created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
