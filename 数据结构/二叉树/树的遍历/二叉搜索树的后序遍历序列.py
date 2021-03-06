# -*- coding: utf-8 -*-
"""
Created on 2018/8/14 11:02

@author: vincent
题目描述
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
"""
# -*- coding:utf-8 -*-
class Solution:
    def VerifySquenceOfBST(self, sequence):
        # write code here
        if not sequence:
            return False
        root = sequence[-1]
        index = 0
        for i in range(len(sequence)):
            if sequence[i] > root:
                break
            index += 1
        for i in range(index,len(sequence)-1):
            if sequence[i] < root:
                return False
        left, right = True, True
        if 0 < index < len(sequence)-1:
            left = self.VerifySquenceOfBST(sequence[:index])
            right = self.VerifySquenceOfBST(sequence[index:-1])
        return left and right