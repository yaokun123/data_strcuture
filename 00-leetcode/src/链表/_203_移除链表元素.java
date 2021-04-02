package 链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @author yaokun
 *
 */
public class _203_移除链表元素 {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public ListNode removeElements(ListNode head, int val) {
		ListNode current = head;
		ListNode preNode = head;
		while(current != null && current.next != null) {
			if(head.val == val) {//如果是头节点要单独处理
				head = head.next;
				current = head;
				preNode = head;
			}else {
				if(current.val == val) {
					preNode.next = current.next;
					current = preNode.next;
				}else {
					preNode = current;
					current = current.next;
				}
				
			}	
		}
		return head;
    }
}
