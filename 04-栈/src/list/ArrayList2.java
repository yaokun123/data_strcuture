package list;

//<E>使用泛型技术可以让动态数组更加通用，可以存储任何数据类型
public class ArrayList2<E> {
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
	private E[] elements;//所有元素
	private static final int DEFAULT_CAPATICY = 4;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	@SuppressWarnings("unchecked")
	public ArrayList2(int capaticy) {//构造函数
		capaticy = (capaticy < DEFAULT_CAPATICY) ? DEFAULT_CAPATICY : capaticy;
		elements = (E[]) new Object[capaticy];//所有的类都继承java.lang.Object
	}
	public ArrayList2() {//构造函数
		//elements = new int[DEFAULT_CAPATICY];
		this(DEFAULT_CAPATICY);
	}
	public int size() {//元素的数量
		return size;
	}
	public boolean isEmpaty() {//是否为空
		return size == 0;
	}
	public E get(int index) {//返回index位置对应的元素
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		return elements[index];
	}
	public E set(int index, E element) {//设置index位置的元素，并返回原来元素
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		E old = elements[index];
		elements[index] = element;
		return old;
	}
	public int indexOf(E element) {//查看元素位置
		//注意处理null的情况
		if (element == null) {
			for (int i=0;i<size;i++) {
				if (elements[i] == null) return i;
			}
		}else {
			for (int i=0;i<size;i++) {
				//泛型比较不能直接使用等号（判断内存地址是不是相等）
				//可以重写equals方法
				//if (elements[i] == element) return i;
				if (element.equals(elements[i])) return i;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	public boolean contains(E element) {//是否包含某个元素
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	public void clear() {//清除所有元素
		size = 0;
		//泛型的数组中存放的是对象的地址，不清空会造成大量内存浪费
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
	}
	public void add(E element) {//添加元素到最后面
		add(size,element);
	}
	public void add(int index,E element) {//往index位置添加元素
		if (index < 0 || index > size) throw new IndexOutOfBoundsException("下标异常");
		ensureCapacity(size+1);//扩容
		for (int i = size-1;i>=index;i--) {
			elements[i+1] = elements[i];
		}
		elements[index] = element;
		size++;
	}
	public E remove(int index) {//删除index位置对应的元素
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("下标异常");
		E old = elements[index];
		for (int i = index+1;i<size;i++) {
			elements[i-1] = elements[i];
		}
		size--;
		elements[size] = null;
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
		E[] newElements = (E[]) new Object[newCapacity];
		for(int i = 0;i<size;i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		System.out.println(oldCapacity + "扩容成功为：" + newCapacity);
	}
	
}
