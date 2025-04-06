public class User {
    private String username;
    private String password;
    private int balance;

    public User(String username, String password, int balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    //登入驗證
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
    //查餘額
    public int getBalance() {
        return balance;
    }
    //存款
    public void deposit(int amount) {
        if(amount > 0) {
            balance += amount;
        } else {
            System.out.println("存款必須大於0");
        }
    }
    //提款
    public void withdraw(int amount) {
        if(amount > balance) {
            System.out.println("餘額不足，提款失敗");
        } else {
            balance -= amount;
            System.out.printf("已提款 $%d，剩餘餘額 $%d\n", amount, balance);
        }
    }
    //登出
    public String getUsername() {
        return username;
    }
}
