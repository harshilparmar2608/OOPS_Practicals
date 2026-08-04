import java.util.Scanner;

public class Practical1A {

    enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {
        int price = 15;
        int total = 0;

        Scanner sc = new Scanner(System.in);
       
        while (total < price) {
            Coin coin = Coin.valueOf(sc.next().toUpperCase());

            int value = 0;

            switch (coin) {
                case ONE:
                    value = 1;
                    break;
                case TWO:
                    value = 2;
                    break;
                case FIVE:
                    value = 5;
                    break;
                case TEN:
                    value = 10;
                    break;
            }

            total += value;
            System.out.println("Total so far: " + total);
        }

        System.out.println("Paid. Change: " + (total - price));

        sc.close();
    }
}