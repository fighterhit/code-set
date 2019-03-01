package sword.ch3;

/**
 * @author Fighter.
 */
public class P127_NumberStrings {
    public boolean isNumeric(char[] str) {
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
