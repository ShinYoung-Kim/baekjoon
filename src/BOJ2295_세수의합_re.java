import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class BOJ2295_세수의합_re {
    static int[] array;
    static int number = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(array);

        HashSet<Integer> sumSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int before = array[i];
                int after = array[j];

                int sum = before + after;

                sumSet.add(sum);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int before = array[i];
                int after = array[j];
                int sub = after - before;

                if (sumSet.contains(sub)) {
                    number = Math.max(number, sub + before);
                }
            }
        }

        //System.out.println(sumSet);
        System.out.println(number);

    }
}