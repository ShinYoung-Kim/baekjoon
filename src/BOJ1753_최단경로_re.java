import java.io.*;
import java.util.*;

public class BOJ1753_최단경로_re {
    static int[] distance;
    static ArrayList[] adjList;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());
        int startNode = Integer.parseInt(br.readLine()) - 1;
        adjList = new ArrayList[v];
        distance = new int[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int end = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int value = Integer.parseInt(stringTokenizer.nextToken());

            adjList[start].add(new int[]{value, end});
        }
        for (int i = 0; i < v; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        dijkstra(startNode);

        for (int i = 0; i < v; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    private static void dijkstra(int start) {
        distance[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        pq.add(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int value = temp[0];
            int point = temp[1];

            if (value > distance[point]) {
                continue;
            }

            int count = adjList[point].size();
            for (int i = 0; i < count; i++) {
                int[] next = (int[]) adjList[point].get(i);
                int nextValue = next[0];
                int nextPoint = next[1];

                if (distance[nextPoint] > value + nextValue) {
                    distance[nextPoint] = value + nextValue;
                    pq.add(new int[]{distance[nextPoint], nextPoint});
                }
            }
        }
    }
}
