package com.mj;

public class Person implements Comparable<Person>{
	
	private int age;
	
	public Person(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public int compareTo(Person e) {
		return age - e.age;
	}
}
