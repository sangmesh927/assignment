package com.contacts.entity;

import java.time.LocalDate;

public class Contact {

	private String name;
	private long mobileNumber;
	private String address;
	private String profilePitcure;
	private LocalDate DateOfBirth;
	private String email;
	private String group;
	public Contact(String name, long mobileNumber, String address, String profilePitcure, LocalDate dateOfBirth,
			String email, String group) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.profilePitcure = profilePitcure;
		DateOfBirth = dateOfBirth;
		this.email = email;
		this.group = group;
	}
	public Contact(String name, long mobileNumber) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProfilePitcure() {
		return profilePitcure;
	}
	public void setProfilePitcure(String profilePitcure) {
		this.profilePitcure = profilePitcure;
	}
	public LocalDate getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "Contact [name=" + name + ", mobileNumber=" + mobileNumber + ", address=" + address + ", profilePitcure="
				+ profilePitcure + ", DateOfBirth=" + DateOfBirth + ", email=" + email + ", group=" + group + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DateOfBirth == null) ? 0 : DateOfBirth.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + (int) (mobileNumber ^ (mobileNumber >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profilePitcure == null) ? 0 : profilePitcure.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (DateOfBirth == null) {
			if (other.DateOfBirth != null)
				return false;
		} else if (!DateOfBirth.equals(other.DateOfBirth))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (mobileNumber != other.mobileNumber)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profilePitcure == null) {
			if (other.profilePitcure != null)
				return false;
		} else if (!profilePitcure.equals(other.profilePitcure))
			return false;
		return true;
	}
	
}
