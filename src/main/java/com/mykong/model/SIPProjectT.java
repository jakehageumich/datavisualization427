package com.mykong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "SIP_PROJECT_T")
@SequenceGenerator(name = "SIP_PROJECT_T_ID_S", sequenceName = "SIP_PROJECT_T_ID_S")
@Proxy(lazy = false)
public class SIPProjectT {
	@Id
	@GeneratedValue(generator = "SIP_PROJECT_T_ID_S", strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "TENANT")
	private String tenant;
	@Column(name = "RESOURCE_URI")
	private String resource_uri;
	@Column(name = "OWNER_ID")
	private int owner_id;
	@Column(name = "OWNER_EMAIL")
	private String owner_email;
	@Column(name = "STATUS")
	private String publish_status;
	@Column(name = "MAJOR_VERSION")
	private int major_version;
	@Column(name = "MINOR_VERSION")
	private int minor_version;
	@Column(name = "PATCH_VERSION")
	private int patch_version;
	@Column(name = "TOTAL_BYTES")
	private int total_bytes;
	@Column(name = "ACTIVE_IND")
	private String active_ind;
	@Column(name = "CREATED_TS")
	private String created_ts;
	@Column(name = "UPDATED_TS")
	private String updated_ts;
	@Column(name = "DISP_NAME")
	private String disp_name;
	@Column(name = "REF_OLD_ID")
	private int ref_old_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public int getMajor_version() {
		return major_version;
	}

	public void setMajor_version(int major_version) {
		this.major_version = major_version;
	}

	public int getMinor_version() {
		return minor_version;
	}

	public void setMinor_version(int minor_version) {
		this.minor_version = minor_version;
	}

	public int getPatch_version() {
		return patch_version;
	}

	public void setPatch_version(int patch_version) {
		this.patch_version = patch_version;
	}

	public int getTotal_bytes() {
		return getMinor_version() + getPatch_version();
	}
	
	public void setTotal_bytes(int total_bytes) {
		this.total_bytes = total_bytes;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getResource_uri() {
		return "/" + this.tenant + "/" + this.id;
	}
	
	public void setResource_uri(String resource_uri) {
		this.resource_uri = resource_uri;
	}

	public String getOwner_email() {
		return owner_email;
	}

	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}

	public String getCreated_ts() {
		return created_ts;
	}

	public void setCreated_ts(String created_ts) {
		this.created_ts = created_ts;
	}

	public String getUpdated_ts() {
		return updated_ts;
	}

	public void setUpdated_ts(String updated_ts) {
		this.updated_ts = updated_ts;
	}

	public String getDisp_name() {
		return disp_name;
	}

	public void setDisp_name(String disp_name) {
		this.disp_name = disp_name;
	}

	public String getStatus() {
		return publish_status;
	}

	public void setStatus(String in_publish_status) {
		this.publish_status = in_publish_status;
	}

	public String getActive_ind() {
		return active_ind;
	}

	public void setActive_ind(String in_active_ind) {
		this.active_ind = in_active_ind;
	}

	public int getRef_old_id() {
		return this.ref_old_id;
	}
	
	public void setRef_old_id(int ref_old_id) {
		this.ref_old_id = ref_old_id;
	}

	public String toString() {
		return "MemberSIPProjectT [id=" + id + ", tenant=" + tenant + ", resource_uri=" + resource_uri + ", owner_id="
				+ owner_id + ", owner_email=" + owner_email + ", publish_status=" + publish_status + ", major_version="
				+ major_version + ", minor_version=" + minor_version + ", patch_version=" + patch_version
				+ ", total_bytes=" + total_bytes + ", active_ind=" + active_ind + ", created_ts=" + created_ts
				+ ", updated_ts=" + updated_ts + ", disp_name=" + disp_name + ", ref_old_id=" + ref_old_id + "]";
	}

}
