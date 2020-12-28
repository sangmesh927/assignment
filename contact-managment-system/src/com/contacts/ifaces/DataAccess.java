package com.contacts.ifaces;


import java.util.List;

public interface DataAccess<T> {
public int add(T t);
public List<T> findAll();
public int removeContact(long phoneNumber);
public int updateContact(T t);

}
