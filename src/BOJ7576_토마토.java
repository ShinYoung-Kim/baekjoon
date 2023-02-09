import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_토마토 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        int changedNumber = 0;
        int minusNumber = 0;
        Queue<int[]> queue = new LinkedList<>();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                String string = stringTokenizer.nextToken();
                if (string.equals("1")) {
                    map[i][j] = 1;
                    changedNumber += 1;
                    queue.add(new int[]{i, j});
                } else if (string.equals("-1")) {
                    minusNumber += 1;
                    map[i][j] = -1;
                }
            }
        }

        final int GOAL_NUMBER = n * m - minusNumber;
        //System.out.println(GOAL_NUMBER);

        if (GOAL_NUMBER == changedNumber) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            Queue<int[]> nextQueue = new LinkedList<>();

            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                for (int i = 0; i < 4; i++) {
                    int tempX = x + dx[i];
                    int tempY = y + dy[i];
                    if (0 <= tempX && tempX <= n - 1 && 0 <= tempY && tempY <= m - 1 && map[tempX][tempY] == 0) {
                        nextQueue.add(new int[]{tempX, tempY});
                        changedNumber += 1;
                        map[tempX][tempY] = 1;
                    }
                }
            }

            queue = nextQueue;
            answer += 1;
        }

        if (changedNumber != GOAL_NUMBER) {
            System.out.println(-1);
        } else {
            System.out.println(answer - 1);
        }
    }
}
