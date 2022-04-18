package easyExcel;

import java.util.*;

/**
 * @author Yuh Z
 * @date 4/15/22
 */
public class Main {
    public static int result;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node root = new Node();
        List<Node> list = new ArrayList<>();
        list.add(root);
        for (int i = 0; i < n - 1; i++) {
            int parent = scanner.nextInt() - 1;
            Node parentNode = list.get(parent);
            Node node = new Node();
            if (parentNode.left == null) {
                parentNode.left = node;
            } else {
                parentNode.right = node;
            }
            node.parent = parentNode;
            list.add(node);
        }
        for (int i = 0; i < n; i++) {
            Node node = list.get(i);
            node.val = scanner.nextInt();
        }
        int visitTime = scanner.nextInt();
        int[][] visit = new int[visitTime][2];
        for (int i = 0; i < visitTime; i++) {
            visit[i][0] = scanner.nextInt() - 1;
        }
        for (int i = 0; i < visitTime; i++) {
            visit[i][1] = scanner.nextInt() - 1;
        }
        List<Integer> route = new ArrayList<>();
        for (int i = 0; i < visit.length; i++) {
            result = 0;
            bfs(route, list.get(visit[i][0]), list.get(visit[i][1]));
            System.out.println(result^0);
        }
    }

    public static boolean bfs(List<Integer> route, Node node, Node target) {
        if (node == target) {
            result ^= target.val;
            return true;
        }
        route.add(node.val);
        result ^= node.val;
        node.color = 1;
        if (node.parent != null && node.parent.color == 0) {
            node.parent.color = 1;
            if (bfs(route, node.parent, target)) {
                Integer remove = route.remove(route.size() - 1);
                node.color = 0;
                node.parent.color = 0;
                return true;
            } else {
                node.parent.color = 0;
            }
        }
        if (node.left != null && node.left.color == 0) {
            node.left.color = 1;
            if (bfs(route, node.left, target)) {
                Integer remove = route.remove(route.size() - 1);
                node.color = 0;
                node.left.color = 0;
                return true;
            } else {
                node.left.color = 0;
            }
        }
        if (node.right != null && node.right.color == 0) {
            node.right.color = 1;
            if (bfs(route, node.right, target)) {
                Integer remove = route.remove(route.size() - 1);
                node.color = 0;
                node.right.color = 0;
                return true;
            } else {
                node.right.color = 0;
            }
        }
        node.color = 0;
        Integer remove = route.remove(route.size() - 1);
        result ^= remove;
        return false;
    }

    public static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        int color = 0;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }
    }
}
