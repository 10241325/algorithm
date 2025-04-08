package com.cy.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 路径和
 * 必须从头节点开始到尾节点结束
 *
 * @author changyuan
 * @date 2025-02-21 13:03:12
 */
public class PathSum {
    public static boolean isSum;

    /**
     * 路径和是否包含sum
     *
     * @param root
     * @param sum
     * @return {@link boolean}
     * @author changyuan
     * @date 2025-02-21 13:24:10
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        isSum = false;
        process(root, 0, sum);
        return isSum;
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (null == root) {
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        process(root, path, 0, sum, ans);
        return ans;
    }

    public static void process(TreeNode root, List<Integer> path,
                               int preSum, int sum, List<List<Integer>> ans) {
        // 添加路径节点
        if (null == root.left && null == root.right) {
            // root 是叶子节点
            boolean cur = preSum + root.val == sum;
            if (cur) {
                // 发现符合路径
                path.add(root.val);
                ans.add(copy(path));
                // 会返回到上一个节点 移除最后一个元素
                path.remove(path.size() - 1);
            }
            return;
        }
        // 非叶子节点
        path.add(root.val);
        preSum += root.val;
        if (root.left != null) {
            process(root.left, path, preSum, sum, ans);
        }
        if (root.right != null) {
            process(root.right, path, preSum, sum, ans);
        }
        // 会返回到上一个节点 移除最后一个元素
        path.remove(path.size() - 1);
    }

    public static void process(TreeNode root, int preSum, int sum) {
        if (null == root.left && null == root.right) {
            // root 是叶子节点
            boolean cur = preSum + root.val == sum;
            if (cur) {
                isSum = true;
            }
            return;
        }
        // 非叶子节点
        preSum += root.val;
        if (root.left != null) {
            process(root.left, preSum, sum);
        }
        if (root.right != null) {
            process(root.right, preSum, sum);
        }
    }

    public static List<Integer> copy(List<Integer> path) {
        return new ArrayList<>(path);
    }
}
