package 二叉树;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 * @author yaok
 *
 */
public class _530_二叉搜索树的最小绝对差 {
	public int getMinimumDifference(TreeNode root) {
		//二叉搜索树特征是中序遍历是有序的
		if(root == null) return  0;
		LinkedList<TreeNode> l = new LinkedList<>();
		LinkedList<TreeNode> ll = inorder(root, l);
		int min = 0;
		for(int i = 0;i<ll.size()-1;i++) {
			TreeNode curNode = ll.get(i);
			TreeNode nextNode = ll.get(i+1);
			if(i == 0) {
				min = Math.abs(curNode.val-nextNode.val);
			}else {
				min = Math.min(min, Math.abs(curNode.val-nextNode.val));
			}
			
		}
		return min;
    }
	
	
	public LinkedList<TreeNode> inorder(TreeNode node,LinkedList<TreeNode> l) {
		if (node == null) return l;
		inorder(node.left,l);
		l.add(node);
		inorder(node.right, l);
		return l;
		
	}
}
