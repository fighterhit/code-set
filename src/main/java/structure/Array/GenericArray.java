package structure.Array;

import java.util.function.LongFunction;

public class GenericArray<E> {
    private E[] data;
    private int size;


    public GenericArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public GenericArray() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) throws IllegalAccessException {
        add(0, e);
    }

    public void addLast(E e) throws IllegalAccessException {
       /*
        if (size == data.length){
            throw new IllegalArgumentException("Add last faided! Array is full.");
        }
        data[size] = e;
        size++;
        */
        add(size, e);
    }

    public void add(int index, E e) throws IllegalAccessException {

        //不能向size之外空间插入元素
        if (index < 0 || index > size) {
            throw new IllegalAccessException("Add failed. Require index >= 0 and index <= size");
        }

        // 数组满了先扩容
        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        //将数组内 <size 元素拷贝到新数组内
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d ", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    public E get(int index) throws IllegalAccessException {
        if (index < 0 || index >= size) {
            throw new IllegalAccessException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    public E getFirst() throws IllegalAccessException {
        return get(0);
    }

    public E getLast() throws IllegalAccessException {
        return get(size - 1);
    }

    public void set(int index, E e) throws IllegalAccessException {
        if (index < 0 || index >= size) {
            throw new IllegalAccessException("Get failed. Index is illegal.");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (e == data[i]) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) throws IllegalAccessException {
        if (index < 0 || index >= size) {
            throw new IllegalAccessException("Remove failed. Index is illegal.");
        }
        E res = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //回收
        data[size] = null;
        // 防止震荡，当容量降到1/4时再缩容 且避免产生容量为 0 的数组
        if (size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }

        return res;
    }

    //若为空数组，则通不过 index >= size 条件
    public E removeFirst() throws IllegalAccessException {
        return remove(0);
    }

    //若为空数组，则通不过 index > 0 条件
    public E removeLast() throws IllegalAccessException {
        return remove(size - 1);
    }

    //因为调用者知道删除哪个元素，因此不返回 int
    public void removeElement(E e) throws IllegalAccessException {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        GenericArray<Integer> myArray = new GenericArray<>(20);
        for (int i = 0; i < 10; i++) {
            myArray.addLast(i);
        }
        System.out.println(myArray);

        myArray.add(1, 100);
        System.out.println(myArray);

        myArray.addFirst(-1);
        System.out.println(myArray);
        //    [-1,0,100,1,2,3,4,5,6,7,8,9]
        myArray.remove(2);
        System.out.println(myArray);

        myArray.removeElement(7);
        System.out.println(myArray);

        myArray.removeFirst();
        System.out.println(myArray);
    }
}

