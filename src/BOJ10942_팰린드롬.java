import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942_팰린드롬 {
    static int[] array;
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int m = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            if (answer(start, end)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static boolean answer(int start, int end) {
        int index = 0;
        while (start + index < end - index) {
            if (array[start + index] != array[end - index]) {
                return false;
            }
            index += 1;
        }
        return true;
    }
}
