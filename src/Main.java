import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>(20000);

        //map.put("hi",1);
        //map.put("himan",2);
        //System.out.println(map.get("himan"));
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("mobyDick.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        //File file = new File(System.getProperty("user.dir") + "/ElephantsChild.txt");

        BufferedReader br = new BufferedReader(streamReader);

        int counter = 0;
        ArrayList<String> words = new ArrayList<String>();

        try {
            String st = "";

            //initialize the arrayList of words by separating lines by spaces
            while ((st = br.readLine()) != null) {
                for (String s : st.split("\\s+")) {
                    //removes anything that's not an apostrophe, dash, or letter
                    String formattedString = s.replaceAll("[^'\\-a-zA-Z]*", "");

                    if(!formattedString.equals("")){
                        words.add(formattedString);
                        ++counter;
                    }
                }
            }
        } catch (IOException i) {
            System.out.println("IOException");
        }


        int C = 123;
        int m = 20000;
        for(String s : words){
            int hash = 0;
            for (Character c : s.toCharArray()) {
                hash = (hash * C + (int) (c)) % m;
            }
            System.out.println("Adding word '" + s + "' with hash " + hash);
            map.put(hash, s);


        }

        System.out.println("Num Words: " + counter);

    }
}
