#coding:utf-8
'''
Created on 2013-7-19

@author: l00165154
'''

'''
功能：消除序列中的重复
输入：s 序列
输出：无
返回：取消重复后的序列
'''

class Demo:
    
    def demo(self, s):
        
        # Get the special case of an empty s out of the way very rapidly
        n = len(s)
        if n == 0:
            return []
        
        # Try using a dict first, because it's the fastest and will usually work
        u = {}
        try:
            for x in s:
                u[x] = 1
        except TypeError:
            del u  # Move on to the next method
        else:
            return u.keys(  )

        # Since you can't hash all elements, try sorting, to bring equal items
        # together and weed them out in a single pass
        try:
            t = list(s)
            t.sort()
        except TypeError:
            del t  # Move on to the next method
        else:
            assert n > 0
            last = t[0]
            lasti = i = 1
            while i < n:
                if t[i] != last:
                    t[lasti] = last = t[i]
                    lasti += 1
                i += 1
            return t[:lasti]

        # Brute force is all that's left
        u = []
        for x in s:
            if x not in u:
                u.append(x)
        return u    
             
        
        #return list(set(s))      
    