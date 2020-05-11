import java.util.ArrayList;
public class HashMap <K,V> {
    private class Node{
        K key;
        V value;
        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private class Bucket{
        ArrayList<Node> nodes = new ArrayList<>();
        Node get(K key){
            for(Node n : nodes){
                if(key.equals(n.key)){
                    return n;
                }
            }
            return null;
        }
        boolean add(Node in){
            for(Node n : nodes){
                if(in.key.equals(n.key)){
                    n.value = in.value;
                    return true;
                }
            }
            //add at front of array list
            nodes.add(0, in);
            return false;
        }
        boolean remove(K key){
            for(int i = 0; i < nodes.size(); i++){
                if(key.equals(nodes.get(i).key)){
                    nodes.get(i).value = null;
                    nodes.get(i).key = null; //cleaning up earlier rather than when the JVM notices
                    nodes.remove(i);
                    return true;
                }
            }
            return false;
        }
    }
    public ArrayList<Bucket> buckets;
    private Hasher<K> hasher;
    private int size;
    public HashMap(Hasher<K> hasher, int size){
        this.hasher = hasher;
        this.buckets = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            this.buckets.add(new Bucket());
        }

        this.size = size;
    }
    // default size is 1000
    public HashMap(Hasher<K> hasher){
        this(hasher,1051);
    }
    public HashMap(int size){
        this(new Hasher<K>() {
            @Override
            public int hash(K input) {
                return input.hashCode();
            }
        },size);
    }
    public V getOrDefault(K key, V d){
        Bucket bucket = buckets.get(hasher.hash(key)%this.size);
        Node r;
        return bucket == null? d : (r = bucket.get(key))==null ? d : r.value;
    }

    /**
     *
     * @param key
     * @return the associated value with the input value
     */
    public V get(K key){
        return getOrDefault(key,null);
    }

    /**
     *
     * @param key
     * @return true if item was removed, false if it wasn't in the hashmap
     */
    public boolean remove(K key){
        Bucket bucket = buckets.get(hasher.hash(key)%this.size);
        return bucket != null && bucket.remove(key);
    }

    /**
     *
     * @param key
     * @param value
     * @return true if you're overriding a node, false otherwise
     */
    public boolean put(K key, V value){
        Node adding = new Node(key,value);

        Bucket bucket = buckets.get(hasher.hash(key)%this.size);
        return bucket != null && bucket.add(adding);
    }
}
