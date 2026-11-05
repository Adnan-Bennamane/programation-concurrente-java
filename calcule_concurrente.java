
class PrimeThread extends Thread {

    private int start;
    private int end;
    public int count = 0;

    public PrimeThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

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

    public void run() {

        for (int i = start; i <= end; i++) {

            if (isPrime(i)) {
                count++;
            }
        }

        System.out.println(
            Thread.currentThread().getName()
            + " finished. Primes = " + count
        );
    }
}

public class calcule_concurrente {

    public static void main(String[] args)
            throws InterruptedException {

        int limit = 50000000;
        int numberOfThreads = 4;
        int chunk = limit / numberOfThreads;

        PrimeThread[] threads = new PrimeThread[numberOfThreads];

        long start = System.nanoTime();

        // Create and start four threads
        for (int i = 0; i < numberOfThreads; i++) {

            int first = i * chunk + 1;
            int last = (i == numberOfThreads - 1)
                       ? limit
                       : (i + 1) * chunk;

            threads[i] = new PrimeThread(first, last);

            threads[i].start();
        }

        // Wait for all threads
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        // Combine the results
        int total = 0;

        for (int i = 0; i < numberOfThreads; i++) {
            total += threads[i].count;
        }

        long end = System.nanoTime();

        double time = (end - start) / 1_000_000_000.0;

        System.out.println("Concurrent result: " + total);
        System.out.println("Concurrent time: " + time + " seconds");
    }
}
