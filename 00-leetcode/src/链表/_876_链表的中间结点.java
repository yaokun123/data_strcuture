package 链表;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * @author yaokun
 *
 */
public class _876_链表的中间结点 {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	/**
	 * 使用快慢指针
	 * @param head
	 * @return
	 */
	public ListNode middleNode(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode fist = head;
		ListNode slow = head;
		
		while(fist != null && fist.next != null) {
			slow = slow.next;
			fist = fist.next.next;
		}
		
		return slow;
    }
}
