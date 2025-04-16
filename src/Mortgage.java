import java.util.Scanner;

public class Mortgage {
    public static void main(String[] args) {
        final int MONTHS_OF_YEAR = 12;
        final int PERCENT = 100;
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入本金: ");
        int principal = scanner.nextInt();

        System.out.print("請輸入利率: ");
        float annualRate = scanner.nextFloat();
        float monthlyRate = annualRate / PERCENT / MONTHS_OF_YEAR;

        System.out.print("請輸入時間(年): ");
        int years = scanner.nextInt();
        int numberOfPayment = years * MONTHS_OF_YEAR;

        double mortgage = principal
                * (monthlyRate * Math.pow((1 + monthlyRate), numberOfPayment))
                / (Math.pow((1 + monthlyRate), numberOfPayment) - 1);

        System.out.printf("貸款金額: $%.2f", mortgage);
    }
}
