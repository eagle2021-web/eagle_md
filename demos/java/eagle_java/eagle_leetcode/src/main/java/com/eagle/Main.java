package com.eagle;

import com.eagle.common.ListNode;
import com.eagle.common.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

@SuppressWarnings("all")
class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> sells = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> buys = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int[] order : orders) {
            int price = order[0], amount = order[1], type = order[2];
            if(type == 0){
                while (amount != 0 && !sells.isEmpty() && sells.peek()[0] <= price){
                    int[] poll = sells.poll();
                    int min = Math.min(poll[1], amount);
                    amount -= min;
                    poll[1] -= min;
                    if(poll[1] != 0){
                        sells.offer(poll);
                    }
                }
                if(amount != 0)buys.offer(new int[]{price, amount});
            }else {
                while (amount != 0 && !buys.isEmpty() && buys.peek()[0] >= price){
                    int[] poll = buys.poll();
                    int min = Math.min(poll[1], amount);
                    amount -= min;
                    poll[1] -= min;
                    if(poll[1] != 0){
                        buys.offer(poll);
                    }
                }
                if(amount != 0)sells.offer(new int[]{price, amount});
            }
        }
        int tot = 0;
        final int MOD = (int)1e9 + 7;
        for (PriorityQueue<int[]> ints : Arrays.asList(sells, buys)) {
          while (!ints.isEmpty()){
              int[] poll = ints.poll();
              tot = (tot + poll[1]) % MOD;
          }
        }
        return tot;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
//        int a = s.minMoves(new int[]{0,1,0, 0,1,1,1,1,0,1}, 6);
//        System.out.println(a);
        s.wiggleMaxLength(new int[]{3,3,3,2,5});
    }
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        ArrayList<Integer> relative = new ArrayList<>(n / 2);
        ArrayList<Integer> sum = new ArrayList<>(n / 2);
        sum.add(0);
        for(int i = 0; i < n; i++){
            int num = nums[i];
            if(num == 1){
                int relative_cnt = i - relative.size();
                relative.add(relative_cnt);
                sum.add(sum.get(sum.size() - 1) + relative_cnt);
            }
        }

        int ans = Integer.MAX_VALUE, size = relative.size();
        for(int i = 0; i <= size - k; i++){
            int r = i + k -1;
            int mid = (i + r) / 2;
            int mid_cnt = relative.get(mid);
            ans = Math.min(ans, mid_cnt * (mid - i) - sum.get(mid) + sum.get(i)
                    + (sum.get(r + 1) - sum.get(mid + 1) - relative.get(mid) * (r - mid)));
        }
        return ans;
    }

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if(n < 2)return 1;
        int[] a = new int[nums.length];
        int d = 0, res = 1;
        for(int i = 1; i < nums.length; i++){
            if((d > 0 && nums[i] - nums[i - 1] < 0) || (d < 0 && nums[i] - nums[i - 1] > 0)){
                res++;
                d = nums[i] - nums[i - 1];
            } else if(d == 0) {
                d = nums[1] - nums[0];
                if(d != 0){
                    res = 2;
                }
            }
        }
        return res;
    }
}
