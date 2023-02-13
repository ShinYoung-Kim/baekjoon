import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ9466_텀프로젝트 {
    static boolean[] visited;
    static boolean[] isTeamed;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int studentNumber = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int[] studentArray = new int[studentNumber];
            for (int j = 0; j < studentNumber; j++) {
                studentArray[j] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            }
            count = 0;
            isTeamed = new boolean[studentNumber];
            visited = new boolean[studentNumber];
            for (int j = 0; j < studentNumber; j++) {
                if (!isTeamed[j]) {
                    dfs(studentArray, j);
                }
            }

            sb.append(String.valueOf(studentNumber - count)).append("\n");
        }

        bufferedWriter.write(String.valueOf(sb));
        bufferedWriter.close();
    }

    private static void dfs(int[] studentArray, int student) {
        if (visited[student]) {
            isTeamed[student] = true;
            count += 1;
        } else {
            visited[student] = true;
        }

        if (!isTeamed[studentArray[student]]) {
            dfs(studentArray, studentArray[student]);
        }
        visited[student] = false;
        isTeamed[student] = true;
    }
}
