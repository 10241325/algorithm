package com.cy.bit;

import com.cy.util.PrintUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 位运算
 *
 * @author changyuan
 */
public class Operation {
    /**
     * 位运算实现加法
     *
     * @param a 加数
     * @param b 被加数
     * @return 和
     * @author changyuan
     * @date 2025-02-19 15:45:58
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            // 无进位相加信息
            sum = a ^ b;
            // 进位信息 需要左移一位
            b = (a & b) << 1;
            // a 变成无进位相加信息
            a = sum;
        }
        return sum;
    }

    /**
     * 位运算实现减法
     *
     * @param a 减数
     * @param b 被减数
     * @return cha
     * @author changyuan
     * @date 2025-02-19 15:45:58
     */
    public static int sub(int a, int b) {
        // a-b -> a+(~b+1)
        return add(a, negNum(b));
    }


    /**
     * 位运算实现乘法
     * 11010
     * *
     * 00101
     * = 010
     * 下面的每一位乘以上面等价于
     * ____11010 1
     * ___00000
     * __11010
     * _00000
     * 00000
     * 全部加起来 就是乘积
     *
     * @param a 乘数
     * @param b 被乘数
     * @return cha
     * @author changyuan
     * @date 2025-02-19 15:45:58
     */
    public static int mul(int a, int b) {
        int sum = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                sum = add(sum, a);
            }
            a = a << 1;
            b = b >>> 1;
        }
        return sum;
    }

    public static int div(int a, int b) {
        // 转成正数
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;

        int ret = 0;
        // 因为已经是正数了 最高位一定是0
        for (int i = 30; i >= 0; i = sub(i, 1)) {
            // x 右移 和 y 左移 是一样的 y 左移需要考虑符号位 所以选择x右移
            if ((x >> i) >= y) {
                // x右移大于等于y(第一次大于等于y),则该位必然有个1
                ret |= (1 << i);
                x = sub(x, y << i);
            }
        }
        // 符号相同则取相反数
        return isNeg(a) ^ isNeg(b) ? negNum(ret) : ret;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            // a 系统最小 b 系统最小
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            // a 不是系统最小 b 系统最小
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            // a 系统最小 b 不是系统最小
            if (b == negNum(1)) {
                // leetcode 规定系统最小除以一个-1 返回系统最大
                return Integer.MAX_VALUE;
            } else {
                // 先将系统最小抹去一个1 就可以转正数的绝对值
                // 算出一个ans
                // 用a - ans*b 获取剩余的
                // 用剩余的除以b 获取ans1
                // return  ans + ans 1
                // 例子：
                // 1、a/b
                // 2、(a+1)/b = c
                // 3、a-(c*b) = d
                // 4、d/b = e
                // 5、return c + e
                int ans = div(add(a, 1), b);
                return add(ans, div(sub(a, mul(ans, b)), b));
            }
        } else {
            // a 不是系统最小 b 不是系统最小
            return div(a, b);
        }
    }

    public static int negNum(int num) {
        return add(~num, 1);
    }

    public static boolean isNeg(int num) {
        return num < 0;
    }


}
