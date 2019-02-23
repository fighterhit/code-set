package sword.ch4;

import java.util.Stack;

/**
 * @author Fighter.
 */

//双栈法
public class P165_StackWithMin {

    Stack<Integer> data = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public void push(int x) {
        data.push(x);
        if (min.isEmpty() || x <= min.peek()) {
            min.push(x);
        } else {
            min.push(min.peek());
        }
    }

    public void pop() {
        assert !data.isEmpty();
        assert !min.isEmpty();

        data.pop();
        min.pop();
    }

    public int top() {
        assert !data.isEmpty();
        return data.peek();
    }

    public int min() {
        assert !min.isEmpty();
        return min.peek();
    }
}

//局部变量存最小值，当压入更小值时则先压入原来的最小值，省内存
class P165_StackWithMin2 {

    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;

    public void push(int x) {
        //先压入老的最小值
        if (x < min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        //栈顶就是最小值，则下面的值就是次小值
        if (stack.pop() == min){
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}
