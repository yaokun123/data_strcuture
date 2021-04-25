package tree;

import com.mj.Comparator;

import tree.BinaryTree.Node;

//红黑树 继承自 BST
//1、C++ STL（比如map、set）
//2、java的TreeMap、TreeSet、HashMap、HashSet
//3、Linux的进程调度
//4、Nginx的timer管理
public class RBT<E> extends BST<E> {
	
	/**
	 * 红黑树也是一种自平衡的二叉搜索树，必须满足以下5条性质
	 * 1、节点是RED或者BLANK
	 * 2、根节点是BLANK
	 * 3、叶子节点（外部节点、空节点）都是BLANK
	 * 4、RED节点的子节点都是BLANK
	 * 	  RED节点的父节点都是BLANK
	 * 	  从根节点到叶子节点的所有路径上不能有两个连续的RED节点
	 * 5、从任意节点到叶子节点的所有路径都包含相同数目的BLANK节点
	 */
	
	
	
	
	
	/**
	 * B-树
	 * B树是一种平衡的多路搜索树，多用于文件系统、数据库的实现
	 * 
	 * 特点：
	 * 1、1个节点可以存储超过两个元素、可以拥有超过2个子节点
	 * 2、拥有二叉树的一些性质
	 * 3、平衡，每个节点的所有子树高度一致
	 * 4、比较矮
	 * 
	 * m阶B树的性质(m>=2)：阶->最多有几个子节点
	 * 1、假设一个节点存储的元素个数为x
	 * 根节点：1 <= x <= m-1
	 * 
	 * 非根节点：ceil(m/2)-1 <= x <= m-1
	 * 
	 * 如果有子节点，子节点的个数y = x + 1
	 * 	根节点：2 <= y <= m
	 * 	非根节点：ceil(m/2) <= y <= m
	 * 
	 */
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public RBT() {
		this(null);
	}
	public RBT(Comparator<E> comparator) {
		super(comparator);
	}
	
	private static class RBTNode<E> extends Node<E>{
		boolean color = RED;
		public RBTNode(E element, Node<E> parent) {
			super(element, parent);
		}
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		//1、有四种情况满足红黑树的性质4:parent为black
		//同样也满足4阶b树
		//因此不用做任何处理
		Node<E> parent = node.parent;
		
		if(parent == null) {//添加的根节点
			black(node);
			return;
		}
		if(isBlack(parent)) return;
		
		
		//2、有八种情况不满足红黑树的性质4:parent为red(double red)
		Node<E> uncle = parent.sibling();
		Node<E> grand = parent.parent;
		
		//2-1叔父节点是红色
		/**
		 * 1、parent、uncle染成black
		 * 2、grand向上合并（染成red,当作是新添加的节点进行处理）
		 */
		if(isRed(uncle)) {
			black(parent);
			black(uncle);
			
			red(grand);
			afterAdd(grand);
			return;
		}
		
		
		
		//2-2叔父节点不是red
		/**
		 * LL/RR
		 * 1、parent染成black，grand染成red
		 * 2、grand进行单旋
		 * 
		 * 
		 * LR/Rl
		 * 1、自己染成black，grand染成red
		 * 进行双旋操作
		 * LR:parent左旋，grand右旋
		 * RL:parent右旋，grand左旋
		 */
		
		if(parent.isLeftChild()) {//L
			if(node.isLeftChild()) {//LL
				black(parent);
				red(grand);
				rotateRight(grand);
			}else {//LR
				black(node);
				red(grand);
				rotateLeft(parent);
				rotateRight(grand);
			}
		}else {//R
			if(node.isLeftChild()) {//RL
				black(node);
				red(grand);
				rotateRight(parent);
				rotateLeft(grand);
			}else {//RR
				black(parent);
				red(grand);
				rotateLeft(grand);
			}
		}
		
		
		
	}
	
	@Override
	protected void afterRemove(Node<E> node,Node<E> replacementNode) {
		//1、如果删除的红色不用做任何处理
		if(isRed(node)) return;
		
		//2、用以取代node节点的子节点是红色（度为1）
		if(isRed(replacementNode)) {
			black(replacementNode);
		}
		
		//3、删除的是黑色叶子节点
	}
	
	/**
	 * 染色
	 * @param node
	 * @param color
	 * @return
	 */
	private Node<E> color(Node<E> node,boolean color){
		if (node == null) return node;
		((RBTNode<E>)node).color = color;
		return node;
	}
	private Node<E> red(Node<E> node){
		return color(node, RED);
	}
	private Node<E> black(Node<E> node){
		return color(node, BLACK);
	}
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBTNode<E>)node).color;
	}
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}
	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}
	
	
	
	/**
	 * 左旋
	 * @param grand
	 */
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		
		grand.right = child;
		parent.left = grand;
		
		parent.parent = grand.parent;//修改p的父节点
		
		//让parent成为子树的根节点
		if(grand.isLeftChild()) {
			grand.parent.left = parent;
		}else if(grand.isRightChild()){
			grand.parent.right = parent;
		}else {//grand是根节点
			root = parent;
		}
		
		//修改child的parent
		if(child != null) {
			child.parent = grand;
		}
		
		//修改grand的parent
		grand.parent = parent;
	}
	
	/**
	 * 右旋
	 * @param node
	 */
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		
		grand.left = child;
		parent.right = grand;
		
		//修改parent的父节点
		parent.parent = grand.parent;
		
		//让parent成为子树的根节点
		if(grand.isLeftChild()) {
			grand.parent.left = parent;
		}else if (grand.isRightChild()) {
			grand.parent.right = parent;
		}else {
			root = parent;
		}
		
		//更新child的父节点
		if(child != null) {
			child.parent = grand;
		}
		//更新grand的父节点
		grand.parent = parent;
	}

}
