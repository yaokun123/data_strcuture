package tree;

import com.mj.Comparator;

import tree.BinaryTree.Node;

//AVL树 继承自 BST
//windows NT内核中广泛使用
public class AVL<E> extends BST<E> {
	public AVL() {
		this(null);
	}
	public AVL(Comparator<E> comparator) {
		super(comparator);
	}
	
	private class AVLNode<E> extends Node<E>{
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
			// TODO Auto-generated constructor stub
		}
		int height = 1;//平衡因子
		
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			return leftHeight - rightHeight;
		}
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			if(leftHeight > rightHeight) return left;
			if(leftHeight < rightHeight) return right;
			return isLeftChild() ? left : right;
		}
	}
	
	
	/**
	 * 添加导致的失衡
	 * 最坏情况：可能会导致所有的祖先节点都失衡
	 * 父节点，非祖先节点都不可能失衡
	 * 
	 * LL 右旋转（单旋）
	 * 
	 * RR 左旋转（单旋）
	 * 
	 * LR RR左旋，LL右旋（双旋）
	 * 
	 * RL LL右旋，RR左旋（双旋）
	 */
	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			//判断node是否平衡
			if(isBalanced(node)) {
				//如果是平衡的，更新高度
				updateHeight(node);
			}else {
				//如果不是平衡的，恢复平衡
				rebalance(node);
				break;//整棵树恢复平衡
			}
		}
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		// TODO Auto-generated method stub
		return new AVLNode<>(element, parent);
	}
	
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	/**
	 * 恢复平衡
	 * @param node高度最低的不平衡节点
	 */
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		
		if(parent.isLeftChild()) {//L
			if(node.isLeftChild()) {//LL
				rotateRight(grand);
			}else {//LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		}else {//R
			if(node.isLeftChild()) {//RL
				rotateRight(parent);
				rotateLeft(grand);
			}else {//RR
				rotateLeft(grand);
			}
		}
	}
	
	/**
	 * 统一旋转
	 * @param grand
	 */
	private void rebalance2(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		
		if(parent.isLeftChild()) {//L
			if(node.isLeftChild()) {//LL
				rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
			}else {//LR
				rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}
		}else {//R
			if(node.isLeftChild()) {//RL
				rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
			}else {//RR
				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
			}
		}
	}
	private void rotate(Node<E> r,//子树根节点
			Node<E> a,Node<E> b,Node<E> c,
			Node<E> d,
			Node<E> e,Node<E>f,Node<E>g) {
		//让d成为子树根节点
		d.parent = r.parent;
		if(r.isLeftChild()) {
			r.parent.left = d;
		}else if(r.isRightChild()) {
			r.parent.right = d;
		}else {
			root = d;
		}
		
		//处理a-b-c
		b.left = a;
		if(a != null) {
			a.parent = b;
		}
		
		b.right = c;
		if(c != null) {
			c.parent = b;
		}
		updateHeight(b);
		
		//处理e-f-g
		f.left = e;
		if(e != null) {
			e.parent = f;
		}
		
		f.right = g;
		if(g != null) {
			g.parent = f;
		}
		updateHeight(f);
		
		
		//处理b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
		updateHeight(d);
		
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
		
		//更新高度
		updateHeight(grand);
		updateHeight(parent);
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
		
		//更新高度
		updateHeight(grand);
		updateHeight(parent);
	}
	
	
	/**
	 * 删除导致的失衡：
	 * 只可能会导致父节点 或祖先节点 失衡（只有一个节点会失衡）
	 */
	
	@Override
	protected void afterRemove(Node<E> node,Node<E> replacementNode) {
		while ((node = node.parent) != null) {
			//判断node是否平衡
			if(isBalanced(node)) {
				//如果是平衡的，更新高度
				updateHeight(node);
			}else {
				//如果不是平衡的，恢复平衡
				rebalance(node);
			}
		}
	}
	
	
}
