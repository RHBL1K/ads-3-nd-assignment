// Average case: Insertion = deletion = retrieving = searching = O(1)
public class MyHashTable<K, V> {
    private class HashNode<K,V>{
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value){
            this.key = key; // assigning key
            this.value = value; // assigning value
        }

        //  TO string returns everything as a string
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    //
//    private MyArrayList<HashNode<K, V>> chainArray; // Using MyArrayList to create the chains
    private HashNode<K, V>[] chainArray;
    private int M = 11; // number of buckets

    // What is Buckets ??
    // !! Each linked list is accepted as bucket !!
    // Array size indicates number of buckets



    private int size; // number of key-value pairs in the hash table

//    public MyHashTable(){
//        this(11);
//    }

    public MyHashTable() {
        chainArray = new HashNode[M];
    }


    // based on number of buckets creates the chain where assigns every element as null
//    public MyHashTable(int M){
//        this.M = M;
//        this.chainArray = new MyArrayList<>(M);
//        for(int i = 0; i < M; i++){
//            chainArray.add(null);
//        }
//    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }



    // Reflexive || Symmetric || Transitive
    // 31( Or any number )  * x + y Rule

    private int computeHashCode(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int hash = 1;
        for (int i = 0; i < arr.length; i++) {
            hash += 51 * hash + arr[i];
        }

        return hash;
    }



    private int computeHashCode(String s) {
        if (s == null) {
            return 0;
        }

        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = hash * 31 + s.charAt(i);
        }
        return hash;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    private int hash(int[] arr) {
        return Math.abs(computeHashCode(arr)) % M;
    }

    private int hash(String s) {
        return Math.abs(computeHashCode(s)) % M;
    }

    // ---------------------------------------------------------


//    public void put(K key, V value) {
//        int index = hash(key);
//        HashNode<K, V> newNode = new HashNode<>(key, value);
//        if (chainArray[index] == null) {
//            chainArray[index] = newNode;
//        } else {  // Handling Collisions with Chaining
//
//            // If the bucket is not empty, it means a collision occurred—two or more keys
//            // have hashed to the same index. Here, the method employs chaining (using linked lists)
//            // to handle the collision.
//
//
//            HashNode<K, V> current = chainArray[index];
//            while (current.next != null) {
//                if (current.key.equals(key)) {
//                    current.value = value;
//                    return;
//                }
//                current = current.next;
//            }
//
//
//            if (current.key.equals(key)) {
//                current.value = value;
//            } else {
//                current.next = newNode;
//            }
//        }
//        size++;
//    }

    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);


        // if there's no node at the index, directly places the new node there
        if(chainArray[index] == null){
            chainArray[index] = newNode;
            size++;
            return;
        }

        // otherwise, traverses the chain to find the key or the last node
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> lastNode = null;
        while(current != null){
            if(current.key.equals(key)){
                current.value = value; // the value if going to be updated if key founds
                return;
            }
            lastNode = current;
            current = current.next;
        }


        // If key not found, adds the new node at the end of the list
        if(lastNode != null){
            lastNode.next = newNode;
            size++;
        }

    }



    // Gets the value by the given key
    // Steps ::
    // 1. finds the index of the key by calling hash() function
    // 2. accesses the chain (linked list) at the computed index
    // 3. traverses the chain, looking for a node where the node's key matches the given key
    // 4. if a match is found, returns the node's value,if no match is found returns null
    public V get(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key){
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        HashNode<K,V> lastNode = null;
        while(current != null){
            if(current.key.equals(key)){
                if(lastNode != null){
                    lastNode.next = current.next;
                }else{
                    chainArray[index] = current.next;
                }
                size--;
                return current.value;
            }
            lastNode = current;
            current = current.next;
        }
        return null;
    }

    // Checks for if value contains in the chainArray if yes returns true and vice versa
    public boolean contains(V value){
        for(int i = 0; i < chainArray.length; i++){
            HashNode<K,V> current = chainArray[i];
            while(current != null){
                if(current.value.equals(value)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }


    // Returns Key based on given value by checking the entire chainArray elements

    // The worst case in terms of time complexity is O(n)

    // As for iteration it takes from 0 ... n and for search method to find the key of following value it takes constant time O(1)

    // Time Complexity is O(n)
    public K getKey(V value){

        for(int i = 0; i < chainArray.length; i++){

            HashNode<K,V> current = chainArray[i];

            while(current != null){
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }


        }

        return null;
    }

    // Returns the size
    public int size(){
        return size;
    }

    public void printBucketCounts() {
        System.out.println("Bucket distribution:");
        for (int i = 0; i < chainArray.length; i++) {
            int count = 0;
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
                count++;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }


}