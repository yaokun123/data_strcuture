package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @author yaokun
 *
 */
public class _20_有效的括号 {

	
	/**
	 * 第一种方式
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
			s.replace("{}", "");
			s.replace("[]", "");
			s.replace("()", "");
		}
		return s.isEmpty();
    }
	
	
	public boolean isValid2(String s) {
		Stack<Character> stack = new Stack<>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if(c == '(' || c == '{' || c == '[') {//左字符进栈
				stack.push(c);
			}else {//右字符出栈
				if (stack.size() == 0) {
					return false;
				}else {
					char cap = stack.pop();
					if((c == ')' && cap == '(') || (c == '}' && cap == '{') || (c == ']' && cap == '[')) {
						continue;
					}else{
						return false;
					}
				}	
			}
		}
		
		//循环完成之后，判断栈是否为空
		return stack.isEmpty();
	}
}
