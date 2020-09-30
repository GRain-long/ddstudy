package com.payphone.leetcode.old;

public class NQueen_By_CPP {
    /**
     *#include<iostream>
     * #include<stdio.h>
     * //index 是行 n是皇后个数。为方便散列数组的使用，散列数组大小为n+1 P数组大小同散列数组，方便访问
     * void genPrem(int index,int n,int P[],bool hastTable[],int &count){
     * 	if(index == n+1){
     * 		count++;
     * 		return;
     *        }
     * 	for(int x = 1;x<=n;x++){//x是列
     * 		if(hastTable[x]==false){//hastTable记录每列是否已经放置了元素，保证不同列放置皇后
     * 			bool flag = true; //该列没有放置元素，我在该列放元素行不行！
     * 			//判断当前行这个位置放置皇后是否会冲突！
     * 			for(int pre = 1;pre<index;pre++){//列举前面的行，我在此行的x列放置元素是否会冲突
     * 				//P[]存放前面行的列号
     * 				// index - pre ==> 我的行号 - 你的行号。
     * 				// x - P[pre] ==> 我的列号 - 你的列号。
     * 				if(abs(index-pre)==abs(x-P[pre])){
     * 					flag = false;
     * 					break;
     *                }
     *            }//end for
     * 			if(flag){
     * 				P[index] = x;
     * 				hastTable[x] = true;
     * 				genPrem(index+1,n,P,hastTable,count);
     * 				hastTable[x] = false;
     *            }
     *        }
     *    }
     * }
     * int totalNQueens(int n) {
     * 	int m = n+1;
     * 	int P[m] = {};//P[i]存放的是第i行放置皇后的列号 如P[1] = 3 表示第1行的第三列放置了皇后
     * 	bool hastTable[m] = {};//记录那些列被使用了。我们逐行遍历，不存在行重复。
     * 	int count = 0;
     * 	genPrem(1,n,P,hastTable,count);
     * 	return count;
     * }
     *
     * int main(){
     *    int a =  totalNQueens(20);
     *     printf("%d",a);
     *     return 0;
     * }
     */
}
