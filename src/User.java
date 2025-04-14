import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {
    private final String username;
    private String password;
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
            String record = time + " 存款$" + amount + " 餘額$" + balance;
            transactionHistory.add(record);
            appendTransactionToFile(record);
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
            String record = time + " 存款$" + amount + " 餘額$" + balance;
            transactionHistory.add(record);
            appendTransactionToFile(record);
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
    //取得帳號
    public String getUsername() {
        return username;
    }
    //取得密碼
    public String getPassword() {
        return password;
    }
    //修改密碼
    public void changePassword(String pwd) {
        password = pwd;
    }
    //將紀錄寫入log
    public void appendTransactionToFile(String record) {
        try {
            File logDir = new File("logs");
            if (!logDir.exists() && !logDir.mkdirs()) {
                System.out.println("建立 logs 資料夾失敗！");
            }

            FileWriter writer = new FileWriter("logs/" + username + ".log", true);
            writer.write(record + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("寫入交易紀錄失敗: " + e.getMessage());
        }
    }
    //讀取紀錄
    public void loadTransactionHistory() {
        File logFile = new File("logs/" + username + ".log");
        if(!logFile.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while((line = reader.readLine()) != null) {
                transactionHistory.add(line);
            }
        } catch (IOException e) {
            System.out.println("讀取交易紀錄失敗: " + e.getMessage());
        }
    }
}
