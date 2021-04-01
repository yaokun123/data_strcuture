package com.mj;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkList<Object> list = new LinkList<>();

		list.add(1);
		list.add("test1");
		list.add(121);
		list.add(1111);
		list.add(0,"hahaha");
		
		System.out.println(list);
		
		list.remove(1);
		System.out.println(list);
	}

}
