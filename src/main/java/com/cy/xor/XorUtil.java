package com.cy.xor;

import com.cy.util.PrintUtil;

/**
 * @author changyuan
 */
public class XorUtil {
    /**
     * 一个数组中有一种数出现了k次，其他数都出现了M次
     * M > 1,K<M
     * 找到出现了K次的数
     *
     * @param arr
     * @return {@link null}
     * @author changyuan
     * @date 2025-02-22 16:31:22
     */
    public static int findOnlyKTimes(int[] arr, int k, int m) {
        int[] helper = new int[32];
        // helper[0] 0位置的1出现了几个
        // helper[1] 1位置的1出现了几个
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < helper.length; j++) {
                helper[j] += (arr[i] >> j) & 1;
            }
        }
        PrintUtil.toArray(helper);
        int ans = 0;
        for (int i = 0; i < helper.length; i++) {
            // 因为k<m 所以这个位置 % m 不等于0 则这个数必然在这个位置是1
            if (helper[i] % m != 0) {
                // 说明该数该位置为1
                ans |= 1 << i;
            }
        }
        return ans;
    }

    /**
     * 给定一个数 提取最后一个二进制为1的
     * 假设给定 01101110010000
     * 返回    00000000010000
     *
     * @param number 数
     * @return {@link int}
     * @author changyuan
     * @date 2025-02-22 15:51:01
     */
    public static int extractTheLastOne(int number) {
        return number & (-number);
        // number & (~number+1) 取反加一会把这个数的1左侧相反右侧变成相同
    }

    /**
     * 给定一个数组 有两个个数出现了奇数次 找到并返回这两个数
     *
     * @param arr 数组
     * @return 出现了奇数次的那个数
     * @author changyuan
     * @date 2025-02-22 15:47:20
     */
    public static int[] findOddTimesTwoNum(int[] arr) {
        // 全异或
        int xor = findOddTimes(arr);
        // a和b是两种数
        // xor != 0 一定成立
        // xor最右的1提取出来
        // xor     :00110010110111000
        // rightOne:00000000000001000
        // 本质思想就是将a和b根据rightOne划分到不同的空间去(xor和onlyOne)
        // xor肯定是a^b的结果 onlyOne必然是a或者b 若onlyOne=a则onlyOne ^ xor必然是b
        int rightOne = xor & (~xor + 1);
        int onlyOne = 0;

        for (int j : arr) {
            if ((j & rightOne) != 0) {
                // 表示 该位置肯定是1
                onlyOne ^= j;
            }
        }
        return new int[]{onlyOne, onlyOne ^ xor};
    }

    /**
     * 给定一个数组 有一个数出现了奇数次 找到并返回这个数
     *
     * @param arr 数组
     * @return 出现了奇数次的那个数
     * @author changyuan
     * @date 2025-02-22 15:47:20
     */
    public static int findOddTimes(int[] arr) {
        int xor = 0;
        for (int j : arr) {
            xor ^= j;
        }
        return xor;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 1, 2, 1, 2, 4, 4, 9};
        System.out.println(findOddTimes(arr));

        System.out.println("=========");
        int a = 0b01101110010000;
        PrintUtil.toBinary(a);
        PrintUtil.toBinary(extractTheLastOne(a));
        System.out.println("=========");

        arr = new int[]{1, 2, 1, 2, 1, 2, 8, 8, 1, 2, 4, 4, 9, 10};
        PrintUtil.toArray(findOddTimesTwoNum(arr));
        System.out.println("=========");
        arr = new int[]{-1, 3, 1, 3, 3, 1, 1, -1};
        System.out.println(findOnlyKTimes(arr, 2, 3));

    }
}
