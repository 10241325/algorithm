package com.cy.util;

/**
 * @author changyuan
 */
public class RandomUtil {

    /**
     * 等概率返回[0,1)之间的随机数
     * 等概率是因为计算机里面的小数是有精度的，意味着0到1之间的小说是有穷尽的
     * 在数学上是做不到的
     *
     * @return [0, 1)之间的随机数
     */
    public static double random() {
        // 等概率返回[0,1)之间的随机数
        // 等概率是因为计算机里面的小数是有精度的，意味着0到1之间的小说是有穷尽的
        // 在数学上是做不到的
        return Math.random();
    }

    /**
     * 等概率返回[0,1)之间的随机数
     * 任意的x,x属于[0,1),[0,x)范围上的数出现的概率由原来的x调整成x的平方
     *
     * @return [0, 1)之间的随机数
     */
    public static double xToPower2() {
        // 原理就是 两次调用都是0到x区间，那么x的概率就是x的平方
        // 0.3 出现的概率是0.3
        // 0.7 出现的概率是0.7
        // 调用两次都是0.3的概率是0.3*0.3
        // 调用两次都是0.7的概率是0.7*0.7
        // max保证的是两次都在这个区间
        return Math.max(random(), random());
        // 同理可以扩展到任意的x的n次方
        // 3次方
        // return Math.max(random(), Math.max(random(), random()));
        // 4次方
        // return Math.max(random(), Math.max(random(), Math.max(random(), random())));
        // 5次方
        // 同理 Math.min(random(),random())概率是1-(1-x)^2
        // 只要有一次是0到x的概率即可
        // ===>1-两次都不是0到x的概率
        // 两次都不是的概率为(1-x)^2
        // ====>> 1-(1-x)^2
    }

    public static int f3() {
        int ans;
        do {
            ans = (f1() << 2) + (f1() << 1) + f1();
        } while (ans == 7);
        return ans;
    }

    /**
     * 等概率返回[0,1]之间的随机数
     *
     * @return [0, 1]之间的随机数
     */
    public static int f1() {
        int ans;
        do {
            ans = f();
        } while (ans == 3);

        return ans < 3 ? 0 : 1;
    }

    /**
     * 等概率返回[1,5]之间的随机数
     *
     * @return [1, 5]之间的随机数
     */
    public static int f() {
        return (int) (random() * 5) + 1;
    }

    /**
     * 不等概率到0 1等概率发生器
     * 00 丢弃
     * 11丢弃
     * 01返回1
     * 10返回1
     * @return
     */
    public static int y() {
        int ans;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }

    /**
     * 0.84概率返回1
     * @return
     */
    public static int x() {
        return random() < 0.84 ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(arr);
        teest(arr);
    }

    private static void teest(int[] arr) {
        System.out.println(arr);
    }
}
