package com.mj;

import list.LinkList3;

//循环双端队列
public class CircleDeque<E> {
	private int front;//记录队头下标
	private int size;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public CircleDeque() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enQueueRear(E element) {//队尾入队
		//如果队列满了要扩容
		ensureCapacity(size+1);
		
		elements[(front + size) % elements.length] = element;
		size++;
	}
	
	public E deQueueFront() {//队头出队
		//如果队列没有元素了
		if(size == 0) {
			throw new IndexOutOfBoundsException("队列为空");
		}
		E old = elements[front];
		elements[front] = null;
		
		front = (front+1) % elements.length;
		size--;
		
		//如果删除之后队列为空,front回到0
		if(size == 0) {
			front = 0;
		}
		return old;
	}
	
	public void enQueueFront(E element) {//队头入队
		//如果队列满了要扩容
		ensureCapacity(size+1);
		if (front == 0) {
			front = elements.length - 1;
		}else {
			front = (front - 1) % elements.length;
		}
		
		elements[front] = element;
		
		size++;
	}
	
	public E deQueueRear() {//队尾出队
		//如果队列没有元素了
		if(size == 0) {
			throw new IndexOutOfBoundsException("队列为空");
		}
		int index = (front + size - 1) % elements.length;
		E old = elements[index];
		elements[index] = null;
		
		size--;
		
		//如果删除之后队列为空,front回到0
		if(size == 0) {
			front = 0;
		}
		return old;
	}
	
	public E front() {//获取队头
		return elements[front];
	}
	
	public E rear() {//获取队尾
		return elements[(front + size - 1) % elements.length];
	}
	
	private void ensureCapacity(int capacity) {//保证要有capacity容量
		int oldCapacity = elements.length;//数组的容量
		if (oldCapacity >= capacity) return;
		
		int newCapacity = oldCapacity + (oldCapacity >> 1);//1.5倍
		E[] newElements = (E[]) new Object[newCapacity];
		for(int i = 0;i<size;i++) {
			newElements[i] = elements[(i + front) % elements.length];
		}
		elements = newElements;
		
		//重置front
		front = 0;
		System.out.println(oldCapacity + "扩容成功为：" + newCapacity);
	}
}
