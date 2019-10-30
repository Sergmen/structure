import java.util.*;
import java.util.stream.Collectors;

public class Structure {

    static String testString = "Своенравная принцесса считает что по ее велению в зимнюю стужу могут расцвести подснежники поэтому она издает указ щедро наградить любого кто пpинесет ей заветные цветы";


    public static void main(String[] args) {
        System.out.println(getStructure(testString));
    }



    public static Map<String, List<String>> getStructure(String str){

        class StringComparator implements Comparator<String> {
            public int compare(String a, String b){
                if (a.length()>b.length()) return -1;
                else if (a.length()<b.length()) return 1;
                else return a.toLowerCase().compareTo(b.toLowerCase());
            }
        }


        Map<String, List<String>> map = new HashMap<>();
        String[] strMass = str.split(" ");

        for (String s : strMass) {
            String firstLetter = s.substring(0,1).toLowerCase();
            if (map.containsKey(firstLetter)){
                List<String> list = map.get(firstLetter);
                if (!list.contains(s)) list.add(s);
                }
            else {
                List<String> addList = new ArrayList<>();
                addList.add(s);
                map.put(firstLetter,addList);
            }
        }
        map=map.entrySet()
               .stream()
               .filter(p->p.getValue().size()>1)
               .collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));

        StringComparator comp = new StringComparator();
        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
            Collections.sort(stringListEntry.getValue(), comp);
        }

        return map;
    }


}
