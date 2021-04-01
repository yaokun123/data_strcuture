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

	/**
	 * 方法一：递归实现
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode newHead = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
    }
	
	/**
	 * 方法二：迭代实现
	 * @param head
	 * @return
	 */
	public ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode newHead = null;
		while (head != null) {
			ListNode tmp = head.next;
			head.next = newHead;
			newHead = head;
			head = tmp;
			
		}
		return newHead;
    }
}
