package 二叉树;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 * @author yaokun
 *
 */
public class _101_对称二叉树 {
	//层序遍历，查看每一层是否对称
	public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        
        //根节点不判断
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> tmp = new LinkedList<>();
        
        
        queue.offer(root);
        int size = 1;
        while (!queue.isEmpty()) {
			TreeNode curNode = queue.poll();
			size--;
			if(curNode.left != null) {
				queue.offer(curNode.left);
			}
			tmp.offerLast(curNode.left);
			if(curNode.right != null) {
				queue.offer(curNode.right);
			}
			tmp.offerLast(curNode.right);
			
			if(size == 0) {//遍历下一层
				size = queue.size();
				//检查是不是对称的
				boolean isRight = isRight(tmp);
				if(!isRight) return false; 
				tmp = new LinkedList<>();
			}
		}
        return true;
	}
	
	public boolean isRight(LinkedList<TreeNode> l) {
		if(l.size() == 0) return true;
		
		if(l.size() % 2 != 0) return false;
		
		while (!l.isEmpty()) {
			TreeNode firsTreeNode = l.pollFirst();
			TreeNode lasTreeNode = l.pollLast();
			if(firsTreeNode == null && lasTreeNode == null) {
				continue;
			}
			if(firsTreeNode == null || lasTreeNode == null) {
				return false;
			}
			
			if(firsTreeNode.val != lasTreeNode.val) {
				return false;
			}
		}
		return true;
	}
	
}
