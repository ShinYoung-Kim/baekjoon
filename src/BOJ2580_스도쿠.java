import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2580_스도쿠 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int[][] map = new int[9][9];
        ArrayList<int[]> zeroPointList = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 0) {
                    zeroPointList.add(new int[]{i, j});
                }
            }
        }

        dfs(0, map, zeroPointList);

    }

    private static void dfs(int depth, int[][] map, ArrayList<int[]> zeroPointList) {
        if (depth == zeroPointList.size()) {
            printMap(map);
            System.exit(0);
        }

        int[] zeroPoint = zeroPointList.get(depth);
        int x = zeroPoint[0];
        int y = zeroPoint[1];

        for (int i = 1; i <= 9; i++) {
            if (checkMap(map, x, y, i)) {
                map[x][y] = i;
                dfs(depth + 1, map, zeroPointList);
                map[x][y] = 0;
            }
        }

    }

    private static void printMap(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean checkMap(int[][] map, int x, int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (map[x][i] == value) {
                return false;
            }
            if (map[i][y] == value) {
                return false;
            }
        }

        int tempX = x / 3;
        int tempY = y / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[tempX * 3 + i][tempY * 3 + j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
