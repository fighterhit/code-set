package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * <p>
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * path = "/a/../../b/../c//.//", => "/c"
 * path = "/a//b////c/d//././/..", => "/a/b/c"
 * <p>
 * In a UNIX-style file system, a period ('.') refers to the current directory, so it can be ignored in a simplified path. Additionally, a double period ("..") moves up a directory, so it cancels out whatever the last directory was. For more information, look here: https://en.wikipedia.org/wiki/Path_(computing)#Unix_style
 * <p>
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * <p>
 * 根据题目中给的那一个例子还真不太好总结出规律，应该再加上两个例子 path = "/a/./b/../c/", => "/a/c"和path = "/a/./b/c/", => "/a/b/c"， 这样我们就可以知道中间是"."的情况直接去掉，是".."时删掉它上面挨着的一个路径，而下面的边界条件给的一些情况中可以得知，如果是空的话返回"/"，如果有多个"/"只保留一个。那么我们可以把路径看做是由一个或多个"/"分割开的众多子字符串，把它们分别提取出来一一处理即可
 */
public class M71_SimplifyPath {

    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : arr) {
            //s 为 .. 并且存在上一级目录(栈内有元素)情况下，需要弹出上一级目录
            if (s.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }
            //当出现非 "."、".."、""(空串)情况下，即此时是目录名，则入栈
            else if (!s.equals(".") && !s.equals("..") && !s.equals("")) {
                stack.push(s);
            }
        }
        List<String> res = new ArrayList<>(stack);
        return "/" + String.join("/", res);
    }

    public static void main(String[] args) {
        //输出 [, ..]
        System.out.println(Arrays.toString("/../".split("/")));
        //[, a, ., b, .., .., c]
        System.out.println(Arrays.toString("/a/./b/../../c/".split("/")));
        //[, a, .., .., b, .., c, , .]
        System.out.println(Arrays.toString("/a/../../b/../c//.//".split("/")));
        //[, a, , b, , , , c, d, , ., ., , ..]
        System.out.println(Arrays.toString("/a//b////c/d//././/..".split("/")));
        new M71_SimplifyPath().simplifyPath("/a//b////c/d//././/..");
    }
}
