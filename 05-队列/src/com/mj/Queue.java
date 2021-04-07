package com.mj;

import list.LinkList3;

//使用数组和链表都可以，优先使用双向链表，因为队列主要是往头尾操作元素
public class Queue<E> {
	private LinkList3<E> list3 = new LinkList3<>();
	
	public int size() {
		return list3.size();
	}
	
	public boolean isEmpty() {
		return list3.isEmpaty();
	}
	
	public void enQueue(E element) {//队尾插入
		list3.add(element);
	}
	
	
	public E deQueue() {//对头弹出
		return list3.remove(0);
	}
	
	public E front() {
		return list3.get(0);
	}
	
}
