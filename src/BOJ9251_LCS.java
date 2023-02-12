import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ9251_LCS {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string1 = bufferedReader.readLine();
        String string2 = bufferedReader.readLine();

        int[][] array = new int[string1.length() + 1][string2.length() + 1];

        for (int i = 0; i < string1.length(); i++) {
            for (int j = 0; j < string2.length(); j++) {
                if (string1.charAt(i) == string2.charAt(j)) {
                    array[i + 1][j + 1] = array[i][j] + 1;
                } else {
                    array[i + 1][j + 1] = Math.max(array[i + 1][j], array[i][j + 1]);
                }
            }
        }

        System.out.println(array[string1.length()][string2.length()]);
    }
}
