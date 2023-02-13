import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15683_감시 {
    static int n, m;
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static int count;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        ArrayList<int[]> cctvList = new ArrayList<>();
        ArrayList<int[]> wallList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                int temp = map[i][j];
                if (temp > 0) {
                    if (temp == 6) {
                        wallList.add(new int[]{i, j});
                    } else {
                        cctvList.add(new int[]{i, j, temp});
                    }
                }
            }
        }

        count = cctvList.size();
        dfs(0, map, cctvList);
        System.out.println(answer);
    }

    private static void mappingCCTV(int x, int y, int cctv, int direction, int[][] map) {
        if (direction == UP) {
            for (int i = 0; i < n; i++) {
                if (x - i >= 0) {
                    if (map[x - i][y] == 6) {
                        break;
                    } else if (map[x - i][y] == 0){
                        map[x - i][y] = cctv;
                    }
                }
            }
        } else if (direction == DOWN) {
            for (int i = 0; i < n; i++) {
                if (x + i < n) {
                    if (map[x + i][y] == 6) {
                        break;
                    } else if (map[x + i][y] == 0){
                        map[x + i][y] = cctv;
                    }
                }
            }
        } else if (direction == LEFT) {
            for (int i = 0; i < m; i++) {
                if (y - i >= 0) {
                    if (map[x][y - i] == 6) {
                        break;
                    } else if (map[x][y - i] == 0){
                        map[x][y - i] = cctv;
                    }
                }
            }
        } else if (direction == RIGHT) {
            for (int i = 0; i < m; i++) {
                if (y + i < m) {
                    if (map[x][y + i] == 6) {
                        break;
                    } else if (map[x][y + i] == 0){
                        map[x][y + i] = cctv;
                    }
                }
            }
        }
    }

    private static void dfs(int depth, int[][] map, ArrayList<int[]> cctvList) {
        if (depth == count) {
            answer = Math.min(answer, countZero(map));
            return;
        }

        int[] temp = cctvList.get(depth);
        int x = temp[0];
        int y = temp[1];
        int cctv = temp[2];
        int[][] tempMap;
        if (cctv == 5) {
            tempMap = copyMap(map);
            mappingCCTV(x, y, 5, UP, tempMap);
            mappingCCTV(x, y, 5, DOWN, tempMap);
            mappingCCTV(x, y, 5, LEFT, tempMap);
            mappingCCTV(x, y, 5, RIGHT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

        } else if (cctv == 4) {
            tempMap = copyMap(map);
            mappingCCTV(x, y, 4, UP, tempMap);
            mappingCCTV(x, y, 4, DOWN, tempMap);
            mappingCCTV(x, y, 4, LEFT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 4, UP, tempMap);
            mappingCCTV(x, y, 4, DOWN, tempMap);
            mappingCCTV(x, y, 5, RIGHT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 4, LEFT, tempMap);
            mappingCCTV(x, y, 4, DOWN, tempMap);
            mappingCCTV(x, y, 4, RIGHT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 4, LEFT, tempMap);
            mappingCCTV(x, y, 4, UP, tempMap);
            mappingCCTV(x, y, 4, RIGHT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

        } else if (cctv == 3) {
            tempMap = copyMap(map);
            mappingCCTV(x, y, 3, LEFT, tempMap);
            mappingCCTV(x, y, 3, UP, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 3, RIGHT, tempMap);
            mappingCCTV(x, y, 3, UP, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 3, LEFT, tempMap);
            mappingCCTV(x, y, 3, DOWN, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 3, RIGHT, tempMap);
            mappingCCTV(x, y, 3, DOWN, tempMap);
            dfs(depth + 1, tempMap, cctvList);

        } else if (cctv == 2) {

            tempMap = copyMap(map);
            mappingCCTV(x, y, 2, LEFT, tempMap);
            mappingCCTV(x, y, 2, RIGHT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 2, UP, tempMap);
            mappingCCTV(x, y, 2, DOWN, tempMap);
            dfs(depth + 1, tempMap, cctvList);


        } else if (cctv == 1) {
            tempMap = copyMap(map);
            mappingCCTV(x, y, 1, LEFT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 1, DOWN, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 1, RIGHT, tempMap);
            dfs(depth + 1, tempMap, cctvList);

            tempMap = copyMap(map);
            mappingCCTV(x, y, 1, UP, tempMap);
            dfs(depth + 1, tempMap, cctvList);
        }
    }

    private static int countZero (int[][] map) {
        int zeroNum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    zeroNum += 1;
                }
            }
        }
        return zeroNum;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] tempMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        return tempMap;
    }
}
