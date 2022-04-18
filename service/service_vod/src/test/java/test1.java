import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yuh Z
 * @date 3/15/22
 */
public class test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int size;
        size = Integer.valueOf(bufferedReader.readLine());
        String[] s = bufferedReader.readLine().split(" ");
        int[] node = new int[s.length];
        for(int i=0;i<s.length;i++){
            node[i]=Integer.valueOf(s[i]);
        }
        int result =0;
        for(int i=0;i<node.length;i++){
            if((node[i]!=-1 && ((2*i+1)>=node.length && (2*i)>=node.length)) || (node[i]!=-1 && node[2*i+1]==-1 && node[2*i]==-1 )){
                result++;
            }
        }
        System.out.println(result);
        //return result;
    }
}
