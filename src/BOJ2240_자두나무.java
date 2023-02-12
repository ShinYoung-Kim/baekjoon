import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2240_자두나무 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int t = Integer.parseInt(stringTokenizer.nextToken());
        int w = Integer.parseInt(stringTokenizer.nextToken());
        boolean[] firstTree = new boolean[t];

        for (int i = 0; i < t; i++) {
            if (bufferedReader.readLine().equals("1")) {
                firstTree[i] = true;
            }
        }

        Stack<Integer> stack = new Stack<>();
        boolean before = true;
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            if (before != firstTree[i]) {
                before = firstTree[i];
                if (!stack.isEmpty()) {
                    indexList.add(i - stack.peek());
                } else {
                    indexList.add(i);
                }
                stack.add(i);
            }
        }

        //System.out.println(stack);
        if (stack.isEmpty()) {
            indexList.add(t - 0);
        } else {
            indexList.add(t - stack.peek());
        }

        //System.out.println(indexList);

        int[][] dp = new int[indexList.size()][w + 1];
        dp[0][0] = indexList.get(0);

        for (int i = 1; i < indexList.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (j > w) {
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }

                if ((i + j) % 2 == 0) {
                    dp[i][j] += indexList.get(i);
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        int answer = 0;
        for (int i = 0; i <= w; i++) {
            if (i % 2 == 0) {
                answer = Math.max(answer, dp[indexList.size() - 1][i]);
            } else {
                answer = Math.max(answer, dp[indexList.size() - 1][i]);
            }
        }

        System.out.println(answer);

        //1 1
        //1
    }
}
