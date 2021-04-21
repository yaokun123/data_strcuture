package 二叉树;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * @author yaok
 *
 */
public class _173_二叉搜索树迭代器 {
	public int index = -1;
	public LinkedList<TreeNode> ll = null;
	public void BSTIterator(TreeNode root) {
		LinkedList<TreeNode> l = new LinkedList<>();
		ll = inorder(root, l);
    }
    
    public int next() {
    	if (hasNext()) {
    		index++;
    		return ll.get(index).val;
    	}
    	return -1;
    }
    
    public boolean hasNext() {
    	return index < ll.size()-1;
    }
    
    
    public LinkedList<TreeNode> inorder(TreeNode node,LinkedList<TreeNode> l) {
		if (node == null) return l;
		inorder(node.left,l);
		l.add(node);
		inorder(node.right, l);
		return l;
		
	}
}
