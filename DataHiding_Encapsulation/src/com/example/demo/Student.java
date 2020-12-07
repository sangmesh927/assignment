package com.example.demo;

public class Student {
	private String name;
	private int rollNumber;
	private double markScored;
    public static String trainerName="javagal";
	public Student() {
		
	}
	public Student(String name, int rollNumber) {
		//calling overloaded constructer 
		this(name, rollNumber,99);
		this.setName(name);
		this.setRollNumber(rollNumber);
	}
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMarkScored() {
		return markScored;
	}
	public void setMarkScored(double markScored) {
		this.markScored = markScored;
	}
	public String assignGrade() {
		//local variable has to be initialized
		//they can't have modifier like private,protected
		String grade="O";
		if( this.markScored<80) {
			grade="A";
		}
		return grade;
	}
	public Student(String name, int rollNumber, double markScored) {
		this.name = name;
		this.rollNumber = rollNumber;
		this.markScored = markScored;
	}
	
	
	

}
