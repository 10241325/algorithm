package com.cy.tree;

/**
 * 平衡二叉树
 *
 * @author changyuan
 */
public class BalancedBinaryTree {

    /**
     * 判断是否平衡
     *
     * @param root
     * @return {@link boolean}
     * @author changyuan
     * @date 2025-02-21 11:47:33
     */


    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }


    static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean balanced, int height) {
            this.isBalanced = balanced;
            this.height = height;
        }
    }



    public static Info process(TreeNode root) {
        if (null == root) {
            return new Info(true, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        // 高度 = 左树和右树的最大值+我自己
        int height = Math.max(left.height, right.height) + 1;
        // 是否平衡：左侧平衡 右侧平衡 左侧和右侧相差不大于1
        boolean isBalanced = left.isBalanced
                && right.isBalanced
                && Math.abs(left.height - right.height) < 2;
        return new Info(isBalanced, height);
    }
}
