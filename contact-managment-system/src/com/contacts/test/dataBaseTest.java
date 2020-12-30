package com.contacts.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.contacts.dao.ContactImplement;
import com.contacts.utils.DbConnectionUtils;

class DatabaseTest {

	private Connection con;
    private ContactImplement dao;
		
	@BeforeAll
	public void beforeTestStarted() {
		con = DbConnectionUtils.getMySqlConnection();
		
	}
	
	
	@BeforeEach
	public void beforeTestMethod() {
		
		 dao = new ContactImplement(con);
	}
	
	@Test
	@DisplayName(value="Test return true If Database Connection is Established ")
	void test() throws SQLException {
		
		boolean Expected = con.isValid(30);
		boolean actual = true;
		assertEquals(Expected, actual);
		
	} 

	 @AfterAll
	public void afterAllTestCompleted() throws SQLException {
			 con.close();
			
		}

}
