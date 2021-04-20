package 二叉树;

/**
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 * @author yaok
 *
 */
public class _700_二叉搜索树中的搜索 {
	public TreeNode searchBST(TreeNode root, int val) {
		if(root == null) return null;
		
		TreeNode tmpNode = root;
		while (tmpNode != null) {
			if(tmpNode.val == val) {
				return tmpNode;
			}else if(tmpNode.val > val) {
				tmpNode = tmpNode.left;
			}else {
				tmpNode = tmpNode.right;
			}
		}
		return null;
    }
}
