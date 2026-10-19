
import java.util.Scanner;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long sum = (long) n * (n + 1) / 2;
        System.out.println(sum);

        sc.close();
    }
}
