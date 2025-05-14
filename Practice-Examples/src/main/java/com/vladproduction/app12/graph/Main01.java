package com.vladproduction.app12.graph;

public class Main01 {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph(7);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(3,6);
        graph.addEdge(2,5);

        graph.printGraph();
        System.out.println("---DFS---");
        MyDFS dfs = new MyDFS();
        dfs.traverseGraph(graph,0);
    }
}
