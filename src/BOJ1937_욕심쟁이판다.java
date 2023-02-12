import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1937_욕심쟁이판다 {
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
                if (dp[i][j] == 0) {
                    dp[i][j] = bfs(i, j);
                }
                answer = Math.max(dp[i][j], answer);
            }
        }

        System.out.println(Arrays.deepToString(dp));
        System.out.println(answer);
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        int bfsValue = 0;

        while(!queue.isEmpty()) {
            Queue<int[]> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int currentX = point[0];
                int currentY = point[1];
                int currentValue = map[currentX][currentY];

                for (int i = 0; i < 4; i++) {
                    int tempX = dx[i] + currentX;
                    int tempY = dy[i] + currentY;
                    if (0 <= tempX && tempX < input && 0 <= tempY && tempY < input) {
                        if (currentValue < map[tempX][tempY]) {
                            nextQueue.add(new int[]{tempX, tempY});
                            dp[tempX][tempY] = bfsValue;
                        } else {

                        }
                    }
                }
            }
            queue = nextQueue;
            bfsValue += 1;
        }
        return bfsValue;
    }
}
