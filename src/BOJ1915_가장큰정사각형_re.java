import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1915_가장큰정사각형_re {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean isOneExist = false;
        for (int i = 0; i < n; i++) {
            String string = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                if (string.charAt(j) == '1') {
                    map[i][j] = 1;
                    isOneExist = true;
                }
            }
        }
        int count = 0;
        if (isOneExist) {
            count = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]) + 1;
                    count = Math.max(count, map[i][j]);
                }
            }
        }

        bw.write(String.valueOf(count * count));
        bw.close();
    }
}
