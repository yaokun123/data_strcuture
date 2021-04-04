package com.mj;


/**
 * 单向循环链表
 * @author yaokun
 *
 * @param <E>
 */
public class SingleCircleLinkList<E> {
	private int size;
	private Node<E> first;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	private static class Node<E>{
		E element;
		Node<E> next;
		
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		} 
	}
	
	public int size() {//元素的数量
		return size;
	}
	public boolean isEmpaty() {//是否为空
		return size == 0;
	}
	public boolean contains(E element) {//是否包含某个元素
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	public int indexOf(E element) {//查看元素位置
		Node<E> currentNode = first;
		//注意处理null的情况
		if (element == null) {
			for (int i=0;i<size;i++) {
				if (currentNode.element == null) return i;
				currentNode = currentNode.next;
			}
		}else {
			for (int i=0;i<size;i++) {
				//泛型比较不能直接使用等号（判断内存地址是不是相等）
				//可以重写equals方法
				//if (elements[i] == element) return i;
				if (element.equals(currentNode.element)) return i;
				currentNode = currentNode.next;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	public void add(E element) {//添加元素到最后面
		add(size,element);
	}
	public void add(int index, E element) {//往index位置添加元素
		//判断是不是第一个
		if (index == 0) {
			//最后一个元素应该指向头节点
			first = new Node<>(element,first);
			Node<E> lastNode = (size == 0) ? first : node(size-1).next;
			lastNode.next = first;
		}else {
			Node<E> preNode = node(index - 1);//上一个节点 
			preNode.next = new Node<>(element, preNode.next);
		}
		size++;
	}
	public void clear() {//清除所有元素
		first = null;
		size = 0;
	}
	public E get(int index){//返回index位置对应的元素
		return node(index).element;
	}
	public E set(int index,E element) {//设置index位置的元素，并返回原来元素
		Node<E> currentNode = node(index);
		E old = currentNode.element;
		currentNode.element = element;
		return old;
	}
	public E remove(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		Node<E> node = first;
		if (index == 0) {
			if(size == 1) {
				first = null;
			}else {
				Node<E> lastNode = node(size -1);
				first = first.next;
				lastNode.next = first;
			}	
		}else {
			Node<E> preNode = node(index-1);
			node = preNode.next;
			preNode.next = preNode.next.next;
		}
		size--;
		return node.element;
	}
	private Node<E> node(int index){//根据下标获取节点
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		Node<E> currentNode = first;
		for (int i = 0; i<index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> currntNode = first;
		for (int i = 0; i < size; i++) {
			string.append(currntNode.element);
			if (i != size-1) {
				string.append(", ");
			}
			currntNode = currntNode.next;
		}
		string.append("]");
		return string.toString();
	}
}
