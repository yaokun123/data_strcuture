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
	}

}
