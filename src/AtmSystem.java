import java.util.Scanner;

class Atm {
    private int balance;

    public Atm(int initialBalance) {
        balance = initialBalance;
    }
    // 選單
    public void showMenu() {
        System.out.println("請選擇操作:");
        System.out.println("1.查詢餘額");
        System.out.println("2.存款");
        System.out.println("3.提款");
        System.out.println("4.離開");
    }
    // 查餘額
    public void checkBalance() {
        System.out.println(balance);
    }
    // 存款
    public void deposit(int amount) {
        if(amount <= 0) {
            System.out.println("存款金額必須大於0元");
        } else {
            balance += amount;
            System.out.printf("→ 已成功存入 $%d！\n", balance);
        }
    }
    // 提款
    public void withdraw(int withdrawals) {
        if(withdrawals > balance) {
            System.out.println("提款金額大於餘額");
        } else {
            balance -= withdrawals;
            System.out.printf("→ 帳戶金額剩餘 $%d！\n", balance);
        }
    }
    // 離開
    public void exit() {
        System.out.println("→ 已離開");
    }
}
public class AtmSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Atm atm = new Atm(0);

        atm.showMenu();
        while(true) {
            System.out.print("輸入選項：");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("請輸入存款金額：");
                    int amount = scanner.nextInt();
                    atm.deposit(amount);
                    break;
                case 3:
                    System.out.print("請輸入提款金額：");
                    int withdrawals = scanner.nextInt();
                    atm.withdraw(withdrawals);
                    break;
                case 4:
                    atm.exit();
                    scanner.close();
                    return;
                default:
                    System.out.println("輸入選項錯誤，請重新輸入!!!");
            }
        }
    }
}
