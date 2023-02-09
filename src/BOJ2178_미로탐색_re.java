import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178_미로탐색_re {
    static int answer = Integer.MAX_VALUE;
    static int n, m;
    static boolean[][] map;
    static Queue<int[]> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        map = new boolean[n][m];
        boolean[][] visited = new boolean[n][m];
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                if (input.charAt(j) == '1') {
                    map[i][j] = true;
                }
            }
        }

        queue = new LinkedList();
        visited[0][0] = true;
        queue.add(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            int count = temp[2];

            if (x == n - 1 && y == m - 1) {
                System.out.println(count);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int tempX = x + dx[i];
                int tempY = y + dy[i];
                if (isInRange(tempX, tempY) && map[tempX][tempY] && !visited[tempX][tempY]) {
                    visited[tempX][tempY] = true;
                    queue.add(new int[]{tempX, tempY, count + 1});
                }
            }
        }

    }

    private static boolean isInRange(int x, int y) {
        if (x > n - 1 || x < 0 || y > m - 1 || y < 0) {
            return false;
        }
        return true;
    }
}
