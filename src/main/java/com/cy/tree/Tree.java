package com.cy.tree;

import com.cy.sort.SortUtil;

import java.util.*;

/**
 * 二叉树：不能有环
 * ___1
 * _2   3
 * 4 5 6 7
 * 先序：头左右 1 2 4 5 3 6 7
 * 中序：左头右 4 2 5 1 6 3 7
 * 后序：左右头 4 5 2 6 7 3 1
 * <p>
 * 特性：
 * 1、假设有前序遍历和后序遍历
 * 其中某个节点x
 * 则前序遍历中x左侧的数和后序遍历中右侧的数的交集则为x的根节点
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * •	前序遍历：1, 2, 4, 5, 3
 * •	后序遍历：4, 5, 2, 3, 1
 * •	节点 x = 5
 * 前序和后序的结果分析：
 * •	前序中 x = 5 左边的节点：1, 2, 4。
 * •	表示可能的父节点。
 * •	后序中 x = 5 右边的节点：2, 3, 1。
 * •	表示可能的父节点。
 * •	交集为 1, 2。
 * •	这正是从根到 x 的路径（父节点列表）。
 * 交集刚好是所有父节点，原因在于：
 * 1.	前序遍历中的左边节点 代表了可能的路径上的父节点。
 * 2.	后序遍历中的右边节点 也代表了可能的路径上的父节点。
 * 3.	交集是两种路径的重合部分，而二叉树的路径从根到 x 是唯一的，所以交集正好是 x 的所有父节点。
 */
public class Tree {

    public static void pre(TreeNode head) {
        if (null == head) {
            return;
        }
        // 头
        System.out.print(head.val + " ");
        // 左
        pre(head.left);
        // 右
        pre(head.right);
    }

