import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    private final String username;
    private final String password;
    private int balance;
    private int loginFailCount;
    private boolean isLocked;
    private final ArrayList<String> transactionHistory = new ArrayList<>();


    public User(String username, String password, int balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.loginFailCount = 0;
        this.isLocked = false;
    }
    //登入驗證
    public boolean login(String inputUsername, String inputPassword) {
        if(isLocked) return false;

        if(username.equals(inputUsername) && password.equals(inputPassword)) {
            loginFailCount = 0;
            return true;
        } else {
            loginFailCount ++;
            if(loginFailCount >= 3) isLocked = true;
            return false;
        }
    }
    //是否封鎖登入
    public boolean isAccountLocked() {
        return isLocked;
    }
    //取得錯誤次數
    public int getLoginFailCount() {
        return loginFailCount;
    }
    //查餘額
    public int getBalance() {
        return balance;
    }
    //存款
    public void deposit(int amount) {
        if(amount > 0) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            balance += amount;
            transactionHistory.add(time + " 存款$" + amount + " 餘額$" + balance);
        } else {
            System.out.println("存款必須大於0");
        }
    }
    //提款
    public void withdraw(int amount) {
        if(amount > balance) {
            System.out.println("餘額不足，提款失敗");
        } else {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            balance -= amount;
            transactionHistory.add(time + " 提款$" + amount + " 餘額$" + balance);
            System.out.printf("已提款 $%d，剩餘餘額 $%d\n", amount, balance);
        }
    }
    //交易紀錄
    public void printTransactionHistory() {
        if(transactionHistory.isEmpty()) {
            System.out.println("尚無交易紀錄");
        } else {
            System.out.println("交易紀錄如下");
            for(String record : transactionHistory) {
                System.out.println(record);
            }
        }
    }
    //登出
    public String getUsername() {
        return username;
    }
}
