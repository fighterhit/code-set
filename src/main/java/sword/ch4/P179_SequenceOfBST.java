package sword.ch4;

/**
 * @author Fighter.
 */
public class P179_SequenceOfBST {
    public boolean VerifySquenceOfBST(int[] sequence) {
        //空树
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean VerifySquenceOfBST(int[] sequence, int l, int r) {
        //两个元素一定是BST
        if (r - l <= 1) {
            return true;
        }
        int rootVal = sequence[r];
        int rightSubTreeIndex = l;

        /*
        可能没有右子树，因此不能用这种写法
        for (int i = l; i < r; i++) {
            if (sequence[i] > rootVal) {
                rightSubTreeIndex = i;
                break;
            }
        }*/

        while (sequence[rightSubTreeIndex] < rootVal){
            rightSubTreeIndex++;
        }
        //检查右子树是不是都小于根节点
        for (int i = rightSubTreeIndex; i < r; i++) {
            if (sequence[i] < rootVal) {
                return false;
            }
        }
        return VerifySquenceOfBST(sequence, l, rightSubTreeIndex - 1) && VerifySquenceOfBST(sequence, rightSubTreeIndex, r - 1);
    }
}
