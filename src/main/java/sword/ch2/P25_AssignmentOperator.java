package sword.ch2;

/**
 *
 *
 * @author Fighter Created on 2018/4/18.
 */
public class P25_AssignmentOperator {
    static class CMyString {
        private String data;

        public CMyString(String data) {
            this.data = data;
        }

        public CMyString assign(final CMyString another) {
            if (this == another || this.data.equals(another.data)) {
                return this;
            } else {
                this.data = another.data;
                return this;
            }
        }

        @Override
        public String toString() {
            return "CMyString data is :" + data;
        }
    }

    public static void main(String[] args) {
        CMyString s1 = new CMyString("a");
        CMyString s2 = new CMyString("b");
        CMyString s3 = new CMyString("c");
        System.out.println(s1.assign(s2).assign(s3));
        System.out.println("s1:" + s1);
        System.out.println("s2:" + s2);
        System.out.println("s3:" + s3);
    }
}
