package 二叉树;

public class _104_二叉树的最大深度 {
	
	
	//1、递归实现
	public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		
		return 1 + Math.max(maxDepth(root.left),maxDepth(root.right));
		
    }
	
	
	//2、层序遍历实现
	
	
}
