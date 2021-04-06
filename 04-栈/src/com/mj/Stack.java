package com.mj;

import list.LinkList2;

public class Stack<E> {
	
	//使用数组和链表都可以
	private LinkList2<E> list2 = new LinkList2<>();
	
	public int size() {
		return list2.size();
	}
	
	public boolean isEmpty() {
		return list2.isEmpaty();
	}
	
	public void push(E element) {
		list2.add(element);
	}
	
	public E pop() {
		return list2.remove(list2.size()-1);
	}
	
	public E top() {
		return list2.get(list2.size()-1);
	}
}
