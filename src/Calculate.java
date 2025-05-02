import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number: ");
        int number = scanner.nextInt();

        String ans;
        if (number % 3 == 0 && number % 5 == 0) {
            ans = "Fizzbuzz";
        } else if (number % 5 == 0) {
            ans = "Fizz";
        } else if (number % 3 == 0) {
            ans = "buzz";
        } else {
            ans = number + "";
        }

        System.out.println(ans);
    }
}
