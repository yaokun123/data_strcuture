package com.mj;

import java.rmi.Remote;

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
		System.out.println("判断元素是否存在：" + list2.indexOf(null));
		list2.add(null);
		System.out.println("判断元素是否存在：" + list2.indexOf(null));
		
		
		list2.add("test1");
		list2.add(121);
		list2.add(1111);
		list2.add(0,"hahaha");
		
		System.out.println(list2);
		
		list2.remove(1);
		System.out.println(list2);
		
		list2.clear();
		list2.add("test");
		System.out.println(list2);
		
		
		System.out.println("===========双向链表============");
		LinkList3<Object> list3 = new LinkList3<>();
		list3.add(11);
		list3.add(22);
		list3.add(33);
		list3.add(44);
		list3.add(0,55);
		list3.add(2,66);
		
		
		System.out.println(list3);
		list3.remove(list3.size()-1);
		System.out.println(list3);
		
		
		
		System.out.println("===========单向循环链表============");
		SingleCircleLinkList<Object> list4 = new SingleCircleLinkList<>();
		
		
		list4.add(11);
		list4.add(22);
		list4.add(33);
		list4.add(44);
		list4.add(0,55);
		list4.add(2,66);
		
		
		System.out.println(list4);
		list4.remove(list4.size()-1);
		System.out.println(list4);
		
		
		System.out.println("===========双向循环链表============");
		CirclrLinkList<Object> list5 = new CirclrLinkList<Object>();
		list5.add(11);
		list5.add(22);
		list5.add(33);
		list5.add(44);
		list5.add(0,55);
		list5.add(2,66);
		
		
		System.out.println(list5);
		list5.remove(list5.size()-1);
		System.out.println(list5);
		
		
		System.out.println("===========双向循环链表解决约瑟夫问题===========");
		CirclrLinkList<Object> list6 = new CirclrLinkList<Object>();
		list6.add(1);
		list6.add(2);
		list6.add(3);
		list6.add(4);
		list6.add(5);
		list6.add(6);
		list6.add(7);
		list6.add(8);
		int i = 0;
		int j = 1;
		while (!list6.isEmpaty()) {
			//System.out.println(i + " " + j);
			if((j) % 3 == 0) {
				System.out.println(list6.remove(i));
				//System.out.println(delIndex);
				//i = 0;
			}else {
				i++;
			}
			
			if (i == list6.size()) {
				i = 0;
			}
			j++;
			
		}
	}

}
