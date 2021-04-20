package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author yaok
 *
 */
public class _98_验证二叉搜索树 {
	
	public boolean isValidBST(TreeNode root) {

		//二叉搜索树特征是中序遍历是有序的
		if(root == null) return  false;
		LinkedList<TreeNode> l = new LinkedList<>();
		LinkedList<TreeNode> ll = inorder(root, l);
		for(int i = 0;i<ll.size()-1;i++) {
			TreeNode curNode = ll.get(i);
			TreeNode nextNode = ll.get(i+1);
			if(curNode.val >= nextNode.val) {
				return false;
			}
		}
		return true;
    }
	
	public LinkedList<TreeNode> inorder(TreeNode node,LinkedList<TreeNode> l) {
		if (node == null) return l;
		inorder(node.left,l);
		l.add(node);
		inorder(node.right, l);
		return l;
		
	}
}
