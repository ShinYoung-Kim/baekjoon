import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686_치킨배달 {
    static final int CHICKEN = 2;
    static final int HOUSE = 1;
    static int m;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<int[]> houseList;
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][n];
        houseList = new ArrayList<>();
        ArrayList<int[]> chickenList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == CHICKEN) {
                    chickenList.add(new int[]{i, j});
                } else if (map[i][j] == HOUSE) {
                    houseList.add(new int[]{i, j});
                }
            }
        }

        boolean[] visited = new boolean[chickenList.size()];
        dfs(0, visited, chickenList, 0);
        System.out.println(answer);
    }

    private static int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static int calculateSmallestDistance(ArrayList<int[]> chickenList, boolean[] visited) {
        int wholeDistance = 0;
        for (int i = 0; i < houseList.size(); i++) {
            int[] housePoint = houseList.get(i);
            int houseX = housePoint[0];
            int houseY = housePoint[1];
            int houseDistance = Integer.MAX_VALUE;
            for (int j = 0; j < visited.length; j++) {
                if (visited[j]) {
                    int[] chickenPoint = chickenList.get(j);
                    int chickenX = chickenPoint[0];
                    int chickenY = chickenPoint[1];
                    houseDistance = Math.min(calculateDistance(houseX, houseY, chickenX, chickenY), houseDistance);
                }
            }
            wholeDistance += houseDistance;
        }

        return wholeDistance;
    }

    //dfs에서 이미 확인한 index를 다시 돌아갈 필요없음 잊지 말기 -> parameter로 index 받아서 다음꺼 검사
    private static void dfs(int depth, boolean[] visited, ArrayList<int[]> chickenList, int index) {
        if (depth == m) {
            answer = Math.min(answer, calculateSmallestDistance(chickenList, visited));
            return;
        }

        for (int i = index; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, visited, chickenList, i + 1);
                visited[i] = false;
            }
        }
    }
}
