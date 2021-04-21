package 二叉树;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/range-sum-of-bst/solution/er-cha-sou-suo-shu-de-fan-wei-he-by-leetcode/
 * @author yaok
 *
 */
public class _938_二叉搜索树的范围和 {
	public int rangeSumBST(TreeNode root, int low, int high) {
		//中序遍历有序
		
		LinkedList<TreeNode> l = new LinkedList<>();
		LinkedList<TreeNode> ll = inorder(root, l);
		
		int res = 0;
		boolean begin = false;
		TreeNode curNode = null;
		for(int i = 0;i<ll.size();i++) {
			curNode = ll.get(i);
			
			if(curNode.val == low) {
				res += curNode.val;
				begin = true;
			}else if(curNode.val == high) {
				System.out.println(high);
				res += curNode.val;
				return res;
			}else {
				if(begin) {
					res += curNode.val;
				}
			}
		}
		return res;
		
    }
	
	public LinkedList<TreeNode> inorder(TreeNode node,LinkedList<TreeNode> l) {
		if (node == null) return l;
		inorder(node.left,l);
		l.add(node);
		inorder(node.right, l);
		return l;
		
	}
}
