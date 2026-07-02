package com.ibm.day1;


import java.util.Scanner;

public class ZigzagPattern {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = input.nextInt();
        int num = 1;
        for (int row = 0; row < n; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < n; col++) {
                    System.out.print(num + "\t");
                    num++;
                }
            } else {
                int start = num + n - 1;
                for (int col = 0; col < n; col++) {
                    System.out.print(start - col + "\t");
                }
                num += n;
            }
            System.out.println();
        }
        input.close();
    }
}