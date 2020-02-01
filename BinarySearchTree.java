
/**
 * Binary Search Tree Data Structure,
 * 
 * Supported Operations are
 * 1. Create a Tree
 * 2. Insert a Node
 * 3. Delete a Node
 * 4. In Order Traversal of the Tree
 *
 */

public class BinarySearchTree {

	public static void main(String[] args) {

		BSTree bstree = new BSTree();
		
		//Build Tree
		bstree.insertNode(10);
		bstree.insertNode(11);
		bstree.insertNode(6);
		bstree.insertNode(5);
		bstree.insertNode(9);

		//View In-order Array
		bstree.inorderTraversal();

		//Delete Node
		bstree.deleteNode(11);
		
		//View the Result
		bstree.inorderTraversal();
	}

}

class BSTree {

	BSTNode root;

	public void insertNode(int val) {
		root = insertNode(root, val);
	}

	private BSTNode insertNode(BSTNode root, int val) {

		if (root == null) {
			root = new BSTNode(val);
			return root;
		}

		if (val < root.val) {
			root.left = insertNode(root.left, val);
		} else {
			root.right = insertNode(root.right, val);
		}

		return root;
	}

	public void inorderTraversal() {
		inorderTraversal(root);
	}

	public void inorderTraversal(BSTNode node) {

		if (node == null)
			return;
		inorderTraversal(node.left);
		System.out.println(node.val);
		inorderTraversal(node.right);
	}

	public BSTNode searchNode(int val) {
		return searchNode(root, val);
	}

	private BSTNode searchNode(BSTNode root, int val) {

		if (root.val == val)
			return root;

		if (val < root.val) {
			return searchNode(root.left, val);
		} else {
			return searchNode(root.right, val);
		}
	}

	public void deleteNode(int val) {
		deleteNode(root, val);
	}

	private BSTNode deleteNode(BSTNode root, int val) {

		if (root == null)
			return root;

		if (val < root.val)
			root.left = deleteNode(root.left, val);
		else if (val > root.val)
			root.right = deleteNode(root.right, val);
		else {

			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			root.val = minValue(root.right);

			root.right = deleteNode(root.right, root.val);
		}

		return root;

	}

	int minValue(BSTNode root) {
		int minv = root.val;
		while (root.left != null) {
			minv = root.left.val;
			root = root.left;
		}
		return minv;
	}

}

class BSTNode {

	BSTNode left;
	BSTNode right;
	int val;

	BSTNode(int val) {
		this.val = val;
	}

}
