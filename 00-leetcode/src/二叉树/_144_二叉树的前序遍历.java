package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class _144_二叉树的前序遍历 {
	
	
	//1、递归实现
	public List<Integer> preorderTraversal(TreeNode root) {
		
		
		List<Integer> res = new ArrayList<Integer>();
		
		preorder(root,res);
		return res;
    }
	
	public void preorder(TreeNode node,List<Integer> l) {
		if (node == null) return;
		
		l.add(node.val);
		preorder(node.left,l);
		preorder(node.right,l);
		
	}
	
	
	
	//2、遍历实现
}
