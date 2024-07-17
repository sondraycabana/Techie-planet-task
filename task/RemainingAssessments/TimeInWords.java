import java.util.Scanner;

public class TimeInWords {
    private static final String[] NUM_WORDS = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "twenty-one", "twenty-two", "twenty-three", "twenty-four", "twenty-five", "twenty-six",
            "twenty-seven", "twenty-eight", "twenty-nine"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int M = scanner.nextInt();

        System.out.println(convertTimeToWords(H, M));
    }

    private static String convertTimeToWords(int H, int M) {
        if (M == 0) {
            return NUM_WORDS[H] + " o'clock";
        } else if (M == 15) {
            return "quarter past " + NUM_WORDS[H];
        } else if (M == 30) {
            return "half past " + NUM_WORDS[H];
        } else if (M == 45) {
            return "quarter to " + NUM_WORDS[(H % 12) + 1];
        } else if (M < 30) {
            if (M == 1) {
                return "one minute past " + NUM_WORDS[H];
            } else {
                return NUM_WORDS[M] + " minutes past " + NUM_WORDS[H];
            }
        } else {
            int remainingMinutes = 60 - M;
            if (remainingMinutes == 1) {
                return "one minute to " + NUM_WORDS[(H % 12) + 1];
            } else {
                return NUM_WORDS[remainingMinutes] + " minutes to " + NUM_WORDS[(H % 12) + 1];
            }
        }
    }
}
