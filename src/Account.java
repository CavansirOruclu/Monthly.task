abstract class Account {
    private final String accountNumber;
    private final String accountHolderName;
    protected double balance;
    private final String pinCode;

    public Account(String accountNumber, String accountHolderName, double initialBalance, String pinCode) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pinCode = pinCode;

        if (initialBalance < 0) {
            System.out.println("Xəta: İlkin balans mənfi ola bilməz. Balans sıfır olaraq təyin edildi.");
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPinCode() {
        return pinCode;
    }

    public abstract void displayAccountType();

    public void printAccountInfo() {
        System.out.println("Hesab Nömrəsi: " + accountNumber);
        System.out.println("Hesab Sahibi: " + accountHolderName);
        System.out.println("Cari Balans: " + balance + " AZN");
        displayAccountType();
    }
}
