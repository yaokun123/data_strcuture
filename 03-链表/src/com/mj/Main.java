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
		
		System.out.println("============使用虚拟头节点的链表============");
		
		
		LinkList2<Object> list2 = new LinkList2<>();
		list2.add(1);
		list2.add("test1");
		list2.add(121);
		list2.add(1111);
		list2.add(0,"hahaha");
		
		System.out.println(list2);
		
		list2.remove(1);
		System.out.println(list2);
	}

}
