import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ15486_퇴사2 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] array = new int[n][2];
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int length = Integer.parseInt(stringTokenizer.nextToken());
            int price = Integer.parseInt(stringTokenizer.nextToken());
            array[i] = new int[]{length, price};
        }

        for (int i = n - 1; i >= 0; i--) {
            if (array[i][0] + i <= n) {
                dp[i] = dp[array[i][0] + i] + array[i][1];
                if (dp[i] < dp[i + 1]) {
                    dp[i] = dp[i + 1];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[0]);

    }
}
