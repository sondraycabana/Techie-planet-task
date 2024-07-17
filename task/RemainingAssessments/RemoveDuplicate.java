import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveDuplicates {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine().trim());

        List<int[]> array = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            List<Integer> rowList = new ArrayList<>();
            StringBuilder number = new StringBuilder();
            for (char ch : line.toCharArray()) {
                if (Character.isDigit(ch)) {
                    number.append(ch);
                } else if (ch == ',' || ch == '}') {
                    if (number.length() > 0) {
                        rowList.add(Integer.parseInt(number.toString()));
                        number.setLength(0);
                    }
                }
            }
            int[] row = new int[rowList.size()];
            for (int j = 0; j < rowList.size(); j++) {
                row[j] = rowList.get(j);
            }
            array.add(row);
        }

        for (int[] row : array) {
            removeDuplicates(row);
        }

        printArray(array);
    }

    private static void removeDuplicates(int[] row) {

        int maxVal = 500000;
        boolean[] seen = new boolean[maxVal + 1];

        for (int i = 0; i < row.length; i++) {
            int num = row[i];
            if (num > 0 && num <= maxVal) {
                if (seen[num]) {
                    row[i] = 0;
                } else {
                    seen[num] = true;
                }
            }
        }
    }

    private static void printArray(List<int[]> array) {
        for (int[] row : array) {
            System.out.print("{");
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
    }
}

