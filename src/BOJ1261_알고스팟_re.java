import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261_알고스팟_re {
    static int r, c;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        c = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String string = br.readLine();
            for (int j = 0; j < c; j++) {
                if (string.charAt(j) == '1') {
                    map[i][j] = 1;
                }
            }
        }

        visited[0][0] = true;
        dijkstra(0, 0);
    }

    private static void dijkstra(int x, int y) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        pq.add(new int[]{0, 0, 0});

        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            int tempValue = temp[2];
            if (tempX == r - 1 && tempY == c - 1) {
                System.out.println(tempValue);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int currentX = tempX + dx[i];
                int currentY = tempY + dy[i];
                if (isInRange(currentX, currentY) && !visited[currentX][currentY]) {
                    visited[currentX][currentY] = true;
                    pq.add(new int[] {currentX, currentY, map[currentX][currentY] + tempValue});
                }
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
