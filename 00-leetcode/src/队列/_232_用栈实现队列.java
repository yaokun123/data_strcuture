package 队列;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @author yaokun
 *
 */
public class _232_用栈实现队列 {
	
	
	//思路就是使用两个栈
	//1、入队时，push到inStack中
	//2、出队时，判断outStack是否为空，
	//如果为空则将inStack所有元素逐一弹出,push到outStack中，outStack弹出栈顶元素
	//如果不为空，outStack弹出栈顶元素
	private Stack<Integer> inStack = new Stack<>();
	private Stack<Integer> outStack = new Stack<>();
	
	/** Initialize your data structure here. */
    public _232_用栈实现队列() {

    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {//入队
    	inStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {//出队
    	if(outStack.isEmpty()) {
    		while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
				
			}
    	}
    	return outStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {//获取对头元素
    	if(outStack.isEmpty()) {
    		while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
				
			}
    	}
    	return outStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
    	return inStack.isEmpty() && outStack.isEmpty();
    }
}
