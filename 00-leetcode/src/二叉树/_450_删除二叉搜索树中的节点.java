package 二叉树;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 * @author yaok
 *
 */
public class _450_删除二叉搜索树中的节点 {
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) return null;
		
		//遍历二叉搜索树
		TreeNode parentNode = null;
		TreeNode currentNode = root;
		
		while (currentNode != null) {
			if(currentNode.val == key) {
				break;
			}
			parentNode = currentNode;
			if(currentNode.val > key) {
				currentNode = currentNode.left;
			}else {
				currentNode = currentNode.right;
			}
		}
		
		
		if(currentNode == null) {//说明没有找到节点
			return root;
		}
		
		//删除节点（）
		if(currentNode.left != null && currentNode.right != null) {
			//找后继节点替换上去，然后删除后继节点
			parentNode = currentNode;
			TreeNode tmpNode = currentNode.right;
			
			while (tmpNode.left != null) {
				parentNode = tmpNode;
				tmpNode = tmpNode.left;
			}
			currentNode.val = tmpNode.val;
			currentNode = tmpNode;
			
		}
		
		//到这里说明待删除的节点只有1个或0个
		TreeNode replacementNode = currentNode.left != null ? currentNode.left : currentNode.right;
		
		if(replacementNode != null) {//有1个节点
			if(parentNode == null) {
				root = replacementNode;
			}else if(parentNode.left == currentNode) {
				parentNode.left = replacementNode;
			}else {
				parentNode.right = replacementNode;
			}
		}else {//无节点
			if(currentNode == root) {
				root = null;
			}else if(currentNode == parentNode.left) {
				parentNode.left = null;
			}else {
				parentNode.right = null;
			}
		}
		return root;
    }
	
}
