package NewCoderProblemSet.Other.Kuaishou.P1;

import java.util.Scanner;

/**
 * 牛客笔试能 AC，但过不了 leetcode
 * 参考 M468_ValidateIPAddress
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String IP = sc.nextLine();
        System.out.println(helper(IP));
    }

    public static String helper(String IP) {
        if (!IP.contains(".") && !IP.contains(":")) {
            return "Neither";
        }

        //ipv4
        if (IP.contains(".")) {
            if (IP.startsWith(".")) {
                return "Neither";

            }
            String[] arr = IP.split("\\.");
            if (arr.length != 4) {
                return "Neither";

            }
            for (int i = 0; i < 4; i++) {
                if (arr[i].length() == 0 || arr[i].length() > 3) {
                    return "Neither";

                }
                for (int j = 0; j < arr[i].length(); j++) {
                    if (arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') {
                        continue;
                    }
                    return "Neither";
                }
                if (Integer.valueOf(arr[i]) > 255 || (arr[i].length() >= 2 && String.valueOf(arr[i]).startsWith("0"))) {
                    return "Neither";

                }
            }
            return "IPv4";
        }

        //6
        if (IP.contains(":")) {
            if (IP.startsWith(":")) {
                return "Neither";
            }
            if (IP.contains("::")) {
                return "Neither";
            }
            //leetcode 该例 "2001:0db8:85a3:0:0:8A2E:0370:7334:" 满足下列条件但不是合法 IPv6，参考 M468_ValidateIPAddress 先统计分隔符个数
            String[] arr = IP.split(":");
            if (arr.length != 8) {
                return "Neither";
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() > 4) {
                    return "Neither";
                }
                for (int j = 0; j < arr[i].length(); j++) {
                    if ((arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') || (arr[i].charAt(j) >= 'A' && arr[i].charAt(j) <= 'F') ||
                            (arr[i].charAt(j) >= 'a' && arr[i].charAt(j) <= 'f')) {
                        continue;
                    }
                    return "Neither";
                }
            }
            return "IPv6";
        }
        return "Neither";
    }
}

