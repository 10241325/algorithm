package com.cy.bit;

import com.cy.util.PrintUtil;

/**
 * 位图
 *
 * @author changyuan
 */
public class BitMap {
    private final long[] bits;

    public BitMap(int max) {
        // 等价于 (max+64) / 64
        // 长度才能够装下0~max
        // 比如1 则需要开辟(1+64)/64 = 1 一个long类型才行
        bits = new long[(max + 64) >> 6];
    }

    /**
     * 添加一个数
     * 1、x % k 可以用 x & (k - 1) 替代的条件是 k 是 2 的幂次方。
     * 2、对于其他非 2 的幂次方，仍然需要使用 % 运算符来进行求余。
     *
     * @param num 数
     * @author changyuan
     * @date 2025-02-19 15:06:45
     */
    public void add(int num) {
        // 1、num >> 6 ->num / 64 ->数组哪个元素中
        // 2、num & 63 ->num % 64 ->哪个元素的哪个bit位中
        //    63是111111 在高一位是64 从这一位开始以后的每一位必然是64的整数倍 64 128 256 512 1024
        //    ********_********_********_**000000 像这样的数必然是64的整数倍
        //    所以只要 & 63 只留下后6位  就是余数
        //    同理只要是%一个2^x数 必然可以等价为 & 2^x-1
        // 3、当前元素 | 1<<位数 就可以设置当前位为1
        // 4、假如是170  170>>6 表示在数组第2个元素中  170%64 = 42 表示在当前元素的第42个bit位
        //   bits[2] |= 1 << 42 等价于设置第2个元素中第42个bit位为1
        bits[num >> 6] |= 1L << (num & 63);
    }

    /**
     * 删除一个数
     *
     * @param num 数
     * @author changyuan
     * @date 2025-02-19 15:07:01
     */
    public void delete(int num) {
        bits[num >> 6] &= ~(1L << (num & 63));
    }

    /**
     * 是否包含一个数
     *
     * @param num 数
     * @return true or false
     */
    public boolean contains(int num) {
        return (bits[num >> 6] & (1L << (num & 63))) != 0;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(64);
        bitMap.add(1);
        bitMap.add(2);
        bitMap.add(3);
        bitMap.add(4);
        bitMap.add(64);
        PrintUtil.toBinary(bitMap.bits[0]);
        PrintUtil.toBinary(bitMap.bits[1]);
        System.out.println(bitMap.contains(64));
        System.out.println(bitMap.contains(63));
    }
}
