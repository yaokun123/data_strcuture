package 链表;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * @author yaokun
 *
 */
public class _83_删除排序链表中的重复元素 {

	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode() {}
		 ListNode(int val) { this.val = val; }
		 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode curNode = head.next;
		ListNode preNode = head;
		
		while(curNode != null) {
			if(curNode.val == preNode.val) {
				//删除当前
				preNode.next = curNode.next;
				curNode = preNode.next;
			}else {
				preNode = curNode;
				curNode = curNode.next;
			}
		}
		
		return head;
    }
}
