package com.cy.tree;

/**
 * 是否为搜索二叉树：对于每一个节点 我的左侧都比我小 右侧都比我大
 * 所以中序遍历严格递增则为搜索二叉树 很强
 *
 * @author changyuan
 */
public class IsSearchBinaryTree {
    static class Info {
        public boolean isSearched;
        public int max;
        public int min;

        public Info(boolean isSearched, int max, int min) {
            this.isSearched = isSearched;
            this.max = max;
            this.min = min;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return process(root).isSearched;
    }

    public static Info process(TreeNode head) {
        if (null == head) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int max = head.val;
        int min = head.val;
        // max 为任意节点左侧最大值
        // min 为任意节点右侧最小值
        if (null != leftInfo) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (null != rightInfo) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        boolean isSearched = true;
        if (null != leftInfo && !leftInfo.isSearched) {
            isSearched = false;
        }
        if (null != rightInfo && !rightInfo.isSearched) {
            isSearched = false;
        }

        boolean leftMaxLess = null == leftInfo ? true : leftInfo.max < head.val;
        boolean rightMinMore = null == rightInfo ? true : rightInfo.min > head.val;
        if (!leftMaxLess || !rightMinMore) {
            isSearched = false;
        }
        return new Info(isSearched, max, min);
    }

    ;
}
