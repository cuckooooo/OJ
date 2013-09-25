#coding:utf-8
'''
Created on 2013-7-16

@author: l00165154
'''


'''
功能：判断字符串是文本还是二进制
输入：s 需要判断的字符串
输出：无
返回：返回一个bool类型， True/False
'''

import string

class Demo:


   
    def istext(self, s):
        #TODO

        text_characters = "".join(map(chr, range(32, 127))) + "\n\r\t\b" #@IndentOk
        _null_trans = string.maketrans("", "")
        # if s contains any null, it's not text:
        if "\0" in s:
            return False
        # an "empty" string is "text" (arbitrary but reasonable choice):
        if not s:
            return True
        # Get the substring of s made up of non-text characters
        t = s.translate(_null_trans, text_characters)
        # s is 'text' if less than 30% of its characters are non-text ones:
        return len(t)*1.00/len(s) <= 0.30


        
         
    