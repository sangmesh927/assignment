package com.example.demo;

public class application {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MobileBill ram= new MobileBill("Ram","9008657107");
		System.out.println(ram.getName()+":"+ram.getMobileNumber()+" "+ram.getPlanName()+" "+ram.getAmount());
		System.out.println(MobileBill.billCount);
		
		MobileBill sangmesh= new MobileBill("Sangmesh","9008657108","premium",399);
		System.out.println(sangmesh.getName()+":"+sangmesh.getMobileNumber()+" "+sangmesh.getPlanName()+" "+sangmesh.getAmount());
		System.out.println(MobileBill.billCount);
		
		ram.setPlanName("premium");
		System.out.println(ram.getName()+":"+ram.getMobileNumber()+" "+ram.getPlanName()+" "+ram.getAmount());
		
		ram.setAmount(199);
		System.out.println(ram.getName()+":"+ram.getMobileNumber()+" "+ram.getPlanName()+" "+ram.getAmount());

	
	}

}
