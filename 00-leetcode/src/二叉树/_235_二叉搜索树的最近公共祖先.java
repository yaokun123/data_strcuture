package 二叉树;


/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * @author yaok
 *
 */
public class _235_二叉搜索树的最近公共祖先 {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val-p.val) * (root.val-q.val) > 0) {
			root = (root.val > p.val) ? root.left : root.right;
		}
        return root;
    }
}
