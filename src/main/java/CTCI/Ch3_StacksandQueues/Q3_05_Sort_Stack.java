package CTCI.Ch3_StacksandQueues;

import java.util.ArrayList;
import java.util.Stack;

public class Q3_05_Sort_Stack {
    public ArrayList<Integer> twoStacksSort(int[] numbers) {
        // write code here
        ArrayList<Integer> res = new ArrayList<>();
        if (numbers == null || numbers.length == 0) {
            return res;
        }
        Stack<Integer> stack = new Stack<>(), helper = new Stack<>();
        for (int i = numbers.length - 1; i >= 0; i--) {
            stack.push(numbers[i]);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!helper.isEmpty() && helper.peek() > cur) {
                stack.push(helper.pop());
            }
            helper.push(cur);
        }
        while (!helper.isEmpty()) {
            res.add(helper.pop());
        }
        return res;
    }
}
