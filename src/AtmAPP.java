import java.util.ArrayList;
import java.util.Scanner;

public class AtmAPP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 建立使用者清單（模擬資料庫）
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("chris", "1234", 10000));
        users.add(new User("amy", "5678", 5000));

        while (true) {
            System.out.println("請先登入");

            System.out.print("請輸入帳號:");
            String username = scanner.nextLine();
            System.out.print("請輸入密碼:");
            String password = scanner.nextLine();

            User loginUser = null;
            // 檢查帳密是否正確
            for (User user : users) {
                if (user.login(username, password)) {
                    loginUser = user;
                    break;
                }
            }

            if(loginUser == null) {
                System.out.println("登入失敗，請重試!");
                continue;
            }
            System.out.printf("登入成功! 歡迎%s\n", loginUser.getUsername());

            // 進入 ATM 操作
            while (true) {
                System.out.println("請選擇操作：");
                System.out.println("1.查詢餘額");
                System.out.println("2.存款");
                System.out.println("3.提款");
                System.out.println("4.登出");

                System.out.print("輸入選項：");
                int choice = Integer.parseInt(scanner.nextLine()); // 避免 nextInt 留換行問題

                switch (choice) {
                    case 1:
                        System.out.println("目前餘額為：$" + loginUser.getBalance());
                        break;
                    case 2:
                        System.out.print("請輸入存款金額：");
                        int depositAmount = Integer.parseInt(scanner.nextLine());
                        loginUser.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("請輸入提款金額：");
                        int withdrawAmount = Integer.parseInt(scanner.nextLine());
                        loginUser.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("已登出使用者：" + loginUser.getUsername());
                        break;
                    default:
                        System.out.println("請輸入 1～4 的選項");
                }

                // 選 4 代表登出 → 跳出 ATM 迴圈回到登入畫面
                if (choice == 4) break;
            }

        }
    }
}
