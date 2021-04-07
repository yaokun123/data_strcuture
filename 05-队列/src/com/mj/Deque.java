package com.mj;

import list.LinkList3;

//双端队列

public class Deque<E> {
	
	private LinkList3<E> list3 = new LinkList3<>();
	
	public int size() {
		return list3.size();
	}
	
	public boolean isEmpty() {
		return list3.isEmpaty();
	}
	
	public void enQueueRear(E element) {//队尾入队
		list3.add(element);
	}
	
	public E deQueueFront() {//对头出队
		return list3.remove(0);
	}
	
	public void enQueueFront(E element) {//队头入队
		list3.add(0, element);
	}
	
	public E deQueueRear() {//队尾出队
		return list3.remove(list3.size()-1);
	}
	
	public E front() {//获取队头
		return list3.get(0);
	}
	
	public E rear() {//获取队尾
		return list3.get(list3.size()-1);
	}
}
