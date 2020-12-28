package com.contacts.services;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.contacts.entity.Contact;
import com.contacts.ifaces.DataAccess;
import com.contacts.utils.DbConnectionUtils;

public class ContactService{
	private Connection con;
	public ContactService(Connection con) {
		
		this.con = con;
	}
	public ContactService() {
		super();
		this.con = DbConnectionUtils.getMySqlConnection();
	}
	public int remove() {
		return 0;
		
	}
	
}
