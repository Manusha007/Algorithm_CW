package com.company;

import java.lang.*;
import java.util.LinkedList;

//Manusha Pamoda w1790142

public class MaxFlow {

    static boolean BFS(int[][] resGrph, int s, int t, int[] prnt, int Vertices)
    {
        boolean visited[] = new boolean[Vertices];
        for (int i = 0; i < Vertices; ++i)
            visited[i] = false;
        LinkedList<Integer> queue_1 = new LinkedList<Integer>();
        queue_1.add(s);
        visited[s] = true;
        prnt[s] = -1;

        while (queue_1.size() != 0) {
            int u = queue_1.poll();
            for (int v = 0; v < Vertices; v++) {
                if (visited[v] == false && resGrph[u][v] > 0) {
                    if (v == t) {
                        prnt[v] = u;
                        return true;
                    }
                    queue_1.add(v);
                    prnt[v] = u;
                    visited[v] = true;
                }
            }
        }
        return false;}

    public static int fordFulkerson(int[][] graph, int s, int t) {
        int Vertices;
        Vertices = t + 1;
        int u, v;

        int resGrph[][] = new int[Vertices][Vertices];
        for (u = 0; u < Vertices; u++)
            for (v = 0; v < Vertices; v++)
                resGrph[u][v] = graph[u][v];

        int prnt[] = new int[Vertices];
        int max_flow = 0; // There is no flow initially
        long start = System.currentTimeMillis();

        while (BFS(resGrph, s, t, prnt, Vertices)) {
            // Find minimum residual capacity of the edges
            // along the path filled by BFS. Or we can say
            // find the maximum flow thro]]ugh the path found.

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = prnt[v]) {
                u = prnt[v];
                path_flow = Math.min(path_flow, resGrph[u][v]);
            }

            for (v = t; v != s; v = prnt[v]) {
                u = prnt[v];
                resGrph[u][v] -= path_flow;
                resGrph[v][u] += path_flow;
            }
            System.out.print("\nAugmnt path : " + s);
            for (int i = 0; i < prnt.length; i++) {
                if (prnt[i] != -1 && prnt[i] != 0) {
                    System.out.print("-->" + prnt[i]);

                }

            }
            System.out.print("-->" + t);

            System.out.println("\nFlow  carried by this path : " + path_flow);
            System.out.println(" max flow is now : " + max_flow + " + " + path_flow + " = " + (max_flow + path_flow));
            max_flow += path_flow;
        }
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        System.out.println("\nElapsed time : " + elapsed + " seconds");
        // Return the overall flow
        return max_flow;
    }
}