package 二叉树;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * @author yaok
 *
 */
public class _230_二叉搜索树中第K小的元素 {
	public int kthSmallest(TreeNode root, int k) {
		//二叉搜索树特征是中序遍历是有序的
		LinkedList<TreeNode> l = new LinkedList<>();
		LinkedList<TreeNode> ll = inorder(root, l);
		return ll.get(k-1).val;
    }
	
	public LinkedList<TreeNode> inorder(TreeNode node,LinkedList<TreeNode> l) {
		if (node == null) return l;
		inorder(node.left,l);
		l.add(node);
		inorder(node.right, l);
		return l;
		
	}
}
