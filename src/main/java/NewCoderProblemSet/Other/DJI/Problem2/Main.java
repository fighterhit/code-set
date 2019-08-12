package NewCoderProblemSet.Other.DJI.Problem2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int cmdN = sc.nextInt(), debugM = sc.nextInt();
            Map<String, String> cmd2action = new HashMap<>();
            for (int i = 0; i < cmdN; i++) {
                String cmd = sc.next(), action = sc.next();
                cmd2action.put(cmd, action);
            }
            for (int i = 0; i < debugM; i++) {
                String cmd = sc.next();
                System.out.println(cmd2action.get(cmd));
            }
        }
    }
}
