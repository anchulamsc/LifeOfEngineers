package com.loe.dms.spring.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounting_info")
public class AccountingInfo extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_id")
	private String account_id;

	@Column(name = "account_number")
	private String account_number;

	@Column(name = "routing_number")
	private String routing_number;

	@Column(name = "account_name")
	private String account_name;

	@Column(name = "account_type")
	private String account_type;

	@Column(name = "bank_name")
	private String bank_name;

	@Column(name = "ifsc_cd")
	private String ifsc_cd;

	@Column(name = "branch_name")
	private String branch_name;

	@Column(name = "branch_location")
	private String branch_location;

	@Column(name = "country")
	private String country;

	@Column(name = "account_email_id")
	private String account_email_id;

	@Column(name = "account_mobile_num")
	private String account_mobile_num;

	@Column(name = "inserted_timestamp")
	private Timestamp inserted_timestamp;

	@Column(name = "updated_timestamp")
	private Timestamp updated_timestamp;

	@Column(name = "sequence_num")
	private String sequence_num;

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getIfsc_cd() {
		return ifsc_cd;
	}

	public void setIfsc_cd(String ifsc_cd) {
		this.ifsc_cd = ifsc_cd;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_location() {
		return branch_location;
	}

	public void setBranch_location(String branch_location) {
		this.branch_location = branch_location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAccount_email_id() {
		return account_email_id;
	}

	public void setAccount_email_id(String account_email_id) {
		this.account_email_id = account_email_id;
	}

	public String getAccount_mobile_num() {
		return account_mobile_num;
	}

	public void setAccount_mobile_num(String account_mobile_num) {
		this.account_mobile_num = account_mobile_num;
	}

	public Timestamp getInserted_timestamp() {
		return inserted_timestamp;
	}

	public void setInserted_timestamp(Timestamp inserted_timestamp) {
		this.inserted_timestamp = inserted_timestamp;
	}

	public Timestamp getUpdated_timestamp() {
		return updated_timestamp;
	}

	public void setUpdated_timestamp(Timestamp updated_timestamp) {
		this.updated_timestamp = updated_timestamp;
	}

	public String getSequence_num() {
		return sequence_num;
	}

	public void setSequence_num(String sequence_num) {
		this.sequence_num = sequence_num;
	}

}
