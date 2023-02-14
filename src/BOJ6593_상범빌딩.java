import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593_상범빌딩 {
    static int[] dx = {0, 0, 0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = null;
        StringBuilder sb = new StringBuilder();
        while (true) {
            string = bufferedReader.readLine();
            if (string.equals("0 0 0")) {
                break;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(string);
            int l = Integer.parseInt(stringTokenizer.nextToken());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            char[][][] map = new char[l][r][c];
            int[] start = new int[3];
            int[] end = new int[3];;

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    string = bufferedReader.readLine();
                    for (int k = 0; k < c; k++) {
                        map[i][j][k] = string.charAt(k);
                        if (map[i][j][k] == 'S') {
                            start = new int[]{i, j, k};
                        } else if (map[i][j][k] == 'E') {
                            end = new int[]{i, j, k};
                        }
                    }
                }
                bufferedReader.readLine();
            }

            //System.out.println(Arrays.deepToString(map));
            Queue<int[]> queue = new LinkedList<>();
            queue.add(start);
            int time = 0;
            boolean isEscaped = false;
            while (!queue.isEmpty()) {
                Queue<int[]> tempQueue = new LinkedList<>();
                time += 1;
                while (!queue.isEmpty()) {
                    int[] point = queue.poll();
                    int x = point[0];
                    int y = point[1];
                    int z = point[2];
                    for (int i = 0; i < 6; i++) {
                        int tempX = x + dx[i];
                        int tempY = y + dy[i];
                        int tempZ = z + dz[i];
                        if (0 <= tempX && tempX < l && 0 <= tempY && tempY < r && 0 <= tempZ && tempZ < c) {
                            if (map[tempX][tempY][tempZ] == '.') {
                                map[tempX][tempY][tempZ] = 'S';
                                tempQueue.add(new int[]{tempX, tempY, tempZ});
                            } else if (map[tempX][tempY][tempZ] == 'E') {
                                tempQueue.clear();
                                queue.clear();
                                isEscaped = true;
                            }
                        }
                    }
                }
                queue = tempQueue;
            }
            if (isEscaped) {
                sb.append("Escaped in ").append(String.valueOf(time)).append(" minute(s).\n");
            } else {
                sb.append("Trapped!\n");
            }
        }
        System.out.println(sb);
    }
}
