package com.example.demo;

public class MobileBill {
	public static  int billNo  =0;
	private String name;
	private String mobileNumber;
	private String planName;
	private  double amount;
	public MobileBill(String name, String mobileNumber, String planName, double amount) {
	this.name=name;
	this.mobileNumber=mobileNumber;
	this.planName=planName;
	this.amount=amount;
	}
	public MobileBill(String name, String mobileNumber) {
		this(name,mobileNumber,"basic",199);
		MobileBill.billNo=MobileBill.billNo+1;
		//this.name = name;
		//this.mobileNumber = mobileNumber;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
		if(planName=="basic") {
			this.amount=199;
		}
		if(planName=="premium") {
			this.amount=399;
		}
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
		if(amount==199) {
			this.planName="basic";
					
		}
		if(amount==399) {
			this.planName="premium";
					
		}
	}
	public String getName() {
		return name;
	}
	
	

}
