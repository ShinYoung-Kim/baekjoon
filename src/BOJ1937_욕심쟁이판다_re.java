import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1937_욕심쟁이판다_re {
    static int[][] map;
    static int input;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        input = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[input][input];
        int answer = 0;

        for (int i = 0; i < input; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < input; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        dp = new int[input][input];

        for (int i = 0; i < input; i++) {
            for (int j = 0; j < input; j++) {
                dp[i][j] = dfs(i, j);
                answer = Math.max(dp[i][j], answer);
            }
        }

        //System.out.println(Arrays.deepToString(dp));
        System.out.println(answer);
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int tempX = dx[i] + x;
            int tempY = dy[i] + y;
            if (0 <= tempX && tempX < input && 0 <= tempY && tempY < input) {
                if (map[tempX][tempY] > map[x][y]) {
                    dp[x][y] = Math.max(dp[x][y], dfs(tempX, tempY) + 1);
                }
            }
        }

        return dp[x][y];
    }
}
