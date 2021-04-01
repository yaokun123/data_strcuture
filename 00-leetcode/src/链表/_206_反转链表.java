package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author yaokun
 *
 */
public class _206_反转链表 {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { 
			this.val = val;
		}
		ListNode(int val, ListNode next) { 
			this.val = val; 
			this.next = next;
		}
	}

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode newHeadListNode = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return newHeadListNode;s
    }
}
