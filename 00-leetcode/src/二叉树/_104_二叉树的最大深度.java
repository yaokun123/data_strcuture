package 二叉树;

public class _104_二叉树的最大深度 {
	public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		
		return 1 + Math.max(maxDepth(root.left),maxDepth(root.right));
		
    }
	
	
}
