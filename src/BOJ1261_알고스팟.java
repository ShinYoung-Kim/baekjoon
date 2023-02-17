import java.io.*;
import java.util.StringTokenizer;

public class BOJ1261_알고스팟 {
    static int r, c;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        c = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            String string = br.readLine();
            for (int j = 0; j < c; j++) {
                if (string.charAt(j) == '1') {
                    map[i][j] = 1;
                }
            }
        }

        boolean[][] visited = new boolean[r][c];
        visited[0][0] = true;

        dfs(0, 0, 0, visited);

        System.out.println(answer);
    }

    private static void dfs(int x, int y, int count, boolean[][] visited) {
        if (x == r - 1 && y == c - 1) {
            answer = Math.min(count, answer);
        }

        for (int i = 0; i < 4; i++) {
            int tempX = dx[i] + x;
            int tempY = dy[i] + y;
            if (isInRange(tempX, tempY) && !visited[tempX][tempY]) {
                visited[tempX][tempY] = true;
                dfs(tempX, tempY, count + map[tempX][tempY], visited);
                visited[tempX][tempY] = false;
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        if (0 <= x && x < r && 0 <= y && y < c) {
            return true;
        }

        return false;
    }
}
