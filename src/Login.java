import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int times = 0;

        while(times < 3) {
            System.out.print("請輸入帳號:");
            String username = scanner.nextLine();

            System.out.print("請輸入密碼:");
            String password = scanner.nextLine();

            if (username.equals("admin") && password.equals("1234")) {
                System.out.println("→ 登入成功！歡迎" + username);
                return;
            } else {
                times++;
                System.out.println("→ 登入失敗！(第"+times+"次)");
            }
        }

        System.out.printf("→ 您已登入失敗%d次了", times);
        scanner.close();
    }
}
