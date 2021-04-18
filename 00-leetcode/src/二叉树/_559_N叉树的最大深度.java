package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 * @author yaokun
 *
 */
public class _559_N叉树的最大深度 {
	public int maxDepth(Node root) {
		if (root == null) return 0;
		
		Queue<Node> queqe = new LinkedList<>();
		queqe.offer(root);
		
		int height = 0;
		int levelSize = 1;
		
		while (!queqe.isEmpty()) {
			Node curNode = queqe.poll();
			levelSize--;
			
			for (Node item : curNode.children) {
	            queqe.offer(item);
	        }
			
			if(levelSize == 0) {
				height++;
				levelSize = queqe.size();
			}
		}
		
		return height;
    }
}