    /**
     * 非递归方式先序遍历
     * 1、先把head压栈
     * 2、栈不为空 栈顶弹出记录cur 有右压入右 有左压入左 一定先右再左 这样下次弹出就是左
     * 3、跳到第二步
     *
     * @author changyuan
     * @date 2025-04-15 11:30:28
     */
    public static void preWithStack(TreeNode head) {
        if (null != head) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                System.out.println(node);
                if (null != node.right) {
                    stack.push(node.right);
                }
                if (null != node.left) {
                    stack.push(node.left);
                }
            }
        }
    }

    public static void in(TreeNode head) {
        if (null == head) {
            return;
        }
        // 左
        in(head.left);
        // 头
        System.out.print(head.val + " ");
        // 右
        in(head.right);
    }

    public static void inWithStack(TreeNode head) {
        if (null != head) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || null != head) {
                if (null != head) {
                    // 将当前节点的左树全部放到栈里去
                    stack.push(head);
                    head = head.left;
                } else {
                    // 此时这个节点站在自己的角度看就是头  站在父节点看就是左
                    // 如果我已经出栈  那我的左必然已经出栈
                    head = stack.pop();
                    System.out.println(head.val + " ");
                    // 把自己的右入栈
                    head = head.right;
                }
            }
        }
    }

    public static void pos(TreeNode head) {
        if (null == head) {
            return;
        }
        // 左
        pos(head.left);
        // 右
        pos(head.right);
        // 头
        System.out.print(head.val + " ");

    }

    public static void posWithStack(TreeNode head) {
        if (null != head) {
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                // 头右左
                head = s1.pop();
                s2.push(head);
                if (null != head.left) {
                    s1.push(head.left);
                }
                if (null != head.right) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                // 左右头
                System.out.print(s2.pop().val + " ");
            }
            System.out.println();
        }
    }

    /**
     * 单个栈实现后序遍历  4 5 2 6 7 3 1
     * 1
     * 23
     * 4567
     *
     * @author changyuan
     * @date 2025-04-15 12:31:32
     */
    public static void posWithStack2(TreeNode head) {
        if (null != head) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            TreeNode c;
            while (!stack.isEmpty()) {
                c = stack.peek();
                // 处理5节点后 此处的c=2 因为是后序遍历 那么4肯定已经处理过了 但是head标记的是5 left为4 所以此处需要判断head != c.right
                // 其实就是避免重新访问左子树 因为4必然在5之前访问 但是此时的head是5
                if (null != c.left && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (null != c.right && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().val + " ");
                    // 标记已经处理的节点 假设已经处理到5节点了
                    head = c;
                }
            }
        }


        System.out.println();
    }

    /**
     * 判断是否为相同的树
     *
     * @param p 树
     * @param q 树
     * @return {@link boolean}
     * @author changyuan
     * @date 2025-02-20 13:55:35
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            // 一个为空 一个不为空
            return false;
        }
        if (p == null) {
            // p 为空 q也为空
            return true;
        }
        // 都不为空 判断值 &&左树 && 右树
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static boolean isMirror(TreeNode root) {
        return isMirror(root, root);
    }

    /**
     * ___1
     * _2   2
     * 4 5 5 4
     * 判断是否为镜面树 比如节点1 两边对称
     *
     * @param h1 节点1
     * @param h2 节点2
     */
    public static boolean isMirror(TreeNode h1, TreeNode h2) {
        if (h1 == null ^ h2 == null) {
            // 一个为空 一个不为空
            return false;
        }
        if (h1 == null) {
            // p 为空 q也为空
            return true;
        }
        return h1.val == h2.val && isMirror(h1.left, h2.right) && isMirror(h1.right, h2.left);
    }

    /**
     * 树的深度
     *
     * @param root
     * @return {@link int}
     * @author changyuan
     * @date 2025-02-20 14:18:12
     */
    public static int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        // 我右侧树的深度 和 我左侧树的深度 在多一层我自己
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 给定一个先序数组和中序数组 生成二叉树
     *
     * @param pre 先序数组
     * @param in  中序数组
     * @return {@link TreeNode}
     * @author changyuan
     * @date 2025-02-20 14:30:00
     */
    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }
        return buildTree(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
    }

    // 有一颗树，先序结果是pre[L1...R1],中序结果是in[L2,R2]
    // 请建出一棵树返回头结点
    public static TreeNode buildTree(int[] pre, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> valueIndexMap) {
        if (L1 > R1) {
            // 不是有效范围，返回空树
            // 左树为空 或者 右树为空
            // 若L1和R1是有效范围 则 L2和R2必然是有效范围 不然就不可能建成树了
            return null;
        }
        TreeNode root = new TreeNode(pre[L1]);
        if (L1 == R1) {
            // 只有一个节点
            return root;
        }
//        // pre是先序节点 第一个节点必然是根节点
//        int find = L2;
//        // 在中序数组中找到根节点 根节点之前的元素就是左树元素 之后的元素就是右树元素
//        while (in[find] != pre[L1]) {
//            // 找到了
//            find++;
//        }
        int find = valueIndexMap.get(pre[L1]);
        // 1 2 4 5 3 6 7 -> 1 就是头节点
        // 4 2 5 1 6 3 7 -> 425就是左树 637是右树
        root.left = buildTree(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
        root.right = buildTree(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);

        return root;
    }

    /**
     * 递归序 每个节点都会来3次 第一次就是先序 第二次就是中序 第三次就是后序
     *
     * @param head
     * @return
     * @author changyuan
     * @date 2025-02-20 14:14:16
     */
    public static void f(TreeNode head) {
        if (null == head) {
            return;
        }
        // 1
        f(head.left);
        // 2
        f(head.right);
        // 3
    }

    /**
     * 遍历树 按层级从左到右收集节点
     *
     * @param root
     * @return {@link List< List< Integer>>}
     * @author changyuan
     * @date 2025-02-21 11:13:40
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (null == root) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                assert node != null;
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(0, list);
        }
        return ans;
    }

    public static Integer num;
    public static boolean isValidBST;

    public static boolean isValidBST(TreeNode root) {
        isValidBST = true;
        in1(root);
        return isValidBST;
    }

    public static void in1(TreeNode head) {
        if (!isValidBST) {
            return;
        }
        if (null == head) {
            return;
        }
        // 左
        in1(head.left);
        // 头
        if (num == null) {
            num = head.val;
        } else {
            if (num > head.val) {
                isValidBST = false;
                return;
            } else {
                num = head.val;
            }
        }
        // 右
        in1(head.right);
    }

    public static void main(String[] args) {
//        // 先序 中序 后序
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
//        pre(root);
//        System.out.println();
//        in(root);
//        System.out.println();
//        pos(root);
//        System.out.println();
//        System.out.println("=======================");
//        // 是否为同一个树
//        TreeNode root1 = new TreeNode(1);
//        root1.left = new TreeNode(2);
//        root1.right = new TreeNode(3);
//        root1.left.left = new TreeNode(4);
//        root1.left.right = new TreeNode(5);
//        root1.right.left = new TreeNode(6);
////        root1.right.right = new Node(7);
//        System.out.println(isSameTree(root1, root));
//        // 是否为镜面树
//        System.out.println("=======================");
//        TreeNode root2 = new TreeNode(1);
//        root2.left = new TreeNode(2);
//        root2.right = new TreeNode(2);
//        root2.left.left = new TreeNode(4);
//        root2.left.right = new TreeNode(5);
//        root2.right.left = new TreeNode(5);
//        root2.right.right = new TreeNode(4);
//
//        System.out.println(isMirror(root2));
//        // 树的深度
//        System.out.println("=======================");
//        TreeNode root3 = new TreeNode(1);
//        root3.left = new TreeNode(2);
//        root3.right = new TreeNode(2);
//        root3.left.left = new TreeNode(4);
//        root3.left.right = new TreeNode(5);
//        root3.right.left = new TreeNode(5);
//        root3.right.right = new TreeNode(4);
//        root3.right.right.left = new TreeNode(9);
//        System.out.println(maxDepth(root2));
//        System.out.println(maxDepth(root3));
        TreeNode root4 = new TreeNode(0);
//        root4.left = new TreeNode(1);
//        root4.right = new TreeNode(4);
//        root4.right.left = new TreeNode(3);
//        root4.right.right = new TreeNode(6);
//        System.out.println(isValidBST(root4));
//        System.out.println(-Double.MAX_VALUE);
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(4);
        root5.left.right = new TreeNode(5);
        root5.right = new TreeNode(3);
        root5.right.left = new TreeNode(6);
        root5.right.right = new TreeNode(7);
        posWithStack2(root5);
        posWithStack(root5);
    }
}
