package com.contacts.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		
		Contact contact;
		ContactImplement service=new ContactImplement(con);
       List<String> groupNames=new ArrayList<>( Arrays.asList("relatives","personal friends","professional friends")); ;
		int key=-1;
		while(key!=0){
			System.out.println("please enter a choice");
			System.out.println("1- Add New Contact");
			System.out.println("2- Update Contact");
			System.out.println("3- Delete Contact");
			System.out.println("4- contacts by group");
			System.out.println("5- name -email");
			System.out.println("6- name -phoneNumber");
			System.out.println("7- contacts list by group increasing order of size");
			System.out.println("8- BirthDays of Month");
			System.out.println("0- Exit Application");
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
		case 4:
			   System.out.println("Contact list by Group");
			   HashMap<String, List<Contact>> contactsBygroup=service.listOfContactByGroup();
			   System.out.println(contactsBygroup);  
			   break;
		case 5:
			   System.out.println("name --- email");
			   System.out.println(service.contactListWithTwoColumn("contactName", "email","nameAndEmail.txt"));
			   break;
		case 6:
			   System.out.println("name --- mobileNumber");
			   System.out.println(service.contactListWithTwoColumn("contactName", "mobileNumber","nameAndPhone.txt"));
			   break;
		case 7:
			   System.out.println("contacts list by group size");
			   service.contactListAscendingOrderOfGroupSize();
			   break;
			   
		case 8:
			   System.out.println("Enter Month");
			   System.out.println(service.birthdays(scanner.nextInt(),"birthDay.txt"));
			   break;
			
	
		default:
			break;
		}
	
}
		scanner.close();	
		
	}

}
