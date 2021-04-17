package 二叉树;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @author yaokun
 *
 */
public class _226_翻转二叉树 {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) return null;
		
		//交换
		TreeNode tmpNode = root.left;
		root.left = root.right;
		root.right = tmpNode;
		
		
		invertTree(root.left);
		invertTree(root.right);
		
		return root;
    }
	
	
}
