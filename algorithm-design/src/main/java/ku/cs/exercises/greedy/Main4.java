package ku.cs.exercises.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Main4 {

    public static void minTime(int n, int t, int[] arrival) {
        if (arrival.length == 0) return;

        int totalTime = 0;
        int currentCar = 0;
        int travelCount = 0;

        Arrays.sort(arrival); // O (m log(m))
        for (int i = 0; i < arrival.length - n; i++) { // O(m)
            if (arrival[i + 1] - arrival[i] < t) currentCar++;
            else currentCar = n;
            if (currentCar == n) {
                travelCount++;
                currentCar = 0;
                continue;
            }
        }

        // O(m) if sorting algorithm is not count or sorting algorithm is counting sort algorithm

        totalTime += arrival[arrival.length - 1] + t;
        travelCount++;
        System.out.println("Minimum time = " + totalTime);
        System.out.println("Travel Count = " + travelCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int m = scanner.nextInt();

        int[] arrival = new int[m];

        for (int i = 0; i < m; i++) 
            arrival[i] = scanner.nextInt();
        
        minTime(n, t, arrival);

        scanner.close();
    }
}
