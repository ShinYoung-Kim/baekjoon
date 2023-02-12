import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2133_타일채우기 {
     public static void main(String[] args) throws Exception {
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
          int input = Integer.parseInt(bufferedReader.readLine());

          if (input % 2 != 0) {
               System.out.println(0);
               return;
          }

          int[] dp = new int[input + 1];
          dp[2] = 3;

          for (int i = 4; i < input + 1; i += 2) {
               dp[i] += dp[i - 2] * 3;
               dp[i] += 2;
               for (int j = i - 4; j >= 0; j--) {
                    dp[i] += dp[j] * 2;
               }
          }

          System.out.println(dp[input]);
     }
}
