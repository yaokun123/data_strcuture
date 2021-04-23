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
	 * 左旋
	 * @param node
	 */
	private void rotateLeft(Node<E> node) {
		//先指向p
		Node<E> tmpNode = node.right;
		
		node.right = node.right.left;
		if(node.right != null) {//修改父节点
			node.right.parent = node;
		}
		
		tmpNode.left = node;
		
		
		if(node.isLeftChild()) {
			node.parent.left = tmpNode;
		}else {
			node.parent.right = tmpNode;
		}
		
		//修改父节点
		tmpNode.parent = node.parent;
		node.parent = tmpNode;
		
		
		//更新高度
		if(node.right != null) {
			AVLNode<E> tNode = (AVLNode<E>)node.right;
			tNode.updateHeight();
		}
		((AVLNode<E>)node).updateHeight();
		((AVLNode<E>)node.parent).updateHeight();
	}
	
	/**
	 * 右旋
	 * @param node
	 */
	private void rotateRight(Node<E> node) {
		
	}
	
	
}
