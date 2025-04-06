public class AtmAPP {
    public static void main(String[] args) {
        User user = new User("amy", "123", 10000);
        if(user.login("amy", "123")) {
            user.deposit(1000);
            user.withdraw(500);
        }
    }
}
