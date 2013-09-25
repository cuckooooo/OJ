#coding:utf-8
'''
Created on Aug 5, 2013

@author: z00241626
'''

'''
取一个任意小于1美元的金额，然后计算可以换成最少多少枚硬币。
硬币有1美分，5美分，10美分，25美分四种。1美元等于100美分。
举例来说，0.76美元换算结果应该是3枚25美分，1枚1美分。
类似76枚1美分，2枚25美分+2枚10美分+1枚5美分+1枚1美分这样的结果都是不符合要求的。
输入：n
返回：list    list[0]为1美分个数；list[1]为5美分个数；list[2]为10美分个数；list[3]为25美分个数
异常：输入不满足小于1美元的金额->返回list=None
'''

class Demo:
    
    def dollarSplit(self, n):
        if(n >= 100):
            return None
        if(n < 0):
            return None
        
        left = n
        list = [0, 0, 0, 0]
        list[3] = left / 25;
        left -= list[3]*25 
        list[2] = left / 10
        left -= list[2]*10
        list[1] = left / 5
        left -= list[1]*5
        list[0] = left / 1        
        
        return list