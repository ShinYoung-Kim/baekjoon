import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class BOJ2011_암호코드 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = bufferedReader.readLine();
        if (string.startsWith("0")) {
            System.out.println(0);
            return;
        } else if (string.contains("00")) {
            System.out.println(0);
            return;
        }

        int[] array = Stream.of(string.split("")).mapToInt(Integer::parseInt).toArray();
        int answer = array.length;
        int[] dp1 = new int[answer];
        int[] dp2 = new int[answer];

        //if (array[answer - 1] != 0) {

        //}
        dp1[answer - 1] = 1;

        for (int i = answer - 2; i >= 0; i--) {
            if (10 <= array[i] * 10 + array[i + 1] && array[i] * 10 + array[i + 1] <= 26) {
                dp2[i] = dp1[i + 1] % 1000000;
            } else {
                dp2[i] = 0;
                if (array[i + 1] == 0) {
                    System.out.println(0);
                    return;
                }
            }
            if (array[i] == 0) {
                dp1[i] = dp1[i + 1] + dp2[i + 1];
                dp2[i] = 0;
            } else if (array[i + 1] == 0) {
                dp1[i] = 0;
                dp2[i] = dp1[i + 1];
            } else {
                dp1[i] = (dp1[i + 1] + dp2[i + 1]) % 1000000;
            }

        }
        //System.out.println(Arrays.toString(dp1));
        //System.out.println(Arrays.toString(dp2));
        System.out.println((dp1[0] + dp2[0]) % 1000000);
    }
}
