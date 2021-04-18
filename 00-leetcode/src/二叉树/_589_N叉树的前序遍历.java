package 二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * @author yaokun
 *
 */
public class _589_N叉树的前序遍历 {
	public List<Integer> preorder(Node root) {
		List<Integer> res = new ArrayList<>();
        return pre(root, res);
    }
	
	public List<Integer> pre(Node node,List<Integer> l){
		if (node == null) return l;
		l.add(node.val);
		
		for (Node item : node.children) {
            pre(item, l);
        }
		
		return l;
	}
	
	
}
