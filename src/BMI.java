import java.util.Scanner;

public class BMI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入身高(cm)");
        double height = scanner.nextDouble();

        System.out.print("請輸入體重(kg)");
        double weight = scanner.nextDouble();

        double bmi = weight / Math.pow(height / 100, 2);

        System.out.printf("你的 BMI 為%.2f\n", bmi);
    }
}
