import java.util.Scanner;

public class Hanoi {
    static int steps = 0;

    static void hanoi(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.printf("移動金盤從%c到%c\n", A, C);
            steps++;
        } else {
            hanoi(n - 1, A, C, B);
            hanoi(1, A, B, C);
            hanoi(n - 1, B, A, C);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入任意盤數: ");
        int n = scanner.nextInt();

        hanoi(n, 'A', 'B', 'C');

        System.out.printf("總共移動%d步\n", steps);
    }
}
