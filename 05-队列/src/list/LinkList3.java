package list;

import java.awt.print.Printable;

import org.w3c.dom.ls.LSException;

/**
 * 双向链表
 * @author yaokun
 *
 * @param <E>
 */
public class LinkList3<E> {
	private int size;
	private Node<E> first;//头部
	private Node<E> last;//尾部
	
	private static final int ELEMENT_NOT_FOUND = -1;
	
	private static class Node<E>{
		E element;
		Node<E> next;//下一个
		Node<E> prev;//上一个
		
		//构造函数也要加上prev
		public Node(Node<E> prev, E element, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		} 
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			if(prev != null) {
				sb.append(prev.element);
			}
			
			sb.append("_").append(element).append('_');
			
			if(next != null) {
				sb.append(next.element);
			}
			return sb.toString();
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
		if (index < 0 || index > size) throw new IndexOutOfBoundsException("下标异常");
		//判断是不是第一个
		if (index == 0) {
			first = new Node<>(null,element,first);
			if(first.next == null) {//之前链表就是空的
				last = first;
			}else {
				first.next.prev = first;
			}			
		} else if(index == size){//最后一个也要特殊处理
			last = new Node<>(last, element, null);
			last.prev.next = last;
		}else {
			Node<E> currentNode = node(index);//找到当前节点
			
			
			Node<E> newNode = new Node<>(currentNode.prev,element,currentNode);
			newNode.prev.next = newNode;
			newNode.next.prev = newNode;
		}
		size++;
	}
	public void clear() {//清除所有元素
		first = null;
		last = null;
		size = 0;
		//这里的对象都可以被销毁(没有被gc root对象执行的对象都会被销毁)
		//1、被栈指针指向的对象
		//2、
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
		
		E old;
		if (index == 0) {
			old = first.element;
			if(first.next == null) {//说明只有一个元素
				first = null;
				last = null;
			}else {//后面还有元素
				first = first.next;
				first.prev = null;
			}
		}else if(index == size-1){//说明是之后一个元素
			old = last.element;
			last = last.prev;
			last.next = null;
		}else {//中间元素
			Node<E> currentNode = node(index);
			old = currentNode.element;
			Node<E> preNode = currentNode.prev;
			Node<E> nextNode = currentNode.next;
			preNode.next = nextNode;
			nextNode.prev = preNode;
		}
		size--;
		return old;
	}
	private Node<E> node(int index){//根据下标获取节点
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		
		//之前查找元素只能从头节点开始查找，现在可以从尾节点开始查找
		if (index < (size >> 2)){//从左边开始查找（同之前一样）
			Node<E> currentNode = first;
			for (int i = 0; i<index; i++) {
				currentNode = currentNode.next;
			}
			return currentNode;
		}else {//从右边开始查找（新找法）
			Node<E> curreNode = last;
			for(int i = size-1; i>index; i--) {
				curreNode = curreNode.prev;
			}
			return curreNode;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> currntNode = first;
		for (int i = 0; i < size; i++) {
			//string.append(currntNode);
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
