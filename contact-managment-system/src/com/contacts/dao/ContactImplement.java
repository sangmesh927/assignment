package com.contacts.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.contacts.entity.Contact;
import com.contacts.ifaces.DataAccess;
import com.contacts.utils.DbConnectionUtils;

public class ContactImplement implements DataAccess<Contact>{
	private Connection con;
	public ContactImplement(Connection con) {
		
		this.con = con;
	}
	public ContactImplement() {
		super();
		this.con = DbConnectionUtils.getMySqlConnection();
	}
	@Override
	public int add(Contact t) {
		String sql="insert into contactTab values(?,?,?,?,?,?,?)";
		
		int rowsAdded=0;
		try (PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, t.getName());
			pstmt.setLong(2, t.getMobileNumber());	
			pstmt.setString(3, t.getAddress());
			pstmt.setNull(4, java.sql.Types.DATE);
    		pstmt.setString(5, t.getProfilePitcure());
    		pstmt.setString(6, t.getEmail());
    		pstmt.setString(7, t.getGroup());
			if(t.getDateOfBirth() !=null) {
				pstmt.setDate(4, Date.valueOf(t.getDateOfBirth()));
			}
            rowsAdded=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowsAdded;
	}

	

	@Override
	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int removeContact(long phoneNumber) {
		String sql="delete from contactTab where mobileNumber=?";
		int deleteStatus = 0;
		try(PreparedStatement pstmt=con.prepareStatement(sql)) {
	
			pstmt.setLong(1,phoneNumber);
            deleteStatus=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return deleteStatus;
	}
	@Override
	public int updateContact(Contact t) {
		String sql="update contactTab set contactName=?, address=?, dateOfBirth=?, profilePicture=?, email=?, contactGroup=? where mobileNumber=?";
		int rowsUpdated=0;
		try (PreparedStatement pstmt=con.prepareStatement(sql)){
			pstmt.setString(1, t.getName());
			
			pstmt.setString(2, t.getAddress());
			pstmt.setNull(3, java.sql.Types.DATE);
    		pstmt.setString(4, t.getProfilePitcure());
    		pstmt.setString(5, t.getEmail());
    		pstmt.setString(6, t.getGroup());
    		pstmt.setLong(7, t.getMobileNumber());	
			if(t.getDateOfBirth() !=null) {
				pstmt.setDate(3, Date.valueOf(t.getDateOfBirth()));
			}
			rowsUpdated=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdated;
	}

	
	

}
