package 二叉树;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * @author yaokun
 *
 */
public class _110_平衡二叉树 {
	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		
		int left = height(root.left);
		int right = height(root.right);
		if (Math.abs(left-right) > 1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
    }
	
	
	
	public int height(TreeNode node) {
		if (node == null) return 0;
		return  1 + Math.max(height(node.left), height(node.right));
	}
}
