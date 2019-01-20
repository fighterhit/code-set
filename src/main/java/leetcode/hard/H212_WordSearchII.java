package leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * Example:
 * Input:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Output: ["eat","oath"]
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 * M79_WordSearch
 */
public class H212_WordSearchII {

    HashSet res;

    public List<String> findWords(char[][] board, String[] words) {
        res = new HashSet();
        if (board == null || words == null) {
            return null;
        }
        for (int i = 0; i < words.length; i++) {
            if (exist(board, words[i])) {
                res.add(words[i]);
            }
        }
        return new ArrayList<>(res);
    }

    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean[][] visited;
    int xLen, yLen;

    public boolean exist(char[][] board, String word) {
        xLen = board.length;
        yLen = board[0].length;
        visited = new boolean[xLen][yLen];
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                //换个开始位置找 word
                if (searchWord(board, wordArr, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchWord(char[][] board, char[] wordArr, int x, int y, int wordIndex) {
        //退出条件：若字符串遍历到最后一个位置
        if (wordIndex == wordArr.length - 1) {
            return board[x][y] == wordArr[wordIndex];
        }

        if (board[x][y] == wordArr[wordIndex]) {
            visited[x][y] = true;
            //当前位置匹配，则看周围四个方向
            for (int i = 0; i < dirs.length; i++) {
                int newX = dirs[i][0] + x, newY = dirs[i][1] + y;
                //判断坐标合法性、是否访问过，并且是否匹配
                if (checkCoordinate(newX, newY)
                        && !visited[newX][newY]
                        && searchWord(board, wordArr, newX, newY, wordIndex + 1)) {
                    return true;
                }
            }
            //四个位置没有匹配的
            visited[x][y] = false;
        }

        return false;
    }

    private boolean checkCoordinate(int x, int y) {
        return x >= 0 && x < xLen && y >= 0 && y < yLen;
    }
}
