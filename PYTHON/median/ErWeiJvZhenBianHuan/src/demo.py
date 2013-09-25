#coding:utf-8
'''
Created on 2013-7-16

@author: l00165154
'''

'''
功能：翻转一个矩阵
输入：m 一个矩阵
输出：无
返回：返回一个翻转的矩阵
'''

class Demo:
    
    def reverseMatrix(self, m):
        
        return map(list,zip(*m))

    