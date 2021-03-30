package com.mj;

public class Main {
	
	/*
	 * 0 1 1 2 3 5 8 13....
	 */
	
	/**
	 * 普通实现
	 * @param n
	 * @return
	 */
	public static int fib(int n) {
		if (n <=1) return n;
		
		int i = 0;
		int j = 1;
		while(n>1) {
			int tmp = i + j;
			i = j;
			j = tmp;
			n--;
		}
		return j;
	}
	
	/**
	 * 递归实现
	 * @param n
	 * @return
	 */
	public static int fib2(int n) {
		if (n <=1) return n;
		return fib2(n-1) + fib2(n-2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(123);
		
		System.out.println(fib(45));
		System.out.println(fib2(45));
		
		
	}

}
