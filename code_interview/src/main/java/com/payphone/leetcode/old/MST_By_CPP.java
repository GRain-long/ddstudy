package com.payphone.leetcode.old;

public class MST_By_CPP {
    /**
     * #include<iostream>
     * #include<algorithm>
     * #include<queue>
     * using namespace std;
     *
     * int n,m;//顶点数 边数
     *
     * void prims(int path[],int marked[],int edge[][1005]){
     *     queue<int> que; que.push(1);
     *     //path数组初始化
     *     for(int i=1;i<=n;i++){
     *         path[i] = edge[1][i];
     *     }
     *     marked[1] = 1;
     *     int cost = 0;
     *     // 看剩余的N-1个点可否加入生成树。
     *     for(int k = 1; k < n; k++){
     *         int MIN = 0xffffff  ;
     *         int index = -1;
     *         //找没有加入生成树且 距离生成树最近的点
     *         for(int i = 1; i <= n; i++){
     *             if(marked[i] == 0 && path[i] < MIN && path[i]!=0){
     *                 MIN = path[i];
     *                 index = i;
     *             }
     *         }
     *         //找到了最短距离的点 加入生成树
     *         if(index != -1){
     *             marked[index] = 1;//标记该点已经加入生成树了。
     *             cost+=path[index];
     *             que.push(index);//记录生成顺序
     *         }
     *         //同时更新 path数组 中还未加入生成树的点的权值
     *         for(int i = 1; i<=n; i++){
     *             if(marked[i] == 0 && edge[index][i]!=0 && edge[index][i] < path[i] ){
     *                 path[i] = edge[index][i];
     *             }
     *         }
     *     }
     *     if(que.size()==n) printf("%d",cost);  //如果存在不连通的点，sum>inf ||sum<0(加法越界)
     *     else printf("-1");
     * }
     *
     * int main(){
     *     int path[1005] = {0}, marked[1005] = {0};
     *     int edge[1005][1005]={};
     *     int u,v,w;
     *     scanf("%d%d",&n,&m);
     *     for(int i=1;i<=n;i++){
     *         for(int j=1;j<=n;j++){
     *             edge[i][j] = edge[j][i] = 0xffffff;
     *         }
     *     }
     *     for(int i=0;i<m;i++){
     *         scanf("%d%d%d",&u,&v,&w);
     *         if(edge[v][u]>w)
     *             edge[v][u] = edge[u][v] = w;
     *     }
     *     prims(path,marked,edge);
     *
     *     return 0;
     * }
     * //https://pintia.cn/problem-sets/15/problems/718
     */
}
