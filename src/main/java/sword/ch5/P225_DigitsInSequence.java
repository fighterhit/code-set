package sword.ch5;

/**
 * @author Fighter.
 */
public class P225_DigitsInSequence {
    public int getDigitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }
        int place = 1;
        while (true) {
            int numbers = countOfIntegers(place);
            if (index < numbers * place) {
                return getDigitAtIndex(index, place);
            }
            index -= numbers * place;
            place++;
        }
    }

    private int getDigitAtIndex(int index, int place) {
        //某位数的起始数是多少，如1位的起始数是0，2位数的起始数是100
        int beginNumber = getBeginNumber(place);
        int shiftNumber = index / place;
        String num = beginNumber + shiftNumber + "";
        int count = index % place;
        return num.charAt(count) - '0';
    }

    private int getBeginNumber(int place) {
        //注意个位
        if (place == 1) {
            return 0;
        }
        return (int) Math.pow(10, place - 1);
    }

    //返回几位数都有几个
    private int countOfIntegers(int place) {
        //注意个位
        if (place == 1) {
            return 10;
        }
        return (int) (9 * Math.pow(10, place - 1));
    }

    public static void main(String[] args) {
        for (int i = 9; i < 16; i++) {
            System.out.println(new P225_DigitsInSequence().getDigitAtIndex(i));
        }
        System.out.println(new P225_DigitsInSequence().getDigitAtIndex(1001));
    }
}
