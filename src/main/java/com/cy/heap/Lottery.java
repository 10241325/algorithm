package com.cy.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个整形数组，int[] arr;和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N,arr[i]表示客户编号，op[i]表示客户操作
 * arr=[3,3,1,2,1,2,5,...]
 * op= [T,T,T,T,F,T,F,...]
 * 依次表示
 * 3用户购买一件商品
 * 3用户购买一件商品
 * 1用户购买一件商品
 * 2用户购买一件商品
 * 1用户退货一件商品
 * 2用户购买一件商品
 * 5用户退货一件商品
 * 一对arr[i]和op[i]就代表一个事件
 * 用户号为arr[i],op[i]==T就代表这个用户购买了一件商品
 * op[i]==F就代表这个用户退货了一件商品
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候，都给购买次数最多的前K名用户颁奖
 * 所以每个时间发生后，你都需要一个得奖名单（得奖区）
 * 得奖系统的规则
 * 1、如果某个用户购买商品数为0，但是又发生了退货事件，
 * 则认为该事件无效，得奖名单和之前事件时一致，比如例子中的5用户
 * 2、某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3、每次都是最多K个用户得奖，K也作为传入的参数
 * 如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 * 4、得奖系统分为得奖区和候选区，任何用户只要购买数>0,
 * 一定在这两个区域中的一个
 * 5、购买数最大的前K名用户进入得奖区，
 * 在最初的时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
 * 6、如果购买数不足以进入得奖区的用户，进入候选区
 * 7、如果候选区购买数最多的用户，已经足以进入得奖区，
 * 该用户就会替换得奖区中购买数最少得用户(大于才能替换)
 * 如果得奖区中购买数最少得用户有多个，就替换最早进入得奖区的用户
 * 如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * 8、候选区和得奖区是两套时间
 * 因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有
 * 从得奖区出来进入候选区的用户，得奖区时间删除
 * 进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 从候选区出来进入得奖区的用户，候选区时间删除
 * 进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 9、如果某用户购买数==0，不管在哪个区域都离开，区域时间删除，离开是指彻底离开，那个区域也不会找到该用户
 * 如果下次改用户又发生了购买行为，产生>0的购买数，
 * 会再次根据之前的规格回到某个区域中，进入区域的时间重记
 *
 * @author changyuan
 * @date 2025-03-09 13:50:25
 */
public class Lottery {
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        // 用户ID-用户信息
        HashMap<Integer, Customer> map = new HashMap<>();
        // 候选区
        List<Customer> cands = new ArrayList<>();
        // 得奖区
        List<Customer> daddy = new ArrayList<>();
        // 每个时间点得奖的名单
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            // 当前时间点用户ID
            int id = arr[i];
            // 买了还是退货
            boolean buyOrRefund = op[i];
            // 退货行为并且没有这个用户 忽略这个事件 不影响当前时间点得奖名单
            // 标识上来就是退后 没有购买行为
            if (!buyOrRefund && !map.containsKey(id)) {
                // 把得奖名单拿出来放到当前时间点
                ans.add(getCurAns(daddy));
                continue;
            }
            // 用户之前购买数是0，此时买货
            // 用户之前购买数是>0,此时买货或者退货
            if (!map.containsKey(id)) {
                // 没有用户 添加新用户
                map.put(id, new Customer(id, 0, 0));
            }
            // 拿到用户信息
            Customer cur = map.get(id);
            if (buyOrRefund) {
                cur.buy++;
            } else {
                cur.buy--;
            }
            // 没有购买数量 移除
            if (cur.buy == 0) {
                map.remove(id);
            }
            // 候选区没有 得奖区也没有 只能是第一次购买
            if (!cands.contains(cur) && !daddy.contains(cur)) {
                if (daddy.size() < k) {
                    cur.enterTime = i;
                    daddy.add(cur);
                } else {
                    cur.enterTime = i;
                    cands.add(cur);
                }
            }
            // 清空购买量为0的
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            // 候选区排序 购买数量大的排前面  数量一致 谁早谁在前面
            cands.sort((o1, o2) -> o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime);
            // 得奖区排序 购买数量小的排前面  数量一致 谁早谁在前面
            daddy.sort((o1, o2) -> o1.buy != o2.buy ? o1.buy - o2.buy : o1.enterTime - o2.enterTime);

            move(cands, daddy, k, i);
            // 把当前时间点得奖区名单加到返回值中
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    private static void move(List<Customer> cands, List<Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        if (daddy.size() < k) {// 得奖区没满
            // 候选区第一个
            Customer c = cands.get(0);
            c.enterTime = time;
            // 进得奖区
            daddy.add(c);
            // 从候选区移除
            cands.remove(0);
        } else { // 得奖区满了 候选区有
            if (cands.get(0).buy > daddy.get(0).buy) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Customer newDaddy = cands.get(0);
                cands.remove(0);

                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;

                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }

        }
    }

    private static void cleanZeroBuy(List<Customer> list) {
        list.removeIf(cur -> cur.buy == 0);
    }

    private static List<Integer> getCurAns(List<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer customer : daddy) {
            ans.add(customer.id);
        }
        return ans;
    }

    static class Customer {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = 0;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        public static void swap(List<?> list, int i, int j) {
            Object o = ((List) list).get(i);
            ((List) list).set(i, o);
        }
    }
}