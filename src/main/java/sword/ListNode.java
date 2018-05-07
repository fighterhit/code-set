package sword;

/**
 * @author Fighter Created on 2018/4/18.
 */
public class ListNode<T> {

    public T val;
    public ListNode<T> next;

    public ListNode(T val) {
        this.val = val;
        this.next = null;
    }

    /**
     * 输出链表
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (ListNode cur = this; ; cur = cur.next) {
            if(cur == null){
                sb.deleteCharAt(sb.lastIndexOf(" ")).deleteCharAt(sb.lastIndexOf(","));
                break;
            }
            sb.append(cur.val).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
