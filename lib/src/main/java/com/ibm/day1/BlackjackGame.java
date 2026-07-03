package com.ibm.day1;


import java.util.Scanner;

public class BlackjackGame {
    
    public static void main(String[] args) {
        System.out.println("=== BLACKJACK ===");
        System.out.println(blackjack(19, 21)); // 21
        System.out.println(blackjack(22, 18)); // 18
        System.out.println(blackjack(22, 30)); // 0
        System.out.println(blackjack(20, 20)); // 20
        System.out.println(blackjack(5, 17)); // 17
        System.out.println(blackjack(21, 21)); // 21
        
        System.out.println("\n=== SWITCH (Traditional) ===");
        switchTraditional();
        
        System.out.println("\n=== SWITCH (Pattern Matching) ===");
        switchPatternMatching();
        
        System.out.println("\n=== PYRAMID (For Loop) ===");
        pyramidForLoop();
        
        System.out.println("\n=== PYRAMID (While Loop) ===");
        pyramidWhileLoop();
        
        System.out.println("\n=== PYRAMID (Do-While Loop) ===");
        pyramidDoWhileLoop();
    }
    
    public static int blackjack(int a, int b) {
        if (a > 21 && b > 21) {
            return 0;
        }
        if (a > 21) {
            return b;
        }
        if (b > 21) {
            return a;
        }
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
    
    public static void switchTraditional() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number from 1 to 7: ");
        int day = input.nextInt();
        
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day number.");
                
        }
    }
    
    public static void switchPatternMatching() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number from 1 to 7: ");
        int day = input.nextInt();
        
        switch (day) {
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            case 7 -> System.out.println("Sunday");
            default -> System.out.println("Invalid day number.");
        }
    }
    
    public static void pyramidForLoop() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int n = 0;
        
        while (!valid) {
            System.out.print("Enter a positive integer N (1-20): ");
            n = input.nextInt();
            if (n >= 1 && n <= 20) {
                valid = true;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 20.");
            }
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    
    public static void pyramidWhileLoop() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int n = 0;
        
        while (!valid) {
            System.out.print("Enter a positive integer N (1-20): ");
            n = input.nextInt();
            if (n >= 1 && n <= 20) {
                valid = true;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 20.");
            }
        }
        
        int i = 1;
        while (i <= n) {
            int j = 1;
            while (j <= i) {
                System.out.print(j);
                j++;
            }
            System.out.println();
            i++;
        }
    }
    
    public static void pyramidDoWhileLoop() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int n = 0;
        
        do {
            System.out.print("Enter a positive integer N (1-20): ");
            n = input.nextInt();
            if (n >= 1 && n <= 20) {
                valid = true;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 20.");
            }
        } while (!valid);
        
        int i = 1;
        do {
            int j = 1;
            do {
                System.out.print(j);
                j++;
            } while (j <= i);
            System.out.println();
            i++;
        } while (i <= n);
    }
}