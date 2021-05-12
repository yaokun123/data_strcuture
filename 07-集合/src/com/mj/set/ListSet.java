package com.mj.set;

import java.util.LinkedList;
import java.util.List;

//使用链表来实现集合
public class ListSet<E> implements Set<E> {

	private List<E> list = new LinkedList<>();

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(E element) {
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		if (list.contains(element)) return;
		list.add(element);
	}

	@Override
	public void remove(E element) {
		list.remove(element);
	}

	@Override
	public void traversal(Visitor<E> visotor) {
		if(visotor == null) return;

		int size = list.size();
		
		
		for(int i = 0;i < size;i++) {
			visotor.visit(list.get(i));
		}
	}

}
