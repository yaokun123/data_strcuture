package 二叉树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 * @author yaokun
 *
 */
public class _107_二叉树的层序遍历2 {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> parentList = new ArrayList<>();
		List<Integer> childList = new ArrayList<>();
		
		
		
		if(root == null) return parentList;
		
		//使用队列
		Queue<TreeNode> queue = new LinkedList<>();
		
		//根节点入队
		queue.offer(root);
		int nodeNum = 1;
		
		while (!queue.isEmpty()) {
			TreeNode curNode = queue.poll();
			childList.add(curNode.val);
			nodeNum--;
			
			if(curNode.left != null) {
				queue.offer(curNode.left);
			}
			if(curNode.right != null) {
				queue.offer(curNode.right);
			}
			
			if(nodeNum == 0) {//下一层
				parentList.add(childList);
				childList = new ArrayList<>();
				nodeNum = queue.size();
			}
		}
		
		Collections.reverse(parentList);
		return parentList;
    }
}
