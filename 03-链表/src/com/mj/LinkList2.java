package com.mj;

/**
 * 添加虚拟头节点，这样就不需要再单独处理头节点了，代码更加精简
 * @author yaokun
 *
 * @param <E>
 */
public class LinkList2<E> {
	
	//添加一个构造函数，创建链表的时候就有一个虚拟头节点了
	public LinkList2(){
		first = new Node<>(null, null);
	}
	
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
		//注意这里first指向了虚拟头节点，所以遍历应该从first.next开始
		Node<E> currentNode = first.next;
		
		
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
//		if (index == 0) {
//			first = new Node<>(element,first);
//		}else {
//			Node<E> preNode = node(index - 1);//上一个节点 
//			preNode.next = new Node<>(element, preNode.next);
//		}
		
		//使用虚拟头节点，这里就不需要淡出判断是不是第一个了
		Node<E> preNode = index == 0 ? first : node(index - 1);//上一个节点 
		preNode.next = new Node<>(element, preNode.next);
		
		size++;
	}
	public void clear() {//清除所有元素
		//first = null;
		//使用虚拟头节点，清除的时候就要将虚拟头节点的next指向null
		first.next = null;
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
//		Node<E> node = first;
//		if (index == 0) {
//			first = first.next;
//		}else {
//			Node<E> preNode = node(index-1);
//			node = preNode.next;
//			preNode.next = preNode.next.next;
//		}
		
		Node<E> preNode = index == 0 ? first : node(index-1);//前一个节点
		Node<E> node = preNode.next;//当前节点
		preNode.next = preNode.next.next;
		
		size--;
		return node.element;
	}
	private Node<E> node(int index){//根据下标获取节点
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		Node<E> currentNode = first.next;//first代表虚拟头节点，这里第一个元素就是first.next
		for (int i = 0; i<index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> currntNode = first.next;//first是虚拟的
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
