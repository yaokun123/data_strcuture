package tree;

import com.mj.Comparable;
import com.mj.Comparator;

//二叉搜索树  继承自二叉树
public class BST<E> extends BinaryTree<E> {
	//二叉搜索树的属性
	private Comparator<E> comparator;
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	public BST() {
		this(null);
	}
	
	//二叉搜索树的方法
	public void add(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
		
		//根节点要特殊处理
		if(size == 0 || root == null) {
			//root = new Node<>(element,null);
			root = createNode(element, null);
			size++;
			
			//新添加节点之后的处理
			afterAdd(root);
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
		
		//Node<E> newNode = new Node<>(element, parent);
		Node<E> newNode = createNode(element, parent);
		if(cmp > 0) {
			parent.right = newNode;
		}else if (cmp < 0) {
			parent.left = newNode;
		}
		
		size++;
		
		//新添加节点之后的处理
		afterAdd(newNode);
	}
	
	/**
	 * 添加node之后的调整
	 * @param node新添加的节点
	 */
	protected void afterAdd(Node<E> node) {
		
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
		
		afterRemove(node,replacementNode);
	}
	/**
	 * 删除node之后的调整
	 * @param node
	 */
	protected void afterRemove(Node<E> node,Node<E> replacementNode) {
		
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
}
