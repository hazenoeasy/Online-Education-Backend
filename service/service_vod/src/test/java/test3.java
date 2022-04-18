import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yuh Z
 * @date 3/15/22
 */
public class test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.valueOf(s[0]);
        int m = Integer.valueOf(s[1]);
        int k = Integer.valueOf(s[2]);
        int[] profit = new int[n];
        String[] s2 = bufferedReader.readLine().split(" ");
        for (int i = 0; i < profit.length; i++) {
            profit[i] = Integer.valueOf(s2[i]);
        }
        int result = 0;
        for (int i = 0; i < profit.length; i++) {
            int sum = 0;
            int counter = 0;
            for (int j = i; j < profit.length; j++) {
                sum += profit[j];
                if (sum < 0) counter++;
                if (counter == m) break;
                if (sum > k) break;
                if (sum == k ){
                    System.out.println(k);
                    return;
                }
                result = Math.max(result, sum);
            }
        }
        System.out.println(result);

    }
}
