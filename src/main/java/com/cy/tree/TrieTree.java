package com.cy.tree;

/**
 * 前缀树
 *
 * @author changyuan
 * @date 2025-03-09 18:37:19
 */
public class TrieTree {
    static class Node {
        /**
         * 经过多少次
         */
        public int pass;
        /**
         * 以这个结尾
         */
        public int end;
        /**
         * 0~25表示a~z
         * children[i] == null 表示i方向的路不存在
         * children[i] != null 表示i方向的路存在
         */
        public Node[] children;

        public Node() {
            pass = 0;
            end = 0;
            children = new Node[26];
        }
    }

    static class Trie1 {
        private Node root;

        public Trie1() {
            root = new Node();
        }

        public void insert(String word) {
            if (null == word) {
                return;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            node.pass++;
            int path;
            for (char aChar : chars) {
                path = aChar - 'a';
                if (node.children[path] == null) {
                    node.children[path] = new Node();
                }
                node = node.children[path];
                node.pass++;
            }
            node.end++;
        }

        public int search(String word) {
            if (null == word) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            for (char aChar : chars) {
                if ((node = node.children[aChar - 'a']) == null) {
                    return 0;
                }
            }
            return node.end;
        }

        public int startsWith(String prefix) {
            if (null == prefix) {
                return 0;
            }
            char[] chars = prefix.toCharArray();
            Node node = root;
            for (char aChar : chars) {
                if ((node = node.children[aChar - 'a']) == null) {
                    return 0;
                }
            }
            return node.pass;
        }

        public void delete(String word) {
            if (0 != search(word)) {
                char[] chars = word.toCharArray();
                Node node = root;
                node.pass--;
                int path;
                for (char aChar : chars) {
                    path = aChar - 'a';
                    if (--node.children[path].pass == 0) {
                        // --pass后若为0 则表示没有这个路径了 直接设置为null 防止内存泄漏
                        node.children[path] = null;
                        return;
                    }
                    node = node.children[path];
                }
                node.end--;
            }
        }
    }


    public static void main(String[] args) {
        Trie1 trieTree = new Trie1();
        trieTree.insert("abc");
        trieTree.insert("abcd");
        System.out.println(trieTree.search("abcz"));
        System.out.println(trieTree.startsWith("abc"));
    }
}
