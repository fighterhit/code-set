package sword.ch2;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 一个栈来添加元素，另一个删除元素
 * @author Fighter Created on 2018/4/29.
 */
public class P68_QueueWithTwoStacks {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()){
          while (!stack1.isEmpty()){
              stack2.push(stack1.pop());
          }
        }
        return stack2.pop();
    }

}
