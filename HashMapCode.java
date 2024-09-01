import java.util.*;

public class HashMapCode {
    static class HashMap<K, V> {
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size;
        private int bucketCount;
        private LinkedList<Node>[] buckets;

        @SuppressWarnings("unchecked")
        public HashMap() {
            this.bucketCount = 4;
            this.buckets = new LinkedList[bucketCount];
            for (int i = 0; i < bucketCount; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        private int getBucketIndex(K key) {
            return Math.abs(key.hashCode()) % bucketCount;
        }

        private int findNodeIndex(K key, int bucketIndex) {
            LinkedList<Node> bucket = buckets[bucketIndex];
            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).key.equals(key)) {
                    return i;
                }
            }
            return -1;
        }

        public void put(K key, V value) {
            int bucketIndex = getBucketIndex(key);
            int nodeIndex = findNodeIndex(key, bucketIndex);

            if (nodeIndex == -1) {
                buckets[bucketIndex].add(new Node(key, value));
                size++;
            } else {
                buckets[bucketIndex].get(nodeIndex).value = value;
            }

            if ((double) size / bucketCount > 2.0) {
                rehash();
            }
        }

        public V get(K key) {
            int bucketIndex = getBucketIndex(key);
            int nodeIndex = findNodeIndex(key, bucketIndex);

            if (nodeIndex != -1) {
                return buckets[bucketIndex].get(nodeIndex).value;
            }
            return null;
        }

        public boolean containsKey(K key) {
            return get(key) != null;
        }

        public V remove(K key) {
            int bucketIndex = getBucketIndex(key);
            int nodeIndex = findNodeIndex(key, bucketIndex);

            if (nodeIndex != -1) {
                Node node = buckets[bucketIndex].remove(nodeIndex);
                size--;
                return node.value;
            }
            return null;
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();
            for (LinkedList<Node> bucket : buckets) {
                for (Node node : bucket) {
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<Node>[] oldBuckets = buckets;
            bucketCount *= 2;
            buckets = new LinkedList[bucketCount];
            for (int i = 0; i < bucketCount; i++) {
                buckets[i] = new LinkedList<>();
            }

            size = 0;
            for (LinkedList<Node> bucket : oldBuckets) {
                for (Node node : bucket) {
                    put(node.key, node.value);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("India", 190);
        map.put("China", 200);
        map.put("US", 50);

        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }

        map.remove("India");
        System.out.println(map.get("India"));  // This will print 'null'
    }
}
