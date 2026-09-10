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
    // Variable to track the number of valid nodes
    private int matchCount = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return matchCount;
    }

    // Helper method returns an array: [sum of subtree, count of nodes in subtree]
    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // 1. Traverse left and right children
        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        // 2. Calculate sum and count for the current subtree
        int currentSum = left[0] + right[0] + node.val;
        int currentCount = left[1] + right[1] + 1;

        // 3. Check if the average equals the current node's value
        // Integer division automatically rounds down
        if (currentSum / currentCount == node.val) {
            matchCount++;
        }

        // 4. Return the aggregated sum and count to the parent node
        return new int[]{currentSum, currentCount};
    }
}
