package com.mj;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Object> queue = new Queue<>();
		
		queue.enQueue(11);
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(44);
		queue.enQueue(55);
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
		
		System.out.println("==========双向队列===========");
		Deque<Integer> deque = new Deque<>();
		
		deque.enQueueFront(11);
		deque.enQueueFront(22);
		deque.enQueueRear(33);
		deque.enQueueRear(44);
		
		while (!deque.isEmpty()) {
			System.out.println(deque.deQueueFront());
			
		}
		
		
		System.out.println("==========环形队列===========");
		CircleQueue<Object> queue2 = new CircleQueue<>();
		for(int i = 0;i<10;i++) {
			queue2.enQueue(i);
		}
		
		for(int i = 0;i<5;i++) {
			queue2.deQueue();
		}
		
		
		for(int i = 15;i<23;i++) {
			queue2.enQueue(i);
		}
		
		//System.out.println(queue);
		while (!queue2.isEmpty()) {
			System.out.println(queue2.deQueue());
			
		}
		
		
		System.out.println("==========循环双端队列===========");
		CircleDeque<Integer> cDeque = new CircleDeque<>();
		for(int i = 0;i<10;i++) {
			cDeque.enQueueFront(i+1);
			cDeque.enQueueRear(i + 100);
		}
		
		for(int i = 0;i<3;i++) {
			cDeque.deQueueFront();
			cDeque.deQueueRear();
		}
		
		cDeque.enQueueFront(11);
		cDeque.enQueueFront(22);
		
		while (!cDeque.isEmpty()) {
			System.out.println(cDeque.deQueueFront());
			
		}
		
	}

}
