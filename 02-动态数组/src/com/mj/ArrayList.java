package com.mj;

public class ArrayList {
	//int size();元素的数量
	//boolean isEmpaty();是否为空
	//boolean contains(E element);是否包含某个元素
	//void add(E element);添加元素到最后面
	//E get(int index);返回index位置对应的元素
	//E set(int index, E element);设置index位置的元素，并返回原来元素
	//void add(int index, E element);往index位置添加元素
	//E remove(int index);删除index位置对应的元素
	//int indexOf(E element);查看元素位置
	//void clear();清除所有元素
	
	private int size;//元素的数量
	private int[] elements;//所有元素
	private static final int DEFAULT_CAPATICY = 4;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	public ArrayList(int capaticy) {//构造函数
		capaticy = (capaticy < DEFAULT_CAPATICY) ? DEFAULT_CAPATICY : capaticy;
		elements = new int[capaticy];
	}
	public ArrayList() {//构造函数
		//elements = new int[DEFAULT_CAPATICY];
		this(DEFAULT_CAPATICY);
	}
	public int size() {//元素的数量
		return size;
	}
	public boolean isEmpaty() {//是否为空
		return size == 0;
	}
	public int get(int index) {//返回index位置对应的元素
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		return elements[index];
	}
	public int set(int index, int element) {//设置index位置的元素，并返回原来元素
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		int old = elements[index];
		elements[index] = element;
		return old;
	}
	public int indexOf(int element) {//查看元素位置
		for (int i=0;i<size-1;i++) {
			if (elements[i] == element) return i;
		}
		return ELEMENT_NOT_FOUND;
	}
	public boolean contains(int element) {//是否包含某个元素
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	public void clear() {//清除所有元素
		size = 0;
	}
	public void add(int element) {//添加元素到最后面
		add(size,element);
	}
	public void add(int index,int element) {//往index位置添加元素
		if (index < 0 || index > size) throw new IndexOutOfBoundsException("下标异常");
		ensureCapacity(size+1);//扩容
		for (int i = size-1;i>=index;i--) {
			elements[i+1] = elements[i];
		}
		elements[index] = element;
		size++;
	}
	public int remove(int index) {//删除index位置对应的元素
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		int old = elements[index];
		for (int i = index+1;i<size;i++) {
			elements[i-1] = elements[i];
		}
		size--;
		return old;
	}
	public String toString() {//自定义打印
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			string.append(elements[i]);
			if (i != size-1) {
				string.append(", ");
			}
		}
		string.append("]");
		return string.toString();
	}
	private void ensureCapacity(int capacity) {//保证要有capacity容量
		int oldCapacity = elements.length;//数组的容量
		if (oldCapacity >= capacity) return;
		
		int newCapacity = oldCapacity + (oldCapacity >> 1);//1.5倍
		int[] newElements = new int[newCapacity];
		for(int i = 0;i<size;i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println(oldCapacity + "扩容成功为：" + newCapacity);
	}
	
}
