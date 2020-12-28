package com.contacts.app;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.contacts.dao.ContactImplement;
import com.contacts.entity.Contact;
import com.contacts.utils.DbConnectionUtils;

public class Application {

	public static void main(String[] args) {
		Connection con=DbConnectionUtils.getMySqlConnection();
		 String contactName;
		 long mobileNumber;
		 String address;
		 String profilePitcure;
		 LocalDate dateOfBirth;
		 String email;
		 String group;
		Scanner scanner = new Scanner(System.in);
		System.out.println("please enter a choice");
		System.out.println("1- Add New Contact");
		System.out.println("2- Update Contact");
		System.out.println("3- Delete Contact");
		System.out.println("0- Exit Application");
		Contact contact;
		ContactImplement service=new ContactImplement(con);
       List<String> groupNames=new ArrayList<>( Arrays.asList("relatives","personal friends","professional friends")); ;
		int key=-1;
		while(key!=0){
		key=scanner.nextInt();
		switch(key) {
		case 1:
			//int rowAdded=service.add(c1);
			  System.out.println("Enter ContacT Name");
			  contactName = scanner.next()+scanner.nextLine();
			   
			  System.out.println("Mobile Number");
			  mobileNumber = scanner.nextLong();			  
			  System.out.println("Enter adress");
			  address = scanner.next()+scanner.nextLine();
			  
			  System.out.println("Enter email");
			  email = scanner.next()+scanner.nextLine();
			  
			  System.out.print("Enter date of birth in YYYY-MM-DD");
			  dateOfBirth = LocalDate.parse(scanner.next());
			  System.out.println(dateOfBirth);
			  
			  System.out.println("Enter profile picture url");
			  profilePitcure = scanner.next()+scanner.nextLine();
			  
			  System.out.println("Enter Contact group");
			  System.out.println("0-for relatives");
			  System.out.println("1-for personal friends");
			  System.out.println("2-for professional friends");
			  group = groupNames.get(scanner.nextInt());
			  
			  contact=new Contact(contactName, mobileNumber, address, profilePitcure, dateOfBirth, email, group);
			  int rowAdded=service.add(contact);
			  if(rowAdded==1) {
					System.out.println("contact added succesfully");
				}
				else {
					System.out.println("mobile numebr "+mobileNumber+" already present the database");
				}
			break;
		case 2:
			System.out.println("Enter Contact number to be deleted");
			mobileNumber = scanner.nextLong();	
			int rowDeleted=service.removeContact(mobileNumber);
			if(rowDeleted==1) {
				System.out.println("contact deleted succesfully");
			}
			else {
				System.out.println("mobile numebr "+mobileNumber+" not present the database");
			}
			break;
		case 3:
			System.out.println("Enter Contact number to be updated");
			mobileNumber = scanner.nextLong();	
			int rowDeleted1=service.removeContact(mobileNumber);
			if(rowDeleted1==0) {
				System.out.println("mobile numebr "+mobileNumber+" not present the database");
			}
			else {
			  System.out.println("Enter Contact Name");
			  contactName = scanner.next()+scanner.nextLine();
			  
			  System.out.println("Enter adress");
			  address = scanner.next()+scanner.nextLine();
			  
			  System.out.println("Enter email");
			  email = scanner.next()+scanner.nextLine();
			  
			  System.out.print("Enter date of birth in YYYY-MM-DD");
			  dateOfBirth = LocalDate.parse(scanner.next());
			  System.out.println(dateOfBirth);
			  
			  System.out.println("Enter profile picture url");
			  profilePitcure = scanner.next()+scanner.nextLine();
			  
			  System.out.println("Enter Contact group");
			  System.out.println("0-for relatives");
			  System.out.println("1-for personal friends");
			  System.out.println("2-for professional friends");
			  group = groupNames.get(scanner.nextInt());
			  
			  contact=new Contact(contactName, mobileNumber, address, profilePitcure, dateOfBirth, email, group);
			  int rowUpdated=service.add(contact);
			  System.out.println("contact upadtaed successfully");
			}
		
			break;
		default:
			break;
		}
		
}
		
	}

}
