package com.contacts.ifaces;


import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;

public interface DataAccess<T> {
public int add(T t);
public List<T> findAll();
public int removeContact(long phoneNumber);
public int updateContact(T t);
public List<T> findByAttribute(String attributeName,String attributeValue);
public List<T> findByAttribute(String attributeName,long attributeValue);
//public List<T> findByAttribute(String attributeName,Date attributeValue);
public HashMap<String,List<T>> listOfContactByGroup();
public List<String> contactListWithTwoColumn(String attributeName1,String attributeName2,String FileNmae);
public void contactListAscendingOrderOfGroupSize();
public List<String> birthdays(int month,String FileName);

}
