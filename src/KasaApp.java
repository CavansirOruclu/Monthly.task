import java.util.*;
public class KasaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n========== Bakı Trust Bank ===============");
        System.out.println("Bankımıza xoş gəlmisiniz! Burada müştərilərimiz üçün aşağıdakı əməliyyatlar mövcuddur:\n" +
                "\n" + "Hesaba pul yatırmaq (mədaxil)\n" +
                "\n" + "Hesabdan pul çıxarmaq (məxaric)\n" +
                "\n" + "Balansı yoxlamaq");

        System.out.println("====================================");
        System.out.println("\nZəhmət olmasa, hesabınızı yaradın.");

        String accountNumber;
        while (true) {// kartin 16 reqemli olub olmamasının yoxlanılması
            System.out.print("Hesab nömrəsini daxil edin (16 rəqəm): ");
            accountNumber = scanner.nextLine();

            if (accountNumber.matches("\\d{16}")) {
                break;
            } else {
                System.out.println("Xəta! Hesab nömrəsi yalnız 16 rəqəmdən ibarət olmalıdır.");
            }
        }

        System.out.print("Hesab sahibinin adını daxil edin: ");
        String accountHolderName = scanner.nextLine();

        double initialBalance;
        System.out.print("İlkin balansı daxil edin (AZN): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Yanlış giriş! Zəhmət olmasa, bir rəqəm daxil edin.");
            scanner.next();
            System.out.print("İlkin balansı daxil edin (AZN): ");
        }
        initialBalance = scanner.nextDouble();
        scanner.nextLine();

        String pinCode;
        while (true) {//bank hesabının parolu daxili ve yoxlanilması
            System.out.print("4 rəqəmli PIN kod daxil edin: ");
            pinCode = scanner.nextLine();
            if (pinCode.matches("\\d{4}")) {
                break;
            } else {
                System.out.println("Xəta! PIN kod yalnız 4 rəqəmdən ibarət olmalıdır.");
            }
        }

        AccountActions account = new MyAccountActions(accountNumber, accountHolderName, initialBalance, pinCode);
        System.out.println("Hesab uğurla yaradıldı.");

        int choice;
        do {
            System.out.println("\n====================================");
            System.out.println("           --- Əsas Menyu ---       ");
            System.out.println("====================================");
            System.out.println("1. Balansı göstər");
            System.out.println("2. Pul mədaxil et");
            System.out.println("3. Pul məxaric et");
            System.out.println("4. Hesab məlumatlarını göstər");
            System.out.println("0. Çıxış");
            System.out.println("====================================");
            System.out.print("Seçiminizi daxil edin: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Yanlış giriş! Zəhmət olmasa, bir rəqəm daxil edin.");
                scanner.next();
                System.out.print("Seçiminizi daxil edin: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1://balansi gostermek
                    System.out.println("Cari Balans: " + account.getBalance() + " AZN");
                    break;

                case 2:// pul medaxil
                    if (verifyPin(scanner, ((MyAccountActions) account).getPinCode())) {
                        System.out.print("Mədaxil etmək istədiyiniz məbləği daxil edin (AZN): ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();
                        account.deposit(depositAmount);
                        System.out.println("Cari Balans: " + account.getBalance() + " AZN");
                    }
                    break;

                case 3://pul mexaric
                    if (verifyPin(scanner, ((MyAccountActions) account).getPinCode())) {
                        System.out.print("Məxaric etmək istədiyiniz məbləği daxil edin (AZN): ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();
                        account.withdraw(withdrawAmount);
                        System.out.println("Cari Balans: " + account.getBalance() + " AZN");
                    }
                    break;

                case 4:
                    account.printAccountInfo();
                    break;

                case 0:
                    System.out.println("Bank hesabından çıxılır. Təşəkkür edirik!");
                    break;

                default:
                    System.out.println("Yanlış seçim! Zəhmət olmasa, 0-4 daxil edin.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }


    public static boolean verifyPin(Scanner scanner, String correctPin) {
        System.out.print("PIN kodu daxil edin: ");
        String enteredPin = scanner.nextLine();
        if (enteredPin.equals(correctPin)) {
            return true;
        } else {
            System.out.println("Xəta! PIN kod səhvdir.Yeniden cehd edin ");
            return false;
        }
    }
}
