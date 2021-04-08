package com.mj;


//循环队列（数组实现）
public class CircleQueue<E> {
	private int front;//记录队头下标
	private int size;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public CircleQueue() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enQueue(E element) {
		//如果队列满了要扩容
		if(size == elements.length) {
			//do something
		}
		
		
		elements[(front + size) % elements.length] = element;
		size++;
	}
	
	public E deQueue() {
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
	
	public E front() {
		return elements[front];
	}
}
