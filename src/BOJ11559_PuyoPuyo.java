import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559_PuyoPuyo {
    static final int HEIGHT = 12;
    static final int WIDTH = 6;
    static ArrayList<int[]> connectedPoint;
    static ArrayList<int[]> puyoList;
    static int[][] map;
    static boolean[][] visited = new boolean[HEIGHT][WIDTH];
    static int answer = 0;
    static ArrayList<Integer> bottomList;
     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         map = new int[HEIGHT][WIDTH];
         connectedPoint = new ArrayList<>();
         puyoList = new ArrayList<>();
         bottomList = new ArrayList<>();

         for (int i = 0; i < HEIGHT; i++) {
             String string = br.readLine();
             for (int j = 0; j < WIDTH; j++) {
                 switch(string.charAt(j)) {
                     case '.':
                         map[i][j] = 0;
                         break;
                     case 'R':
                         map[i][j] = 1;
                         break;
                     case 'G':
                         map[i][j] = 2;
                         break;
                     case 'B':
                         map[i][j] = 3;
                         break;
                     case 'P':
                         map[i][j] = 4;
                         break;
                     case 'Y':
                         map[i][j] = 5;
                         break;
                 }
             }
         }

         while (true) {
             puyoCheck();

             for (int i = 0; i < puyoList.size(); i++) {
                 int[] puyo = puyoList.get(i);
                 bfs(puyo[0], puyo[1]);
             }
             puyoList.clear();

             clearPuyo();
             checkBottom();
             if (bottomList.isEmpty()) {
                 System.out.println(answer);
                 return;
             }
             gravity();
         }
     }

     private static void puyoCheck() {
         for (int i = 0; i < HEIGHT; i++) {
             for (int j = 0; j < WIDTH; j++) {
                 if (map[i][j] != 0) {
                     puyoList.add(new int[]{i, j});
                     visited[i][j] = false;
                 } else {
                     visited[i][j] = true;
                 }
             }
         }
     }

     private static void gravity() {
         for (int column:bottomList) {
             Queue<Integer> queue = new LinkedList<>();
             for (int i = HEIGHT - 1; i >= 0; i--) {
                 if (map[i][column] != 0) {
                     queue.add(map[i][column]);
                 }
             }

             clearColumn(column);
             int index = HEIGHT - 1;

             while (!queue.isEmpty()) {
                 map[index][column] = queue.poll();
                 index -= 1;
             }
         }
         bottomList.clear();
     }

     private static void clearColumn(int column) {
         for (int i = 0; i < HEIGHT; i++) {
             map[i][column] = 0;
         }
     }

     private static void clearPuyo() {
         if (!connectedPoint.isEmpty()) {
             for (int[] connection: connectedPoint) {
                 int x = connection[0];
                 int y = connection[1];
                 map[x][y] = 0;
             }

             connectedPoint.clear();
             answer += 1;
         }
     }

     private static void checkBottom() {
         for (int i = 0; i < HEIGHT - 1; i++) {
             for (int j = 0; j < WIDTH; j++) {
                 if (map[i][j] != 0 && map[i + 1][j] == 0) {
                     bottomList.add(j);
                 }
             }
         }
     }

     private static void bfs(int x, int y) {
         Queue<int[]> queue = new LinkedList<>();
         Queue<int[]> tempQueue = new LinkedList<>();
         queue.add(new int[]{x, y});
         tempQueue.add(new int[]{x, y});
         visited[x][y] = true;
         int[] dx = {1, -1, 0, 0};
         int[] dy = {0, 0, 1, -1};
         int count = 1;

         while(!queue.isEmpty()) {
             int[] point = queue.poll();
             int pointX = point[0];
             int pointY = point[1];
             int color = map[pointX][pointY];

             for (int i = 0; i < 4; i++) {
                 int tempX = pointX + dx[i];
                 int tempY = pointY + dy[i];

                 if (0 > tempX || tempX > HEIGHT - 1 || 0 > tempY || tempY > WIDTH - 1) {
                     continue;
                 }
                 if (!visited[tempX][tempY] && map[tempX][tempY] == color) {
                     queue.add(new int[]{tempX, tempY});
                     tempQueue.add(new int[]{tempX, tempY});
                     visited[tempX][tempY] = true;
                     count += 1;
                 }
             }
         }

         if (count >= 4) {
             for (int i = 0; i < count; i++) {
                 int[] temp = tempQueue.poll();
                 connectedPoint.add(temp);
             }
         }
     }
}
