public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;
    private class Node{
        private K key;
        private V value;
        private Node left, right;
        public Node( K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.value = val;
            // updating the value if the key already exists
        }
        return node;
    }

    public V get(K key){
        return get( root, key);
    }

    private V get(Node node, K key){
        while(node != null){
            int cmp = key.compareTo(node.key);
            if( cmp < 0) node = node.left;
            else if(cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
            // Searching for the key in the left subtree
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
            // Searching for the key in the right subtree
        } else {
            if (node.left == null) {
                return node.right;
                // No left child, replacing node with right child
            }
            if (node.right == null) {
                return node.left;
                // No right child, replace node with left child
            }
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            // In the right subtree deleting the minimum node
            node.left = t.left;
        }
        return node;
    }


    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }
    private Node deleteMin( Node x ){
        if( x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;

    }
    public int size(){
        return size;
    }

    public Iterable<K> iterator() {
        MyArrayList<K> keys = new MyArrayList<>();
        inOrder(root, keys);
        return keys;
    }

    private void inOrder(Node node, MyArrayList<K> keys) {
        if (node != null) {
            inOrder(node.left, keys);
            keys.add(node.key);
            inOrder(node.right, keys);
        }
    }

    public Iterable<Node> nodes() {
        MyArrayList<Node> nodes = new MyArrayList<>();
        inOrderNodes(root, nodes);
        return nodes;
    }

    private void inOrderNodes(Node node, MyArrayList<Node> nodes) {
        if (node != null) {
            inOrderNodes(node.left, nodes);
            nodes.add(node);
            inOrderNodes(node.right, nodes);
        }
    }


}