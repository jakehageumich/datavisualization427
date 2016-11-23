package com.mykong.model;

import java.util.ArrayList;

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
@SequenceGenerator(name = "SIP_PROJECT_T_TENANT_S", sequenceName = "SIP_PROJECT_T_TENANT_S")
@Proxy(lazy = false)
public class SIPProjectTCurrent {
	
	
	@Id
	@GeneratedValue(generator = "SIP_PROJECT_T_TENANT_S", strategy = GenerationType.AUTO)
	@Column(name="TENANT")
	private String tenant;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name = "COUNT(PROJ.ID)")
	private int count;
	public String getTenant() {
		return tenant;
	}
	
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
}
