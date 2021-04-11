package com.mj;

public class Main {
	
	private static class PersonComparator implements Comparator<Person>{
		public int compare(Person e1, Person e2) {
			return e1.getAge() - e2.getAge();
			//return e2.getAge() - e1.getAge();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Integer data[] = new Integer[] {
				7,4,9,2,5,8,11,3
		};
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		
		
		//第一种方法：要求存放的数据都要实现Comparable接口
		/*BinarySearchTree<Person> bst2 = new BinarySearchTree<>();
		bst2.add(new Person(12));
		bst2.add(new Person(15));*/
		
		//第二种方法：使用二叉树的时候传递一个比较器（每个二叉树的比较逻辑可以个性化定制）
		/*BinarySearchTree<Person> bst3 = new BinarySearchTree<>(new PersonComparator());
		bst3.add(new Person(12));
		bst3.add(new Person(15));*/
		
		//优化，可以不传比较器，有默认比较器
	}

}
