import java.util.ArrayList;
import java.util.Scanner;

class UserManager {
    private final ArrayList<User> users = new ArrayList<>();

    //檢查是否被使用
    public boolean isUsernameTaken(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    //註冊帳號
    public void register(String username, String password, int balance) {
        if (isUsernameTaken(username)) {
            System.out.println("此帳號已被使用，請重新輸入");
        } else {
            users.add(new User(username, password, balance));
            System.out.println("註冊成功，請重新登入");
        }
    }

    //登入
    public User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                if (u.isAccountLocked()) {
                    System.out.println("此帳號已被鎖定");
                    return null;
                }
                if (u.login(username, password)) {
                    System.out.println("登入成功，歡迎 " + username);
                    return u;
                } else {
                    System.out.println("登入失敗，第 " + u.getLoginFailCount() + " 次錯誤");
                    if (u.isAccountLocked()) {
                        System.out.println("帳號已鎖定");
                    }
                    return null;
                }
            }
        }
        System.out.println("無此帳號");
        return null;
    }

    //所有人員清單
    public ArrayList<User> getAllUsers() {
        return users;
    }
}

public class  AtmAPP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        while (true) {
            System.out.println("請選擇操作:");
            System.out.println("1.登入");
            System.out.println("2.註冊");
            System.out.println("3.離開");
            System.out.print("輸入選項: ");
            int operation = Integer.parseInt(scanner.nextLine());

            if(operation == 1) {
                System.out.print("請輸入帳號:");
                String inputusername = scanner.nextLine();
                System.out.print("請輸入密碼:");
                String inputpassword = scanner.nextLine();

                User loginUser = userManager.login(inputusername, inputpassword);

                if (loginUser != null) {
                    // 進入 ATM 操作
                    while (true) {
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
                                loginUser.printTransactionHistory();
                                break;
                            case 5:
                                System.out.println("已登出使用者：" + loginUser.getUsername());
                                break;
                            default:
                                System.out.println("請輸入 1～4 的選項");
                        }

                        // 選 4 代表登出 → 跳出 ATM 迴圈回到登入畫面
                        if (choice == 5) break;
                    }
                }

            } else if(operation == 2) {
                System.out.print("請輸入帳號: ");
                String username = scanner.nextLine();
                System.out.print("請輸入密碼: ");
                String password = scanner.nextLine();
                System.out.print("請輸入初始金額: ");
                int balance = Integer.parseInt(scanner.nextLine());

                userManager.register(username, password, balance);
            } else if(operation == 3) {
                System.out.println("離開系統");
                return;
            } else {
                System.out.println("無效輸入，請重新選擇");
            }
        }
    }
}
