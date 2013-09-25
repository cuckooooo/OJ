#coding:utf-8
'''
Created on 2013-7-19

@author: l00165154
'''
from distutils.tests.setuptools_build_ext import if_dl

'''
功能：检查圆括号是否平衡
输入：s 输入字符串
输出：无
返回：布尔值，平衡返回true,否则返回False
'''

class Demo:
    
    def demo(self, s):
        op = 0
        for c in s:
            if c == '(':
                op += 1
            elif c == ')':
                if op == 0:
                    return False
                op -= 1
        
        if op == 0:
            return True
        
        return False






        
        
        









        
         
    