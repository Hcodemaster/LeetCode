import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /*非递归前序遍历
     * 最重要的就是要理解对象引用与对象之间的关系
     * 这属于最基本的概念，没有理清楚很容易理解不了代码的运行逻辑，
     * 在编程过程中容易造成死循环
     * 对象引用只是指向对象的一个引用，可以理解为一个指针
     * 所以将对象引用赋值为null并不意味着该对象就变成null了!!!!!
     * 一定要注意这点！！！
     */
    public TreeNode pruneTree(TreeNode root) {
        TreeNode head = new TreeNode(-1); //构建一个辅助节点，处理全部节点都是0值节点的情况
        head.left = root;
        head.right = null;
        while(true)
        {
            int cnt = 0;//统计val为0的叶子节点个数
            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = head.left, pre = head;
            while(!stack.empty() || p!=null)
            {
                if(p == null)
                {
                    p = stack.pop();
                    pre = p; 
                    p = p.right;
                }
                while(p != null)
                {
                    if(p.val == 0 && p.left == null && p.right == null)
                    {
                        cnt++;
                        if(pre.left == p)
                            pre.left = null;
                        if(pre.right == p)
                            pre.right = null;
                        p = null;
                    }
                    else
                    {
                        stack.push(p);
                        pre = p;
                        p = p.left;
                    }
                }
            }
            if(cnt == 0)
                break;
        }
        return head.left;
    }
    /*非递归后序遍历
     * 前序遍历多做了一些无用的遍历工作
     * 使用非递归的后续遍历仅用一次遍历过程即可完成
     * 待补充
     */
    public TreeNode pruneTree2(TreeNode root) {
        return null;
    }
    /*递归版本 对于二叉树类型的题目应该优先考虑递归形式 */
    public TreeNode pruneTree3(TreeNode root) {
        if(root == null)
            return root;
        TreeNode left = pruneTree2(root.left);
        TreeNode right = pruneTree2(root.right);
        if(root.val == 0 && left == null && right == null)
            return null;
        root.left = left;
        root.right = right;
        return root;
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
       }
}