//题目描述
//在数组中的两个数字，如果前面一个数字大于后面的数字，
//则这两个数字组成一个逆序对。
//输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007

public class Solution {
    public int InversePairs(int [] array) {
        if (array == null || array.length < 1) {
            throw new IllegalArgumentException("Array arg should contain at least a value");
        }

        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        return inversePairsCore(array, copy, 0, array.length - 1);
    }

    private static int inversePairsCore(int[] data, int[] copy, int start, int end) {

        if (start == end) {
            copy[start] = data[start];
            return 0;
        }

        int length = (end - start) / 2;
        int left = inversePairsCore(copy, data, start, start + length)%1000000007;
        int right = inversePairsCore(copy, data, start + length + 1, end)%1000000007;

        // 前半段的最后一个数字的下标
        int i = start + length;
        // 后半段最后一个数字的下标
        int j = end;
        // 开始拷贝的位置
        int indexCopy = end;
        // 逆序数
        int count = 0;

        while (i >= start && j >= start + length + 1) {
            if (data[i] > data[j]) {
                copy[indexCopy] = data[i];
                indexCopy--;
                i--;
                count += j - (start + length); // 对应的逆序数
                if (count >= 1000000007){
                    count %= 1000000007;
                }
            } else {
                copy[indexCopy] = data[j];
                indexCopy--;
                j--;
            }
        }

        for (; i >= start;) {
            copy[indexCopy] = data[i];
            indexCopy--;
            i--;
        }

        for (; j >= start + length + 1;) {
            copy[indexCopy] = data[j];
            indexCopy--;
            j--;
        }
        return (count + left + right)%1000000007;
    }

}