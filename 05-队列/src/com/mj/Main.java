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
		
		
	}

}
