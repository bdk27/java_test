import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

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
            User newUser = new User(username, password, balance);
            users.add(newUser);
            saveUserToFile(newUser);
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
                    u.loadTransactionHistory();
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
    //刪除帳號
    public void removeUser(User user) {
        users.remove(user);
    }
    //所有人員清單
    public ArrayList<User> getAllUsers() {
        return users;
    }
    //讀取檔案資料
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    int balance = Integer.parseInt(parts[2]);
                    users.add(new User(username, password, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("讀取檔案失敗: " + e.getMessage());
        }
    }
    //新增資料到檔案中
    public void saveUserToFile(User user) {
        try (FileWriter writer = new FileWriter("users.txt", true)) {
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getBalance() + "\n");
        } catch (IOException e) {
            System.out.println("寫入檔案失敗: " + e.getMessage());
        }
    }
}

public class  AtmAPP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        userManager.loadFromFile();

        while (true) {
            System.out.println("請選擇操作:");
            System.out.println("1.登入");
            System.out.println("2.註冊");
            System.out.println("3.離開");
            System.out.print("輸入選項: ");
            int operation = Integer.parseInt(scanner.nextLine());

            if(operation == 1) {
                System.out.print("請輸入帳號: ");
                String inputusername = scanner.nextLine();
                System.out.print("請輸入密碼: ");
                String inputpassword = scanner.nextLine();

                User loginUser = userManager.login(inputusername, inputpassword);
            if (loginUser != null) {
                    // 進入 ATM 操作
                    while (true) {
                        System.out.println("請選擇操作: ");
                        System.out.println("1.查詢餘額");
                        System.out.println("2.存款");
                        System.out.println("3.提款");
                        System.out.println("4.查詢交易紀錄");
                        System.out.println("5.修改密碼");
                        System.out.println("6.刪除帳號");
                        System.out.println("7.登出");

                        System.out.print("輸入選項: ");
                        int choice = Integer.parseInt(scanner.nextLine()); // 避免 nextInt 留換行問題

                        switch (choice) {
                            case 1:
                                System.out.println("目前餘額為: $" + loginUser.getBalance());
                                break;
                            case 2:
                                System.out.print("請輸入存款金額: ");
                                int depositAmount = Integer.parseInt(scanner.nextLine());
                                loginUser.deposit(depositAmount);
                                break;
                            case 3:
                                System.out.print("請輸入提款金額: ");
                                int withdrawAmount = Integer.parseInt(scanner.nextLine());
                                loginUser.withdraw(withdrawAmount);
                                break;
                            case 4:
                                loginUser.printTransactionHistory();
                                break;
                            case 5:
                                System.out.print("請輸入原密碼: ");
                                String oldPassword = scanner.nextLine();
                                if(oldPassword.equals(loginUser.getPassword())) {
                                    System.out.print("請輸入新密碼: ");
                                    String newPassword = scanner.nextLine();
                                    System.out.print("請再次輸入新密碼: ");
                                    String confirmPassword = scanner.nextLine();

                                    if(newPassword.isEmpty()) {
                                        System.out.print("新密碼不能為空!");
                                    } else if (!newPassword.equals(confirmPassword)) {
                                        System.out.println("兩次輸入的新密碼不一致!");
                                    } else if (newPassword.equals(oldPassword)) {
                                        System.out.println("新密碼不能與舊密碼相同!");
                                    } else {
                                        loginUser.changePassword(newPassword);
                                        System.out.println("密碼已更新成功，請重新登入!");
                                        break;
                                    }
                                } else {
                                    System.out.println("原密碼輸入錯誤!");
                                }
                            case 6:
                                System.out.print("確認刪除帳號，請再次輸入密碼: ");
                                String confirmDelete = scanner.nextLine();

                                if(confirmDelete.equals(loginUser.getPassword())) {
                                    userManager.removeUser(loginUser);
                                    System.out.println("帳號已刪除，系統將登出");
                                    loginUser = null;
                                    choice = 7;
                                } else {
                                    System.out.println("密碼錯誤，取消刪除帳號");
                                }
                                break;
                            case 7:
                                System.out.println("已登出使用者: " + loginUser.getUsername());
                                break;
                            default:
                                System.out.println("請輸入 1～7 的選項");
                        }

                        // 選 7 代表登出 → 跳出 ATM 迴圈回到登入畫面
                        if (choice == 7) break;
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
