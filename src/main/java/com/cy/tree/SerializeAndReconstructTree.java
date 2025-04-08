package com.cy.tree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化和反序列化树
 *
 * @author changyuan
 * @date 2025-03-12 12:49:11
 */
public class SerializeAndReconstructTree {
    /**
     * 先序方式序列化
     *
     * @author changyuan
     * @date 2025-03-12 12:50:56
     */
    public static Queue<String> preSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static TreeNode buildByPreQueue(Queue<String> preList) {
        if (null == preList || preList.isEmpty()) {
            return null;
        }
        return preb(preList);
    }

    /**
     * 非递归{@link #pres_1(TreeNode, Queue)}
     *
     * @author changyuan
     * @date 2025-03-12 12:59:00
     */
    public static void pres(TreeNode head, Queue<String> ans) {
        if (null != head) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                if (null != head) {
                    ans.add(String.valueOf(head.val));
                } else {
                    ans.add(null);
                }
                if (null != head) {
                    stack.push(head.right);
                    stack.push(head.left);
                }
            }
        }
    }

    /**
     * 递归
     *
     * @author changyuan
     * @date 2025-03-12 12:58:48
     */
    public static void pres_1(TreeNode head, Queue<String> ans) {
        if (null == head) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            pres_1(head.left, ans);
            pres_1(head.right, ans);
        }
    }

    public static TreeNode preb(Queue<String> preList) {
        String value = preList.poll();
        if (null == value) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }
}
