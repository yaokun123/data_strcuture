package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//二叉树
public class BinaryTree<E> {
	//二叉树的属性
	protected int size;
	protected Node<E> root;
	
	//二叉树的节点
	protected static class Node<E>{
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent){
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
		public Node<E> sibling(){
			if(isLeftChild()) {
				return parent.right;
			}
			if(isRightChild()) {
				return parent.left;
			}
			
			return null;
		}
	}
	
	
	//二叉树的方法
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void clear() {
		root = null;
		size = 0;
	}
	
	
	/**
	 * 二叉树-前序遍历（递归实现）
	 */
	public void preorderTraversal() {
		preorderTraversal(root);
	}
	public void preorderTraversal(Node<E> node) {
		if (node == null) return;
		System.out.println(node.element);
		
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
	
	/**
	 * 二叉树-前序遍历（循环实现）使用栈
	 */
	public void preorderTraversal2() {
		if (root == null) return;
		
		Node<E> node = root;
		Stack<Node<E>> stack = new Stack<>();
		
		while (true) {
			if(node != null) {
				System.out.println(node.element);
				//右子节点入栈
				if(node.right != null) {
					stack.push(node.right);
				}
				//向左走
				node = node.left;
			}else if(stack.isEmpty()) {
				return;
			}else {
				node = stack.pop();
			}
			
		}
	}
	
	/**
	 * 二叉树-前序遍历（循环实现）使用栈
	 */
	public void preorderTraversal3() {
		if (root == null) return;
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node<E> node = stack.pop();
			
			System.err.println(node.element);
			
			if(node.right != null) {
				stack.push(node.right);
			}
			
			if(node.left != null) {
				stack.push(node.left);
			}
		}
	}
	
	
	/**
	 * 二叉树-后序遍历
	 */
	public void postorderTraversal() {
		postorderTraversal(root);
	}
	public void postorderTraversal(Node<E> node) {
		if (node == null) return;
		
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.println(node.element);
	}
	
	
	/**
	 * 二叉树-后序遍历（循环实现）使用栈
	 */
	public void postorderTraversal2() {
		if (root == null) return;
		
		//记录上一次弹出访问的节点
		Node<E> prev = null;
		
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node<E> top = stack.peek();
			if((top.right == null && top.left == null) || (prev != null && prev.parent == top)) {
				prev = stack.pop();
				System.out.println(prev.element);
			}else {
				if(top.right != null) {
					stack.push(top.right);
				}
				if(top.left != null) {
					stack.push(top.left);
				}
			}
			
		}
	}
	
	
	/**
	 * 二叉树中序遍历
	 */
	public void inorderTraversal() {
		inorderTraversal(root);
	}
	public void inorderTraversal(Node<E> node) {
		if (node == null) return;
		
		inorderTraversal(node.left);
		System.out.println(node.element);
		inorderTraversal(node.right);
	}
	
	/**
	 * 二叉树-中序遍历（循环实现）使用栈
	 */
	public void inorderTraversal2() {
		if (root == null) return;
		
		Node<E> node = root;
		Stack<Node<E>> stack = new Stack<>();
		
		while (true) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			}else if(stack.isEmpty()) {
				return;
			}else {
				node = stack.pop();
				System.out.println(node.element);
				
				if(node.right != null) {
					node = node.right;
				}
			}
		}
		
	}
	
	
	/**
	 * 二叉树层序遍历
	 */
	public void levelOrderTraversal() {
		if (root == null) return;
		
		//使用队列
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);//根节点入队
		
		while (!queue.isEmpty()) {
			Node<E> currentNode = queue.poll();
			System.out.println(currentNode.element);
			
			if (currentNode.left != null) {
				queue.offer(currentNode.left);
			}
			
			if(currentNode.right != null) {
				queue.offer(currentNode.right);
			}
			
		}
	}
	
	
	
	/**
	 * 二叉树-计算高度（递归）
	 * @return
	 */
	public int height() {
		return height(root);
	}
	public int height(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	
	/**
	 * 二叉树-计算高度（层序遍历）
	 * @return
	 */
	public int height2() {
		if (root == null) return 0;
		
		//使用队列
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);//根节点入队
		
		int height = 0;
		int levelSize = 1;
		
		while (!queue.isEmpty()) {
			Node<E> currentNode = queue.poll();
			levelSize--;
			
			if (currentNode.left != null) {
				queue.offer(currentNode.left);
			}
			if(currentNode.right != null) {
				queue.offer(currentNode.right);
			}
			
			if(levelSize == 0) {//即将要访问下一层
				levelSize = queue.size();
				height++;
			}
		}
		return height;
	}
	
	
	/**
	 * 判断一棵树是否为完全二叉树（层序遍历）
	 * @return
	 */
	public boolean isComplete() {
		if (root == null) return false;
		
		/**
		 * 思路：
		 * 如果node.left != null && node.right != null ，将node.left、node.right按顺序入队
		 * 
		 * 如果node.left == null && node.right != null ，返回false
		 * 
		 * 如果node.left != null && node.right == null 
		 * 或者node.left == null && node.right == null
		 * 那么后面的遍历的节点都为叶子节点，才是完全二叉树
		 */
		
		//使用队列
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);//根节点入队
		boolean leaf = false;//标识是否从该节点之后都要是叶子节点
		
		while (!queue.isEmpty()) {
			Node<E> currentNode = queue.poll();
			if(leaf) {
				if(currentNode.left != null || currentNode.right != null) {
					return false;
				}
			}
			
			if (currentNode.left != null && currentNode.right != null) {//左右节点都不为空
				queue.offer(currentNode.left);
				queue.offer(currentNode.right);
			}else if(currentNode.left == null && currentNode.right != null) {//左节点为空、右节点不为空
				return false;
			}else {//左右节点都为空 + 左节点不为空右节点为空（后面遍历的节点都应该是叶子节点）
				leaf = true;
				if(currentNode.left != null) {//如果是左节点不为空右节点为空的情况左节点还要正常入队
					queue.offer(currentNode.left);
				}
			}
		}
		return true;
	}
	
	
	/**
	 * 二叉树-前驱节点
	 * 按照中序遍历得到节点的前一个节点
	 * @param node
	 * @return
	 */
	public Node<E> predecessor(Node<E> node){
		if (node == null) return null;
		
		if(node.left != null) {//如果左节点存在，就从左节点开始找，找到最右节点
			Node<E> tmpNode = node.left;
			while (node.right != null) {
				tmpNode = node.right;
			}
			return tmpNode;
		}else {
			//判断父节点是否为空
			while (node.parent != null && node.parent.left == node) {
				node = node.parent;
			}
			
			//node.parent == null || node = node.parent.right
			return node.parent;
		}
	}
	
	
	/**
	 * 二叉树-后继节点
	 * 按照中序遍历得到节点的后一个节点
	 * @param node
	 * @return
	 */
	public Node<E> successor(Node<E> node){
		if (node == null) return null;
		
		if(node.right != null) {//如果右节点存在，就从右节点开始找，找到最左节点
			Node<E> tmpNode = node.right;
			while (node.left != null) {
				tmpNode = node.left;
			}
			return tmpNode;
		}else {
			//判断父节点是否为空
			while (node.parent != null && node.parent.right == node) {
				node = node.parent;
			}
			
			//node.parent == null || node = node.parent.right
			return node.parent;
		}
	}
	
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
}
