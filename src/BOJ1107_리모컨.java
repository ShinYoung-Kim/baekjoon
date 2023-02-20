import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1107_리모컨 {
    static int[] broken;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int channel = Integer.parseInt(br.readLine());
        int from100 = Math.abs(channel - 100);
        int brokenNumber = Integer.parseInt(br.readLine());
        broken = new int[brokenNumber];
        if (brokenNumber > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            for (int i = 0; i < brokenNumber; i++) {
                broken[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int count = Integer.MAX_VALUE;
        boolean isChanged = false;

        for (int i = 0; i <= 500000; i++) {
            int up = channel + i;
            int down = channel - i;

            if (canMakeNumber(up)) {
                count = Math.min(countNumberLength(up) + i, count);
                isChanged = true;
            }
            if (canMakeNumber(down) && down >= 0) {
                count = Math.min(countNumberLength(down) + i, count);
                isChanged = true;
            }
            if (isChanged) {
                //System.out.println(up);
                //System.out.println(down);
                break;
            }
        }

        System.out.println(Math.min(from100, count));
    }

    private static int countNumberLength(int number) {
        String string = String.valueOf(number);
        return string.length();
    }

    private static boolean canMakeNumber(int number) {
        while (number >= 0) {
            int lastNumber = number % 10;
            for (int i = 0; i < broken.length; i++) {
                if (lastNumber == broken[i]) {
                    return false;
                }
            }
            number /= 10;
            if (number == 0) {
                break;
            }
        }

        return true;
    }
}
