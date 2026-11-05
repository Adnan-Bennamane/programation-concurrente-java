public class calcule_normale {
       // Check if a number is prime
    static boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int limit = 500000;
        int count = 0;

        double start = System.nanoTime();

        // One thread checks all numbers
        for (int i = 1; i <= limit; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        double end = System.nanoTime();

        double time = (end - start) / 1_000_000_000.0;

        System.out.println("Sequential result: " + count);
        System.out.println("Sequential time: " + time + " seconds");
    }
}
