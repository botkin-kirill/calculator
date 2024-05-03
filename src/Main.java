import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("введите выражение, разделяйте переменные пробелом");
        String input = new Scanner(System.in).nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input){
        return Calculator.calculate(input);
    }
}