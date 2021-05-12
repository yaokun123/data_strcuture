package com.mj;

import com.mj.set.ListSet;
import com.mj.set.Set;
import com.mj.set.Set.Visitor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<Integer> listSet = new ListSet<>();
		
		listSet.add(10);
		listSet.add(11);
		listSet.add(11);
		listSet.add(12);
		listSet.add(10);
		
		listSet.traversal(new Visitor<Integer>() {
			
			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
	}

}
