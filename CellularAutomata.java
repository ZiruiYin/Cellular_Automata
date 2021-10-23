import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CellularAutomata {
    public String[] update(String[] s) {
        String[] copy = Arrays.copyOf(s, s.length);
        for (int k = 3; k < s.length - 3; k += 1) {
            if (s[k].equals("-")) { //dead cell
                if (s[k - 1].equals("#") && s[k + 1].equals("-")){ //left cell alive
                    copy[k] = "#";
                }
                //else if (s[k + 1].equals("#") && s[k - 1].equals("-")){ //right cell alive
                //  copy[k] = "#";
                //}
            }
            else if (s[k - 1].equals("#") && s[k + 1].equals("#")) { //both neighbors alive or dead
                copy[k] = "-";
            }
        }
        s = copy;
        return s;
    }

    public String[] update2(String[] s) {
        String[] copy = Arrays.copyOf(s, s.length);
        for (int k = 3; k < s.length - 3; k += 1) {
            if (s[k].equals("-")) {
                String[] temp = new String[7];
                System.arraycopy(s, k-3, temp, 0, 7);
                if (Collections.frequency(Arrays.asList(temp), "#") > 1 && Collections.frequency(Arrays.asList(temp), "#") < 4) {
                    copy[k] = "#";
                }
            }
            else {
                String[] temp = new String[5];
                System.arraycopy(s, k-2, temp, 0, 5);
                if (Collections.frequency(Arrays.asList(temp), "#") > 3) {
                    copy[k] = "-";
                }
                //if (Collections.frequency(Arrays.asList(temp), "#") > 4) {
                //   copy[k] = "-";
                //}
            }
        }
        s = copy;
        return s;
    }

    public static void main(String[] args) {
        CellularAutomata ca = new CellularAutomata();
        String str = "---#---------##########---#---#--#-##---#--###--##-##-#---";
        //String str = "---####################################################---";
        System.out.println(str.length()-2);
        String[] s = str.split("");
        System.out.println(String.join(" ",s));
        ArrayList<String> output = new ArrayList<>();
        output.add(str);
        for (int k = 1; k <= 10000; k += 1) {
            output.add(String.join(" ",s));
            System.out.println(String.join(" ",ca.update2(s)));
            s = ca.update2(s);
            if (output.contains(String.join(" ",s))) {
                System.out.println("Repeated pattern at trail:");
                System.out.println(k);
                System.out.println("From trial:");
                System.out.println(output.indexOf(String.join(" ",s)));
                break;
            }
        }
    }
}