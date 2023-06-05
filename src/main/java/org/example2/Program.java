package org.example2;

import java.util.Random;
import java.util.Scanner;

public class Program {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'Y';
    private static final char DOT_EMPTY = '.';

    private static final Scanner scanner = new Scanner(System.in);
    private static char[][] field;
    private static final Random random = new Random();
    private static int fieldSizeX;
    private static int fieldSizeY;


    public static void main(String[] args) {
        initialize();
        printField();

    }

    private static void initialize() {
        fieldSizeX = 10;
        fieldSizeY = 5;

        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeY; x++) {
            for (int y = 0; y < fieldSizeX; y++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();

        for(int i = 0; i <fieldSizeY; i++){
            System.out.print(i+1 +"|");
            for (int j = 0; j < fieldSizeX; j++){
                System.out.print(field[j][i]+"|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }

    }
}