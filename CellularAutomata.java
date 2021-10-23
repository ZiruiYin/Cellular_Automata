import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CellularAutomata {
    public String[] update(String[] s) {
        String[] copy = Arrays.copyOf(s, s.length);
        for (int k = 3; k < s.length - 3; k += 1) {
            if (s[k].equals("-")) { //dead cell
                if (s[k - 1].equals("#") && s[k + 1].equals("-")){
                    copy[k] = "#";
                }
                else if (s[k + 1].equals("#") && s[k - 1].equals("-")){
                  copy[k] = "#";
                }
            }
            else if (s[k - 1].equals("#") && s[k + 1].equals("#")) {
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
                if (Collections.frequency(Arrays.asList(temp), "#") > 1
                        && Collections.frequency(Arrays.asList(temp), "#") < 4) {
                    copy[k] = "#";
                }
            }
            else {
                String[] temp = new String[5];
                System.arraycopy(s, k-2, temp, 0, 5);
                if (Collections.frequency(Arrays.asList(temp), "#") > 3) {
                    copy[k] = "-";
                }
                //if (Collections.frequency(Arrays.asList(temp), "#") > 4) { //This will create a fixed loop much sooner
                //   copy[k] = "-";
                //}
            }
        }
        s = copy;
        return s;
    }

    public static void main(String[] args) {
        CellularAutomata ca = new CellularAutomata();
        String str1 = "---#------########---#---#--#-##---#--###--##-##-##---###-#--##-###-#--#-###-##-###---";
        String str2 = "---################################################################################---";
        String str3 = "-------------###--------#####-#----####---###------##-##-#-------#--##-#---#----##----";
        String str4 = "--------------------------------------------------------------------------------------";
        String str = str3;
        String[] s = str.split("");
        System.out.println(String.join(" ",s));
        ArrayList<String> output = new ArrayList<>();
        output.add(str);
        for (int k = 1; k <= 100000; k += 1) {
            output.add(String.join(" ",s));
            s = ca.update2(s); //Change this to update/update2 for different methods
            System.out.println(String.join(" ",s));
            if (output.contains(String.join(" ",s))) {
                System.out.println("Repeated pattern at trail: " + k);
                System.out.println("From trial: " + (output.indexOf(String.join(" ",s)) - 1));
                break;
            }
        }
        System.out.println("Number of cells: " + (str.length()-6));
    }
}