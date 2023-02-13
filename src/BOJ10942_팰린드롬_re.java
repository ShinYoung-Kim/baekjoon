import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10942_팰린드롬_re {
    static int[] array;
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            if (i + 1 < n && array[i] == array[i + 1]) {
                dp[i][i + 1] = true;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                if (i + 1 < n && 0 <= j - 1 &&dp[i + 1][j - 1] && array[j] == array[i]) {
                    dp[i][j] = true;
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            if (dp[start][end]) {
                sb.append("1").append("\n");
            } else {
                sb.append("0").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean answer(int start, int end) {
        int index = 0;
        while (start + index < end - index) {
            if (array[start + index] != array[end - index]) {
                return false;
            }
            index += 1;
        }
        return true;
    }
}
