package structure.PriorityAndHeap;

import java.util.Arrays;

/**
 * @author Fighter
 */
public class IndexMaxHeap<E extends Comparable> {
    //最大索引堆中的数据
    private E[] data;
    //最大索引堆中的索引
    private int[] indexes;
    // 最大索引堆中的反向索引, reverse[i] = x 表示索引i在x的位置
    private int[] reverse;
    private int count;
    private int capacity;

    public IndexMaxHeap(int capacity) {
        this.data = (E[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i < capacity; i++) {
            //0 表示不在堆中
            reverse[i] = 0;
        }
        count = 0;
        this.capacity = capacity;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }


    // 向堆中添加元素 堆得索引从1开始
    public void insert(int i, E e) {
        assert count + 1 <= capacity;
        assert i + 1 >= 0 && i + 1 <= capacity;
        //再插入一个新元素，需要保证索引i所在位置没有元素
        assert !contain(i);
        //先添加到数组末尾，再上浮
        i++;
        data[i] = e;
        indexes[count + 1] = i;
        reverse[i] = count + 1;
        count++;
        shiftUp(count);
    }

    //获取堆最大元素，将数组最后一个元素放到根再下沉
    public E extractMax() {
        assert count > 0;
        E ret = data[indexes[1]];
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count--;
        shiftDown(1);
        return ret;
    }

    //从最大索引堆中取出堆顶元素的索引
    public int extractMaxIndex() {
        assert count > 0;
        //用户端索引从0开始，故要-1
        int ret = indexes[1] - 1;
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count--;
        shiftDown(1);
        return ret;
    }

    //交换索引堆中的索引i和j
    private void swapIndexes(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;

        reverse[indexes[j]] = j;
        reverse[indexes[i]] = i;
    }

    public E getMax() throws IllegalArgumentException {
        assert count > 0;
        return data[indexes[1]];
    }

    // 获取最大索引堆中的堆顶元素的索引
    public int getMaxIndex() {
        assert count > 0;
        return indexes[1] - 1;
    }

    boolean contain(int i) {
        assert i + 1 >= 1 && i + 1 <= capacity;
        return reverse[i + 1] != 0;
    }

    // 获取最大索引堆中索引为i的元素
    public E getItem(int i) {
        assert contain(i);
        return data[i + 1];
    }

    // 将最大索引堆中索引为i的元素修改为newItem
    public void change(int i, E newItem) {
        assert contain(i);

        i += 1;
        data[i] = newItem;

        // 找到indexes[j] = i, j表示data[i]在堆中的位置
        // 之后shiftUp(j), 再shiftDown(j)
        /*for (int j = 1; j <= count; j++)
            if (indexes[j] == i) {
                shiftUp(j);
                shiftDown(j);
                return;
            }*/
        //利用reverse数组优化
        shiftDown(reverse[i]);
        shiftDown(reverse[i]);
    }

    //下沉
    private void shiftDown(int i) throws IllegalArgumentException {
        //先判断有没有左孩子
        while (2 * i <= count) {
            int j = 2 * i;
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
                j++;
            }
            if (data[indexes[i]].compareTo(data[indexes[j]]) < 0) {
                swapIndexes(i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    //上浮
    private void shiftUp(int index) {
        while (index > 1 && data[indexes[index / 2]].compareTo(data[indexes[index]]) < 0) {
            swapIndexes(index, index / 2);
            index = index / 2;
        }
    }

    // 测试索引堆中的索引数组index
    // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    public boolean testIndexes() {

        int[] copyIndexes = new int[count + 1];

        for (int i = 0; i <= count; i++)
            copyIndexes[i] = indexes[i];

        copyIndexes[0] = 0;
        Arrays.sort(copyIndexes);

        // 在对索引堆中的索引进行排序后, 应该正好是1...count这count个索引
        boolean res = true;
        for (int i = 1; i <= count; i++)
            if (copyIndexes[i - 1] + 1 != copyIndexes[i]) {
                res = false;
                break;
            }

        if (!res) {
            System.out.println("Error!");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int N = 1000000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(N);
        for (int i = 0; i < N; i++)
            indexMaxHeap.insert(i, (int) (Math.random() * N));
        assert indexMaxHeap.testIndexes();
    }

}