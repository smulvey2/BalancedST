// starter class for a BalancedSearchTree
// you may implement AVL, Red-Black, 2-3 Tree, or 2-3-4 Tree
// be sure to include in class header which tree you have implemented
public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> {
    private static final boolean red = true;
    private static final boolean black = false;

	// inner node class used to store key items and links to other nodes
	protected class Treenode<T extends Comparable<T>> {
		public Treenode(T item, boolean color) {
			this(item,color,null,null);
		}
		public Treenode(T item, boolean color, Treenode<T> left, Treenode<T> right) {
			key = item;
			this.color = color;
			this.left = left;
			this.right = right;
		}
		T key;
		Treenode<T> left;
		Treenode<T> right;
		boolean color;
		
		private void insertHelper(Treenode<T> node) {
		    if (node.key.compareTo(this.key) < 0) {
	            if (this.left == null) {
	               this.left = node;
	            }
	            else {
	                this.left.insertHelper(node);
	            }
	        }
	        else if (node.key.compareTo(this.key) > 0) {
	            if (this.right == null) {
	                this.right = node;
	            }
	            else {
	                this.right.insertHelper(node);
	            }
	        }
	        if (node.key.equals(this.key)) {
	            throw new DuplicateKeyException("This key already exists.");
	        }
		}
		
		private boolean lookupHelper(T item) {
		    boolean temp = false; 
	        if (item.equals(this.key)) {
	            temp = true;
	        } 
	        if (item.compareTo(this.key) < 0) {
	            if (this.left == null) {
	               temp = false;
	            }
	            else {
	                temp = this.left.lookupHelper(item);
	            }
	        }
	        else if (item.compareTo(this.key) > 0) {
	            if (this.right == null) {
	                temp = false;
	            }
	            else {
	                temp = this.right.lookupHelper(item);
	            }
	        }
	        return temp;
		}
		
		private String inAscendingOrderHelper() {
		    String keys = "";
		    keys = keys + root.key;
		    if (this.left == null && this.right != null) {
		        keys = keys + this.right.key;
		        this.right.inAscendingOrderHelper();
		    }
		    if (this.left != null && this.right == null) {
                keys = keys + this.left.key;
                this.left.inAscendingOrderHelper();
            }
		    if (this.left != null && this.right != null) {
                keys = keys + this.right.key;
                keys = keys + this.left.key;
                this.left.inAscendingOrderHelper();
                this.right.inAscendingOrderHelper();
            }
		    return keys;
		}
		
		private int heightHelper() {
		    int count = 0;
		    if (this.left == null && this.right != null) {
                count += 1;
                this.right.heightHelper();
            }
            if (this.left != null && this.right == null) {
                count += 1;
                this.left.heightHelper();
            }
            if (this.left != null && this.right != null) {
                count += 2;
                this.left.heightHelper();
                this.right.heightHelper();
            }
            return count;
		}
	}

	protected Treenode<T> root;

	public String inAscendingOrder() {
		//TODO : must return comma separated list of keys in ascending order
		if (root == null) {
		    return "";
		}
		return root.inAscendingOrderHelper();
		
	}

	public boolean isEmpty() {
		//TODO return empty if there are no keys in structure
		if (root == null) {
		    return true;
		}
		return false;
	}

	public int height() {
		//TODO return the height of this tree
		if (root == null) {
		    return 0;
		}
		return root.heightHelper();
	}

	public boolean lookup(T item) {
		//TODO must return true if item is in tree, otherwise false
		return root.lookupHelper(item);
	}

	public void insert(T item) {
		//TODO if item is null throw IllegalArgumentException, 
		// otherwise insert into balanced search tree
	    if (item == null) {
	        throw new IllegalArgumentException();
	    }
	    if (root == null) {
            root = new Treenode<T>(item, black);
        }
	    else {
	        root.insertHelper(new Treenode<T>(item, red));
	    }
	}

	public void delete(T item) {
		//TODO if item is null or not found in tree, return without error
		// else remove this item key from the tree and rebalance

		// NOTE: if you are unable to get delete implemented
		// it will be at most 5% of the score for this program assignment
	}


	// HINT: define this helper method that can find the smallest key 
	// in a sub-tree with "node" as its root
	// PRE-CONDITION: node is not null
	private T leftMost(Treenode<T> node) {
		// TODO return the key value of the left most node in this subtree
		// or return node's key if node does not have a left child
		return node.key;
	}

}