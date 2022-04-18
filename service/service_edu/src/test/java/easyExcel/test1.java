package easyExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");
        int m = Integer.valueOf(s1[0]);
        int n = Integer.valueOf(s1[1]);
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            String s2 = scanner.nextLine();
            for (int j = 0; j < s2.length(); j++) {
                map[i][j] = s2.charAt(j);
            }
        }
        boolean rB = bfs(map,'r');
        boolean pB = bfs(map,'p');
        if(rB) System.out.println("kou");
        else if(pB) System.out.println("yukari");
        else System.out.println("to be continued");

    }

    public static boolean bfs(char[][] map, char c) {
        int[][][] val = new int[map.length][map[0].length][3];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]==c){
                    val[i][j][0]=1;
                    val[i][j][1]=1;
                    val[i][j][2]=1;
                    if(i>1){
                        val[i][j][0]=val[i-1][j][0]+1;
                        if(val[i][j][0]>=5) return true;

                    }
                    if(j>1){
                        val[i][j][1]=val[i][j-1][1]+1;
                        if(val[i][j][1]>=5) return true;

                    }
                    if(i>1 && j>1){
                        val[i][j][2]=val[i-1][j-1][2]+1;
                        if(val[i][j][2]>=5) return true;
                    }
                }
            }
        }
        return false;
    }
}