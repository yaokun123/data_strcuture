package com.mj;


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
		
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		if(size == 0 || root == null) {//添加根节点
			root = new Node<>(element,null);
			size++;
			return;
		}
		
		//子节点
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
	
	public void remove(E element) {
		
	}
	
	public boolean contains(E element) {
		return false;
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

}
