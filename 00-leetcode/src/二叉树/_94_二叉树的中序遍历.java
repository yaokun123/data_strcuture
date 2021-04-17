package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class _94_二叉树的中序遍历 {
	
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		
		inorder(root,res);
		return res;
    }
	
	public void inorder(TreeNode node,List<Integer> l) {
		if (node == null) return;
		
		inorder(node.left,l);
		l.add(node.val);
		inorder(node.right,l);
		
	}
}
