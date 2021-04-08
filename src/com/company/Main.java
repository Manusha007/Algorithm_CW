package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Manusha Pamoda w1790142

public class Main {
    public static int[][] grphe;
    public static void main(String[] args) {
        grphe = file_Reder();
        menu();
    }

    private static int[][] file_Reder() {
        ArrayList<String> dinput = new ArrayList<>();
        System.out.println("Getting data from file");
        try {
            File ob = new File("benchmarks/bridge_1.txt");
            Scanner sc1 = new Scanner(ob);
            while (sc1.hasNextLine()){
                String data = sc1.nextLine();
                dinput.add(data);
            }
            sc1.close();

        }catch (FileNotFoundException e0){
            System.out.println("Error,File can not be Found");
        }
        if (dinput.size() !=0){
            return genarete_graph(dinput);
        }else {
            return null;
        }
    }

    private static int[][] genarete_graph(ArrayList<String> dInput) {

        int grph_Size = Integer.parseInt(dInput.get(0).trim());
        int[][] graph = new int[grph_Size][grph_Size];
        System.out.println("making Matrix");
        for (int row = 0; row < grph_Size; row++) {
            for (int column = 0; column < grph_Size; column++) {
                graph[row][column] = 0;
            }
        }
        for (int item = 1; item < dInput.size(); item++) {
            String[] split_item = dInput.get(item).split(" ");
            int p = Integer.parseInt(split_item[0].trim());
            int q = Integer.parseInt(split_item[1].trim());
            int value = Integer.parseInt(split_item[2].trim());
            addEdge(p, q, value, graph);
        }
        return graph;
    }


    private static void menu() {

        System.out.println();
        if (grphe !=null){
            System.out.println("--Matrix for graph--");
            display_Graph(grphe);
            System.out.println();
            System.out.println("Max Flow - " + MaxFlow.fordFulkerson(grphe, 0,  grphe.length- 1));
        }
    }

    private static void addEdge(int x, int y, int value, int[][] graph) {
        graph[x][y] = value;
    }


    private static void display_Graph(int[][] grph) {

        int num_edges =0;
        for (int[] DataRow : grph){ for (int index =0;index< grph.length; index++){
            if (DataRow[index] >0){
                num_edges++;
            }
            System.out.println(DataRow[index] + "");
        }
            System.out.println();
        }
        System.out.println("Total_Edges_present : " + num_edges );
        System.out.println("The_Source_Node : 0 ");
        System.out.println("The_Target_Node : " + (grph.length - 1));
    }
}
