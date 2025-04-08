package com.cy.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class LotteryOptimization {
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhosYourDaddy whoDaddies = new WhosYourDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            whoDaddies.operate(i, arr[i], op[i]);
            ans.add(whoDaddies.getDaddies());
        }
        return ans;
    }

    static class WhosYourDaddy {
        private Map<Integer, Customer> customers;
        private HeapGreater<Customer> candHeap;
        private HeapGreater<Customer> daddyHeap;
        private final int daddyLimit;

        public WhosYourDaddy(int daddyLimit) {
            customers = new HashMap<>();
            // 大根堆 候选区排序 购买数量大的排前面  数量一致 谁早谁在前面
            candHeap = new HeapGreater<>((o1, o2) -> o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime);
            // 小根堆 得奖区排序 购买数量小的排前面  数量一致 谁早谁在前面
            daddyHeap = new HeapGreater<>((o1, o2) -> o1.buy != o2.buy ? o1.buy - o2.buy : o1.enterTime - o2.enterTime);
            this.daddyLimit = daddyLimit;
        }

        public void operate(int time, int id, boolean buyOrRefund) {
            if (!buyOrRefund && !customers.containsKey(id)) {
                // 首次就是退货 忽略
                return;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer customer = customers.get(id);
            if (buyOrRefund) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            if (customer.buy == 0) {
                customers.remove(id);
            }
            // 同时都没有 表示一定是首次购买
            if (!candHeap.contains(customer) && !daddyHeap.contains(customer)) {
                if (daddyHeap.size() < daddyLimit) {
                    customer.enterTime = time;
                    daddyHeap.push(customer);
                } else {
                    customer.enterTime = time;
                    candHeap.push(customer);
                }
            } else if (candHeap.contains(customer)) {
                if (customer.buy == 0) {
                    candHeap.remove(customer);
                } else {
                    candHeap.resign(customer);
                }
            } else {
                if (customer.buy == 0) {
                    daddyHeap.remove(customer);
                } else {
                    daddyHeap.resign(customer);
                }
            }
            daddyMove(time);
        }

        private void daddyMove(int time) {
            if (candHeap.isEmpty()) {
                return;
            }
            if (daddyHeap.size() < daddyLimit) {
                Customer customer = candHeap.pop();
                customer.enterTime = time;
                daddyHeap.push(customer);
            } else {
                if (candHeap.peek().buy > daddyHeap.peek().buy) {
                    Customer oldDaddy = daddyHeap.pop();
                    Customer newDaddy = candHeap.pop();
                    oldDaddy.enterTime = time;
                    newDaddy.enterTime = time;
                    daddyHeap.push(newDaddy);
                    candHeap.push(oldDaddy);
                }

            }
        }

        public List<Integer> getDaddies() {
            return daddyHeap.getAllElements().stream().map(o -> o.id).collect(Collectors.toList());
        }
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
    }
}
