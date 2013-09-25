#coding:utf-8
'''
Created on 2013-7-18

@author: l00165154
'''

'''
功能：交换列表a和b的元素，使a的元素和与b的元素和差值最小
输入：list1, list2
输出：无
返回：经过交换后的list1和list2
'''

class Demo:
    
    def demo(self, list1, list2):
        #TODO
                
        if sum(list1) < sum(list2):
            array = list1
            list1 = list2
            list2 = array
            
        diff_sum = sum(list1) - sum(list2)
        
        loop = True
        while loop:
            loop = False
            md = diff_sum / 2
            
            for i in range(0, len(list1)):
                for j in range(0, len(list2)):
                    x = list1[i] - list2[j]
                    if x < diff_sum and x > 0:
                        loop = True
                        if abs(x - diff_sum / 2) < md:
                            md = abs (x - diff_sum / 2)
                            mi = i
                            mj = j
            if loop:
                tmp = list1[mi]
                list1[mi] = list2[mj]
                list2[mj] = tmp
                
                diff_sum = diff_sum - 2 * (list2[mj] - list1[mi])
                if diff_sum < 0:
                    array = list1
                    list1 = list2
                    list2 = array
                    diff_sum = - diff_sum
        
        return list1, list2


#===============================================================================
# 当数组a和b的和之差为A = sum(a) - sum(b)，a的第i个元素和b的第j个元素交换后，a和b的和之差为：
# 
# A' = sum(a) - a[i] + b[j] - (sum(b) - b[j] + a[i])
# 
#    = sum(a) - sum(b) - 2 (a[i] - b[j])
# 
#    = A - 2 (a[i] - b[j])
# 
# 设 x = a[i] - b[j]
# 
# |A| - |A'| = |A| - |A - 2x|
# 
# 假设A > 0，
# 
# 当x在(0, A)之间时，做这样的交换才能使得交换后的a和b的和之差变小，x越接近A/2效果越好，如果找不到在 (0, A)之间的x，则当前的a和b就是答案。
# 
# 所以大概算法如下：在a和b中寻找使得x在(0, A)之间，并且最接近A/2的i和j，交换相应的i和j元素，重新计算A后，重复前面的步骤知道找不到(0, A)之间的x为止。
#===============================================================================
