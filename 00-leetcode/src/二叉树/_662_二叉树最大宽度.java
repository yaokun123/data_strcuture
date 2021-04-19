package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 * @author yaokun
 *
 */
public class _662_二叉树最大宽度 {
	
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) return 0;
		
		LinkedList<TreeNode> queue = new LinkedList<>();
		root.val = 1;
		queue.offer(root);
		
		int res = 0;
		
		while (!queue.isEmpty()) {
			res = Math.max(res, queue.peekLast().val - queue.peekFirst().val + 1);
			
			int n = queue.size();
			
			while (n > 0) {
				TreeNode curNode = queue.poll();
				if(curNode.left != null) {
					curNode.left.val = curNode.val * 2 + 1;
					queue.offer(curNode.left);
				}
				
				if(curNode.right != null) {
					curNode.right.val = curNode.val * 2 + 2;
					queue.offer(curNode.right);
				}
				n--;
			}
		}
		
		return res;
    }
}
