import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ1644_소수의연속합 {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        boolean[] prime = new boolean[n + 1];
        prime[0] = prime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= n; j+= i) {
                    prime[j] = true;
                }
            }
        }

        ArrayList<Integer> primeNumberList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!prime[i]) {
                primeNumberList.add(i);
            }
        }

        int count = 0;
        int primeNumberCount = primeNumberList.size();

        for (int i = 0; i < primeNumberCount; i++) {
            int index = i;
            int sum = primeNumberList.get(index);
            while (sum <= n) {
                if (sum == n) {
                    count += 1;
                    break;
                } else {
                    index += 1;
                    if (index == primeNumberCount) {
                        break;
                    }
                    sum += primeNumberList.get(index);
                }
            }
        }

        //System.out.println(Arrays.toString(sumArray));
        System.out.println(count);
    }

}
