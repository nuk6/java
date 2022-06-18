package com.interfaces;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int[] arr = {5,6,7,8,2,3,4,5,6};
        int n = arr.length;
        int[] ans = new int[n];
        //ArrayList<Integer> ans = new ArrayList<>(n);
        stack.push(n);
        for(int i = n-1; i >= 0; --i) {
            while(!stack.empty() && stack.peek() < n && arr[i] > arr[stack.peek()]) {
                stack.pop();
            }
            ans[i] = stack.peek();
            stack.push(i);
        }
        System.out.println("Printing ans....");
        for (int i = 0; i < n; ++i) {
            System.out.println(ans[i]);
        }
        //ans.forEach(item -> System.out.println("Item = "+ item));
    }
}
