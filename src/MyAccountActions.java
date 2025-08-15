class MyAccountActions extends Account implements AccountActions {

    public MyAccountActions(String accountNumber, String accountHolderName, double initialBalance, String pinCode) {
        super(accountNumber, accountHolderName, initialBalance, pinCode);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " AZN mədaxil edildi.");
        } else {
            System.out.println("Mədaxil məbləği müsbət olmalıdır.");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Məxaric məbləği müsbət olmalıdır.");
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.println(amount + " AZN məxaric edildi.");
            return true;
        } else {
            System.out.println("Balansda kifayət qədər vəsait yoxdur. Cari balans: " + balance);
            return false;
        }
    }

    @Override
    public void printAccountInfo() {
        super.printAccountInfo();
    }

    @Override
    public void displayAccountType() {
        System.out.println("Hesab tipi: Standart Hesab");
    }
}

