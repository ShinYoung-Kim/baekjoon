import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1759_암호만들기 {
    static int l;
    static int c;
    static String[] array;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        l = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        array = br.readLine().split(" ");
        Arrays.sort(array);

        boolean[] visited = new boolean[c];
        dfs(0, 0, "", visited);
    }

    private static void dfs(int depth, int index, String string, boolean[] visited) {
        if (depth == l) {
            if (checkString(string)) {
                System.out.println(string);
                return;
            }
        }

        for (int i = index; i < c; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i + 1, string + array[i], visited);
                visited[i] = false;
            }
        }
    }

    private static boolean checkString(String string) {
        int number =0;
        for (int i = 0; i < l; i++) {
            if ("aeiou".contains(String.valueOf(string.charAt(i)))) {
                number += 1;
            }
        }

        if (number >= 1 && (l - number) >= 2) {
            return true;
        }

        return false;
    }
}
