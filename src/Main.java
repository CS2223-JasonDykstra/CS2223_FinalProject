public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(100);

        map.put("hi",1);
        map.put("himan",2);
        System.out.println(map.get("himan"));
    }
}
