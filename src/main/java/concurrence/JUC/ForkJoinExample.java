package concurrence.JUC;

import java.util.concurrent.*;

public class ForkJoinExample extends RecursiveTask<Integer> {
    int l, r;

    public ForkJoinExample(int l, int r) {
        this.l = l;
        this.r = r;
    }

    @Override
    protected Integer compute() {
        int res = 0;
        //注意必须有递归终止条件，这里 r-l <= 10 时要进行计算
        if (r - l <= 10) {
            for (int i = l; i <= r; i++) {
                res += i;
            }
        } else {
            int mid = (l + r) / 2;
            ForkJoinExample lSum = new ForkJoinExample(l, mid);
            ForkJoinExample rSum = new ForkJoinExample(mid + 1, r);
            lSum.fork();
            rSum.fork();
            res += lSum.join() + rSum.join();
        }
        return res;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool fkp = new ForkJoinPool();
        Future res = fkp.submit(new ForkJoinExample(1, 100));
        System.out.println(res.get());
    }
}