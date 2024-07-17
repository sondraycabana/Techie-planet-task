import java.util.Scanner;

public class SumOfDigit {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number:");
        long number = scanner.nextLong();

        long result = sumOfDigits(number);

        System.out.println("Sum of digits: " + result);

        scanner.close();
    }
    public static long sumOfDigits(long number) {
        if (number == 0) {
            return 0;
        }
        return number % 10 + sumOfDigits(number / 10);
    }
}
