package parkingCalculator;

import java.util.*;

public class testingDataProvider {
    public static void main(String[] args) {
        Map<String, List> map = new HashMap<>();
        map.put("entryDate", new ArrayList<>(Arrays.asList("one", "two", "thee")));

        System.out.println(map.get("entryDate"));
    }


}
