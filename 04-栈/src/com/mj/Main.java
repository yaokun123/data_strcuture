package com.mj;

public class Main {
	
	public static void main(String[] args) {
		Stack<Object> stack1 = new Stack<Object>();
		
		stack1.push(11);
		stack1.push("test1");
		stack1.push(22);
		stack1.push("test2");
		
		System.out.println("============");
		System.out.println(stack1.size());
		
		
		while (!stack1.isEmpty()) {
			System.out.println(stack1.pop());
		}
	}
}
