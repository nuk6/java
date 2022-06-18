package com.queue;

import java.util.*;

public class QueueDemo {
    static ArrayList<Integer> bfsOfGraphs(int n, ArrayList<ArrayList<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0); // add throws but offer doesn't
        ArrayList<Integer> ans = new ArrayList<>(17);
        Map<Integer, Boolean> vis = new HashMap<>();
        int i = 0;
        while (!q.isEmpty()) {
            int curr = q.remove(); // Can use pook also. but using remove is safe ans we're making !empty check
            vis.put(curr, true);
            System.out.println("Traversing..."+curr);
            ans.add(i++, curr);
            for(int node : graph.get(curr)) {
                System.out.println("Adding nodes.."+ node);
                if(vis.get(node) == null) q.offer(node);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        ArrayList<Integer> fuckingAl = new ArrayList<>();
        fuckingAl.add(1);
        fuckingAl.add(2);
        fuckingAl.add(3);
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(fuckingAl);
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        ArrayList<Integer> ans = bfsOfGraphs(5, graph);
    }
}
