import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1926_그림 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        boolean[][] map = new boolean[n][m];
        Stack<int[]> stack = new Stack<>();
        int wholeCount = 0;
        int number = 0;

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                if (stringTokenizer.nextToken().equals("1")) {
                    map[i][j] = true;
                    stack.push(new int[]{i, j});
                }
            }
        }

        while(!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];
            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            int count = 0;

            if (map[x][y]) {
                map[x][y] = false;
                count += 1;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{x, y});
                while (!queue.isEmpty()) {
                    int temp[] = queue.poll();
                    int currentX = temp[0];
                    int currentY = temp[1];

                    for (int i = 0; i < 4; i++) {
                        int tempX = currentX + dx[i];
                        int tempY = currentY + dy[i];
                        if (0 <= tempX && tempX <= n - 1 && 0 <= tempY && tempY <= m - 1 && map[tempX][tempY]) {
                            map[tempX][tempY] = false;
                            count += 1;
                            queue.add(new int[]{tempX, tempY});
                        }
                    }
                }
                number += 1;
                wholeCount = Math.max(wholeCount, count);
            }
        }

        System.out.println(number);
        System.out.println(wholeCount);
    }
}
