import java.util.Scanner;
import java.util.Random;

public class Generator {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    private int passwordLength;
    private int generationMode;

    public Generator() {
        do {
            String password = "";
            generationMode = chooseGeneration();

            chooseLength();

            password = generator(password);
            System.out.println(password);

        } while (checkYesOrNo());
    }

    private void chooseLength() {
        do {
            System.out.println("Enter password length from 8 to 20:");
            passwordLength = scanner.nextInt();
        } while (passwordLength < 8 || passwordLength > 20);
    }

    private int chooseGeneration() {
        int x;

        do {
            System.out.println("1 - generate random password , 2 - generate custom password (choose symbols):");
            x = scanner.nextInt();  // cmd + alt + L = auto-format
        } while (x != 1 && x != 2);

        return x;
    }

    private boolean checkYesOrNo() {
        System.out.println("Enter \"y\" to continue, \"n\" to exit");
        String yn = scanner.next();
        if (yn.equals("y")) {
            return true;
        } else if (yn.equals("n")) {
            return false;
        } else {
            checkYesOrNo();
        }
        return false;
    }

    private String generator(String password) {
        if (generationMode == 1) {
            for (int i = 0; i < passwordLength; i++) {
                int digit = random.nextInt(3);

                switch (digit) {
                    case 0:
                        password = password.concat(String.valueOf(random.nextInt(10)));
                        break;
                    case 1:
                        char x = (char) (random.nextInt(26) + 97);
                        password = password.concat(String.valueOf(x));
                        break;
                    case 2:
                        char y = (char) (random.nextInt(26) + 65);
                        password = password.concat(String.valueOf(y));
                }
            }
        } else {
            int customMode = chooseCustomGeneration();

            for (int i = 0; i < passwordLength; i++) {
                switch (customMode) {
                    case 1 -> {
                        password = password.concat(String.valueOf(onlyDigits()));
                    }
                    case 2 -> {
                        password = password.concat(String.valueOf(onlySmallLetters()));
                    }
                    case 3 -> {
                        password = password.concat(String.valueOf(onlyBigLetters()));
                    }
                    case 12 -> {
                        int digit = random.nextInt(2);

                        if (digit == 0) {
                            password = password.concat(String.valueOf(onlyDigits()));
                        } else {
                            password = password.concat(String.valueOf(onlySmallLetters()));
                        }
                    }
                    case 13 -> {
                        int digit = random.nextInt(2);

                        if (digit == 0) {
                            password = password.concat(String.valueOf(onlyDigits()));
                        } else {
                            password = password.concat(String.valueOf(onlyBigLetters()));
                        }
                    }
                    case 23 -> {
                        int digit = random.nextInt(2);

                        if (digit == 0) {
                            password = password.concat(String.valueOf(onlyBigLetters()));
                        } else {
                            password = password.concat(String.valueOf(onlySmallLetters()));
                        }
                    }
                }
            }
        }
        return password;
    }

    private int onlyDigits() {
        return random.nextInt(10);
    }

    private char onlySmallLetters() {
        return (char) (random.nextInt(26) + 97);
    }

    private char onlyBigLetters() {
        return (char) (random.nextInt(26) + 65);
    }

    private int chooseCustomGeneration() {
        int x;

        do {
            System.out.println("1 - digits , 2 - small letters, 3 - big letters");
            x = scanner.nextInt();  // cmd + alt + L = auto-format
        } while (x != 1 && x != 2 && x != 3 && x != 12 && x != 13 && x != 23);

        return x;
    }
}
