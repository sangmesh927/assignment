package com.contacts.dao;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		List<Contact> contactList=new ArrayList<Contact>();
		String sql="select *from contactTab";
		
		try(PreparedStatement pstmt=con.prepareStatement(sql)) {
			ResultSet rs=pstmt.executeQuery();
			ResultSetMetaData rsmeta=rs.getMetaData();
			System.out.println("Number of columns "+rsmeta.getColumnCount());
			System.out.println("Table name of column 1"+rsmeta.getTableName(1));
			while(rs.next()) {
				String contactName=rs.getString("contactName");
				 long mobileNumber=rs.getLong("mobileNumber");
				 String address=rs.getString("address");
				 String profilePicture=rs.getString("profilePicture");
				 LocalDate dateOfBirth=rs.getDate("dateOfBirth").toLocalDate();
				 String email=rs.getString("email");
				 String contactGroup=rs.getString("contactGroup");
			
				Contact contact=new Contact(contactName, mobileNumber, address, profilePicture, dateOfBirth, email, contactGroup);
				contactList.add(contact);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return contactList;
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
	@Override
	public HashMap<String, List<Contact>> listOfContactByGroup() {
		HashMap<String, List<Contact>> contactsBygroup=new HashMap<String, List<Contact>>();
		contactsBygroup.put("relatives",findByAttribute("contactGroup","relatives"));
		contactsBygroup.put("personal friends",findByAttribute("contactGroup","personal friends"));
		contactsBygroup.put("professional friends",findByAttribute("contactGroup","professional friends"));
		System.out.println(contactsBygroup.size());
		
		try {
			  File fileObj = new File("ContactlistbyGroup.txt");
			  if (fileObj.createNewFile()) {
			        System.out.println("File created");
			      }
			  FileWriter FileWriter = new FileWriter(fileObj);
			  Iterator<Entry<String, List<Contact>>> iterator = contactsBygroup.entrySet().iterator(); 
			  FileWriter.write("Contact list by Group\n");
			   while (iterator.hasNext()) {
				    Map.Entry mapElement = (Map.Entry)iterator.next(); 
				    FileWriter.write(mapElement.getKey()+":\n");
				    FileWriter.write(mapElement.getValue()+":\n");
		           
			   }
			   FileWriter.close();
		  } catch (IOException e) {
			  System.out.println("error occured");
				e.printStackTrace();
			}
		return contactsBygroup;
	}
	
	@Override
	public List<Contact> findByAttribute(String attributeName, String attributeValue) {
		String sql="select *from contactTab where contactGroup=?";
		List<Contact> contactList=new ArrayList<Contact>();
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			//pstmt.setString(1, attributeName);
			pstmt.setString(1, attributeValue);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String contactName=rs.getString("contactName");
				 long mobileNumber=rs.getLong("mobileNumber");
				 String address=rs.getString("address");
				 String profilePicture=rs.getString("profilePicture");
				 LocalDate dateOfBirth=rs.getDate("dateOfBirth").toLocalDate();
				 String email=rs.getString("email");
				 String contactGroup=rs.getString("contactGroup");
				 Contact contact=new Contact(contactName, mobileNumber, address, profilePicture, dateOfBirth, email, contactGroup);
				 contactList.add(contact);
		    } 
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return contactList;
	}
	@Override
	public List<Contact> findByAttribute(String attributeName, long attributeValue) {
		String sql="select *from contactTab mobileNumber ?=?";
		List<Contact> contactList=new ArrayList<Contact>();
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			//pstmt.setString(1, attributeName);
			pstmt.setLong(1, attributeValue);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String contactName=rs.getString("contactName");
				 long mobileNumber=rs.getLong("mobileNumber");
				 String address=rs.getString("address");
				 String profilePitcure=rs.getString("profilePitcure");
				 LocalDate dateOfBirth=rs.getDate("dateOfBirth").toLocalDate();
				 String email=rs.getString("email");
				 String contactGroup=rs.getString("contactGroup");
				 Contact contact=new Contact(contactName, mobileNumber, address, profilePitcure, dateOfBirth, email, contactGroup);
				 contactList.add(contact);
		    } 
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return contactList;

	}
	@Override
	public List<String> contactListWithTwoColumn(String attributeName1, String attributeName2,String FileNmae) {
		String sql="select *from contactTab";
		 List<String> contactList=new ArrayList<>();		
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			ResultSet rs=pstmt.executeQuery();
			try {
				  File fileObj = new File(FileNmae);
				  if (fileObj.createNewFile()) {
				        System.out.println("File created");
				      }
				  FileWriter FileWriter = new FileWriter(fileObj); 
				  FileWriter.write(attributeName1+"-"+attributeName2+"\n");
					  while(rs.next()) {
						  String string1=rs.getString(attributeName1);
						  String string2=rs.getString(attributeName2);
						  String result=string1+" "+string2;
						  contactList.add(result) ;
						  FileWriter.write(string1+"-");
						  FileWriter.write(string2+"\n");
					  }   FileWriter.close();
				  } catch (IOException e) {
					  System.out.println("error occured");
						e.printStackTrace();
					}
		      
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return contactList;
	}
	@Override
	public void contactListAscendingOrderOfGroupSize() {
	
		String sql= "select contactGroup,count(*) as total from contactTab group by contactGroup order by total";
		try(PreparedStatement pstmt=con.prepareStatement(sql)){
			ResultSet rs=pstmt.executeQuery();
			try {
				  File fileObj = new File("contactsByGroup.txt");
				  if (fileObj.createNewFile()) {
				        System.out.println("File created");
				      }
				  FileWriter FileWriter = new FileWriter(fileObj); 
				  FileWriter.write("contacts by group in ascending order of group size\n");
					  while(rs.next()) {
						  String string1=rs.getString("contactGroup");
						  String string2=rs.getString("total"); 
						  FileWriter.write(string1+"-");
						  FileWriter.write(string2+"\n");
						  FileWriter.write(findByAttribute("contactGroup",string1)+"\n");
					  }   FileWriter.close();
				  } catch (IOException e) {
					  System.out.println("error occured");
						e.printStackTrace();
					}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<String> birthdays(int month,String FileName) {
         List<String> list=new ArrayList<>(); 
		
		String sql = "select *from contactTab where month(dateOfBirth)=? order by date(dateOfBirth);";
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, month);		 	
			ResultSet rs = pstmt.executeQuery();
			try {
				  File fileObj = new File(FileName);
				  if (fileObj.createNewFile()) {
				        System.out.println("File created");
				   }
				  FileWriter FileWriter = new FileWriter(fileObj); 
				  FileWriter.write("BirthDays of month\n");
			      while(rs.next()){
				  Date birthday =rs.getDate("dateOfBirth");
				  String name  = rs.getString("contactName");
				  String email  =rs.getString("email");
				  String contactNumber  = rs.getString("mobileNumber");
				  String result=name+"  "+birthday.toString()+" " +email+" "+contactNumber;
				  list.add(result);
				  FileWriter.write(result+"\n");
			      }
			      FileWriter.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (Exception e) {	
					e.printStackTrace();;
		 }
				
			return list;
	}
	
	

	
	

}
