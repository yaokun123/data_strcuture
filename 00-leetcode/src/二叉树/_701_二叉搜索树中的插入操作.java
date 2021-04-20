package 二叉树;


/**
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 * @author yaok
 *
 */
public class _701_二叉搜索树中的插入操作 {
	public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        
        if(root == null) {
        	root = newNode;
        }else {
        	TreeNode tmpNode = root;
        	TreeNode parentNode = null;
        	int cmp = 0;
        	while (tmpNode != null) {
				if(tmpNode.val == val) {
					return root;
				}

				parentNode = tmpNode;
				if(tmpNode.val > val) {
					cmp = 1;//左
					tmpNode = tmpNode.left;
				}else {
					cmp = -1;//右
					tmpNode = tmpNode.right;
				}	
			}
        	
        	if(cmp > 0) {
        		parentNode.left = newNode;
        	}else {
        		parentNode.right = newNode;
        	}
        }
        
        
        return root;
    }
}
