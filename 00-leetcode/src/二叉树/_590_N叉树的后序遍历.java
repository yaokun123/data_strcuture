package 二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * @author yaokun
 *
 */
public class _590_N叉树的后序遍历 {
	public List<Integer> postorder(Node root) {
		List<Integer> res = new ArrayList<>();
        return post(root, res);
    }
	
	public List<Integer> post(Node node,List<Integer> l){
		if (node == null) return l;
		for (Node item : node.children) {
            post(item, l);
        }
		l.add(node.val);
		
		return l;
	}
}
