package com.mj;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树
 * @author yaokun
 *
 */
//public class BinarySearchTree<E extends Comparable> {//第一种方法
public class BinarySearchTree<E> {//第二种方法
	private Comparator<E> comparator;
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public BinarySearchTree() {
		this(null);
	}
	
	
	private static class Node<E>{
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent){
			this.element = element;
			this.parent = parent;
		}
	}
	
	
	private int size;
	private Node<E> root;
	
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
	 * 二叉搜索树-添加
	 * @param element
	 */
	public void add(E element) {
		elementNotNullCheck(element);
		
		//根节点要特殊处理
		if(size == 0 || root == null) {
			root = new Node<>(element,null);
			size++;
			return;
		}
		
		//其他子节点
		Node<E> node = root;
		Node<E> parent = null;	
		int cmp = 0;
		
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if(cmp > 0) {//往右找
				node = node.right;
			}else if(cmp < 0) {
				node = node.left;
			}else {
				node.element = element;//替换元素
				return;
			}
		}
		
		Node<E> newNode = new Node<>(element, parent);
		if(cmp > 0) {
			parent.right = newNode;
		}else if (cmp < 0) {
			parent.left = newNode;
		}
		
		size++;
	}
	
	
	/**
	 * 二叉搜索树-删除元素
	 * @param element
	 */
	public void remove(E element) {
		//1、删除度为0的节点（叶子节点）直接删除
		//node == node.parent.left	->  node.parent.left = null
		//node == node.parent.right ->	node.parent.right = null
		//node.parent == null		->	root = null
		
		//2、删除度为1的节点，用子节点替代原节点的位置（注意区分左右和根）
		//child是node.left 或者 child是node.right
		//如果node是左节点		->	child.parent = node.parent && node.parent.left = child
		//如果node是右节点		->	child.parent = node.parent && node.parent.right = child
		//如果node是根节点		->	child.parent = null && root = child
		
		
		//3、删除度为2的节点，先用前驱或者后继节点的值覆盖原节点的值，删除然后相应的前驱/后继
		remove(node(element));
		
	}
	
	/**
	 * 二叉搜索树-删除节点
	 * @param node
	 */
	private void remove(Node<E> node) {
		if (node == null) return;
		size--;
		
		if(node.left != null && node.right != null) {//1、度为2
			
			//找到后继---> 度为2的节点肯定会有前驱和后继
			Node<E> succNode = successor(node);
			
			node.element = succNode.element;//使用后继节点的值覆盖度为2的节点的值
			node = succNode;//删除后继节点（度为2的节点的前驱或者后继的度只可能为0或1）
		}
		
		
		//删除node节点
		Node<E> replacementNode = node.left != null ? node.left : node.right;
		
		if(replacementNode != null) {//node是度为1的
			replacementNode.parent = node.parent;
			
			if(node.parent == null) {//node是根
				root = replacementNode;
			}else if(node == node.parent.left) {//node是父节点的左节点
				node.parent.left = replacementNode;
			}else {//node是父节点的有右节点
				node.parent.right = replacementNode;
			}
		}else {//node度为0
			if(node.parent == null) {//node是root
				root = null;
				return;
			}
			
			if(node == node.parent.left) node.parent.left = null;
			if(node == node.parent.right) node.parent.right = null;
		}
	}
	
	/**
	 * 二叉搜索树-根据元素找到节点
	 * @param element
	 * @return
	 */
	private Node<E> node(E element){
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp > 0) node = node.right;
			if (cmp < 0) node = node.left;
			if (cmp == 0) return node;
			
		}
		return null;
	}
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	/**
	 * 可以定义一个接口，存放的数据都必须实现该接口的比较方法 
	 * @param e1
	 * @param e2
	 * @return
	 * 返回值等于0，代表e1和e2相等
	 * 返回值大于0，代表e1大于e2
	 * 返回值小于0，代表e1小于e2
	 */
	private int compare(E e1, E e2) {
		//第一种方法
		//return e1.compareTo(e2);
		
		//第二种方法
		//return comparator.compare(e1, e2);
		
		//优化（如果传递比较器则使用比较器，如果没有传递则要求存放的数据实现Comparable接口）
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	
	/**
	 * 二叉树-前序遍历
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
	

}
