package 队列;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * @author yaokun
 *
 */
public class _225_用队列实现栈 {
	//private Queue<Integer> queue = new LinkedList<>();
	private Deque<Integer> queue = new ArrayDeque<Integer>();
	
	
	/** Initialize your data structure here. */
    public _225_用队列实现栈() {

    }
    
    /** Push element x onto stack. */
    public void push(int x) {
    	queue.addFirst(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	return queue.removeFirst();
    }
    
    /** Get the top element. */
    public int top() {
    	return queue.element();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
    	return queue.isEmpty();
    }
}
