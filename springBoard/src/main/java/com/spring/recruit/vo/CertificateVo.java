package com.spring.recruit.vo;

public class CertificateVo {

	private String certificateSeq;   // EDU_SEQ 필드
    private String seq;
	private String qualifiName;
	private String acquDate;
	private String organizeName;

	public String getCertificateSeq() {
		return certificateSeq;
	}
	public void setCertificateSeq(String certificateSeq) {
		this.certificateSeq = certificateSeq;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getQualifiName() {
		return qualifiName;
	}
	public void setQualifiName(String qualifiName) {
		this.qualifiName = qualifiName;
	}
	public String getAcquDate() {
		return acquDate;
	}
	public void setAcquDate(String acquDate) {
		this.acquDate = acquDate;
	}
	public String getOrganizeName() {
		return organizeName;
	}
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}
	
	
}
