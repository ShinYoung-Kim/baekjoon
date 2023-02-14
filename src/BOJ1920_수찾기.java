import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920_수찾기 {
    public static void main (String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] nArray = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            nArray[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int m = Integer.parseInt(bufferedReader.readLine());
        StringBuilder sb = new StringBuilder();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        Arrays.sort(nArray);

        for (int i = 0; i < m; i++) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            if (binarySearch(nArray, number)) {
                sb.append("1").append("\n");
            } else {
                sb.append("0").append("\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean binarySearch(int[] array, int number) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int index = (left + right) / 2;
            if (array[index] > number) {
                right = index - 1;
            } else if (array[index] < number) {
                left = index + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
