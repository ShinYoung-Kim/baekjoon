import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ4179_불 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[r][c];
        Queue<int[]> fireQueue = new LinkedList<>();
        Queue<int[]> jihoonQueue = new LinkedList<>();
        int time = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < r; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < c; j++) {
                char character = input.charAt(j);
                map[i][j] = character;
                if (character == 'F') {
                    fireQueue.add(new int[]{i, j});
                } else if (character == 'J') {
                    jihoonQueue.add(new int[]{i, j});
                }
            }
        }

        //지훈이만 있을 때 고려하기
        while (!fireQueue.isEmpty() || !jihoonQueue.isEmpty()) {
            Queue<int[]> nextFireQueue = new LinkedList<>();
            Queue<int[]> nextJihoonQueue = new LinkedList<>();
            while (!jihoonQueue.isEmpty()) {
                int[] jihoon = jihoonQueue.poll();
                int jihoonX = jihoon[0];
                int jihoonY = jihoon[1];

                if (map[jihoonX][jihoonY] == 'F') {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int movedJihoonX = jihoonX + dx[i];
                    int movedJihoonY = jihoonY + dy[i];
                    if (0 <= movedJihoonX && movedJihoonX <= r - 1 && 0 <= movedJihoonY && movedJihoonY <= c - 1) {
                        if (map[movedJihoonX][movedJihoonY] == '.') {
                            nextJihoonQueue.add(new int[]{movedJihoonX, movedJihoonY});
                            map[movedJihoonX][movedJihoonY] = 'J';
                        }
                    } else {
                        System.out.println(time + 1);
                        return;
                    }
                }
            }

            jihoonQueue = nextJihoonQueue;

            while (!fireQueue.isEmpty()) {
                int[] fire = fireQueue.poll();
                int fireX = fire[0];
                int fireY = fire[1];

                for (int i = 0; i < 4; i++) {
                    int movedFireX = fireX + dx[i];
                    int movedFireY = fireY + dy[i];
                    if (0 <= movedFireX && movedFireX <= r - 1 && 0 <= movedFireY && movedFireY <= c - 1) {
                        if (map[movedFireX][movedFireY] == '.' || map[movedFireX][movedFireY] == 'J') {
                            map[movedFireX][movedFireY] = 'F';
                            nextFireQueue.add(new int[]{movedFireX, movedFireY});
                        }
                    }
                }

            }

            fireQueue = nextFireQueue;
            time += 1;
        }

        System.out.println("IMPOSSIBLE");
    }
}
