package com.contacts.services;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.io.*;

import com.contacts.entity.Contact;

public class ContactService{
	public boolean writeToTextFile(List<Contact> list,File fileName) {
		 
		 
	    try {  
	    	
	    	FileWriter myWriter = new FileWriter(fileName);
            Iterator<Contact> iterator = list.iterator();
			while(iterator.hasNext()){
							myWriter.write(iterator.next()+"\n");
			}
	        myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	}
	      catch (IOException e) {
			e.printStackTrace();
		}
	      return true;
      }
}
