import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yuh Z
 * @date 3/15/22
 */
public class test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line1 = bufferedReader.readLine();
        String[] s = line1.split(" ");
        int m1 = Integer.valueOf(s[0]);
        int m2 = Integer.valueOf(s[1]);
        int[][] matrix = new int[m1][m2];
        for (int i = 0; i < m1; i++) {
            String line2 = bufferedReader.readLine();
            for (int j = 0; j < line2.length(); j++) {
                if (line2.charAt(j) == '-') {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = 1;
                }
            }
        }

        String line3 = bufferedReader.readLine();
        String[] s2 = line3.split(" ");
        int n1 = Integer.valueOf(s2[0]);
        int n2 = Integer.valueOf(s2[1]);
        int[][] matrix2 = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            String line2 = bufferedReader.readLine();
            for (int j = 0; j < line2.length(); j++) {
                if (line2.charAt(j) == '-') {
                    matrix2[i][j] = 0;
                } else {
                    matrix2[i][j] = 1;
                }
            }
        }
        int result = 0;
        for(int i=0;i<=matrix.length-matrix2.length;i++){
            for(int j=0;j<=matrix[0].length - matrix2[0].length;j++){
                boolean flag = true;
                for(int m=0;m<matrix2.length && flag;m++){
                    for(int n=0;n<matrix2[0].length && flag;n++){
                        if(matrix2[m][n]!=matrix[i+m][j+n]){
                            flag = false;
                        }
                    }
                }
                if(flag) result++;
            }
        }
        System.out.println(result);
    }
}
