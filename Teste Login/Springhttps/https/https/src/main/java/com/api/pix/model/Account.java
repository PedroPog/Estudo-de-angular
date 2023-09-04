package com.api.pix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Account {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String branch;
	  private String account;
	  private String accountType;


	 // Getter Methods 

	  public String getBranch() {
	    return branch;
	  }

	  public String getAccount() {
	    return account;
	  }

	  public String getAccountType() {
	    return accountType;
	  }

	 // Setter Methods 

	  public void setBranch( String branch ) {
	    this.branch = branch;
	  }

	  public void setAccount( String account ) {
	    this.account = account;
	  }

	  public void setAccountType( String accountType ) {
	    this.accountType = accountType;
	  }
}
