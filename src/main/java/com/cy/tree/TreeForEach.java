package com.cy.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树遍历
 *
 * @author changyuan
 * @date 2025-03-12 12:29:48
 */
public class TreeForEach {
    /**
     * 按层遍历 其实就是宽度优先遍历，用队列
     * 先将头结点入队列
     * 弹出时先打印 有左加左 有右加右
     * 没法知道哪一层
     *
     * @author changyuan
     * @date 2025-03-12 12:36:18
     */
    public static void levelOrder(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.val + " ");
            if (null != cur.left) {
                queue.add(cur.left);
            }
            if (null != cur.right) {
                queue.add(cur.right);
            }
        }
    }
}
