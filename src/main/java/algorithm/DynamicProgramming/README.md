### 动态规划
> 将原问题拆解成若干子问题，同时保存子问题的答案，使得每个子问题只求解一次，最终获得原问题的答案

![动态规划](动态规划.png)

- 斐波那契数列：F(0)=1,F(1)=1,F(n)=F(n-1)+F(n-2)
    - [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
    ![爬楼梯](爬楼梯.png)
    - [120. Triangle](https://leetcode.com/problems/triangle/)
    - [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/)
![斐波那契数列](斐波那契数列.png)
![记忆化搜索](记忆化搜索.png)
![动态规划自下而上](动态规划自下而上.png)
```c
//记忆化搜索
vector<int> memo;
int fib(int n){
    if(n == 0){
        return 0;
    }
    if(n == 1){
        return 1;
    }
    if(memo[n] == -1){
        memo[n] = fib(n-1) + fib(n-2);
    }
    return memo[n];
}

//动态规划
int fib(int n){
    vector<int> memo(n+1, -1);
    memo[0] = 0;
    memo[1] = 1;
    for(int i = 2; i <= n; i++){
        memo[i] = memo[i-1] + memo[i-2];
    }
    return memo[n];
}
```
- 最优子结构：通过求子问题的最优解，可以获得原问题的最优解
![整数分解](整数分解.png)
- [343. Integer Break](https://leetcode.com/problems/integer-break/)
- [279. Perfect Squares](https://leetcode.com/problems/perfect-squares/)
- [91. Decode Ways](https://leetcode.com/problems/decode-ways/)
- [62. Unique Paths](https://leetcode.com/problems/unique-paths/)
- [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii/)
- 状态转移和状态方程
    - [198. House Robber](https://leetcode.com/problems/house-robber/)（暴力解法：检查房子所有组合，对每个房子检查是否相邻，没有则记录最大值。O((2^n)*n)）
    ![HouseRobber](HouseRobber.png)
    ![HouseRobber状态方程](HouseRobber状态方程.png)
    - 不同的状态定义
    ![HouseRobber状态方程2](HouseRobber状态方程2.png)
- [213. House Robber II](https://leetcode.com/problems/house-robber-ii/)
递归树中每个节点所表示要解决的问题在DP里表示定义的一个状态
- [337. House Robber III](https://leetcode.com/problems/house-robber-iii/)
- [309. Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)
- 0-1背包问题 (暴力解法：每个物品可放进背包也可不放，O((2^n)*n))
- ![背包问题](01背包问题.png)
- ![背包问题](01背包问题2.png)
- [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)
- []()
- []()
- []()

