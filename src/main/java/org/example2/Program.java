package org.example2;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Program {
    private static final int WIN_COUNT = 4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'Y';
    private static final char DOT_EMPTY = '.';

    private static final Scanner scanner = new Scanner(System.in);
    private static char[][] field;
    private static final Random random = new Random();
    private static int fieldSizeX;
    private static int fieldSizeY;


    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы смогли выиграть")) break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Вы проиграли")) break;
            }
            System.out.println("Желаете снова попробовать? (Yes):");
            if (scanner.next().equalsIgnoreCase("Y")) break;

        }

    }

    private static void initialize() {
        fieldSizeX = 5;
        fieldSizeY = 5;

        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /*
    Метод printField() доработан с учетом возвожного расширения поля
     */

    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[j][i] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до " + fieldSizeY + ") через пробел >>> ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private static void aiTurn() {
        int i, j;
        boolean flag = false;
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {

                if (isCellValid(x, y) && isCellValid(x, y + 1) && isCellValid(x, y + 2)) {
                    if (field[x][y] == DOT_HUMAN && field[x][y + 1] == DOT_HUMAN && field[x][y + 2] == DOT_HUMAN &&
                            isCellValid(x, y - 1) && isCellEmpty(x, y - 1)) {
                        field[x][y - 1] = DOT_AI;
                        flag = true;
                    }
                    if (field[x][y] == DOT_HUMAN && field[x][y + 1] == DOT_HUMAN && field[x][y + 2] == DOT_HUMAN &&
                            isCellValid(x, y + 3) && isCellEmpty(x, y + 3) && flag == false) {
                        field[x][y + 3] = DOT_AI;
                        flag = true;
                    }
                }
                if (isCellValid(x, y) && isCellValid(x+1, y) && isCellValid(x +2, y)) {
                    if (field[x][y] == DOT_HUMAN && field[x +1 ][y] == DOT_HUMAN && field[x +2 ][y] == DOT_HUMAN &&
                            isCellValid(x - 1, y) && isCellEmpty(x - 1, y)) {
                        field[x-1][y] = DOT_AI;
                        flag = true;
                    }
                    if (field[x][y] == DOT_HUMAN && field[x +1][y] == DOT_HUMAN && field[x +2][y] == DOT_HUMAN &&
                            isCellValid(x +3, y ) && isCellEmpty(x +3 , y) && flag == false) {
                        field[x+3][y] = DOT_AI;
                        flag = true;
                    }
                }
//                if (isCellValid(x, y) && isCellValid(x + 1, y) && isCellValid(x + 2, y) && isCellValid(x - 1, y) ||
//                        (isCellValid(x + 3, y))) {
//                    if (field[x][y] == DOT_HUMAN && field[x + 1][y] == DOT_HUMAN && field[x + 2][y] == DOT_HUMAN && isCellEmpty(x - 1, y)) {
//                        if (isCellEmpty(x - 1, y)) {
//                            field[x - 1][y] = DOT_AI;
//                            flag = true;
//                        }
////                        else if (isCellEmpty(x + 3, y) && flag == false) {
////                            field[x + 3][y] = DOT_AI;
////                            flag = true;
////                            break;
////                        }
//                    }
//                }


            }
        }
        if (flag == false) {
            do {
                i = random.nextInt(fieldSizeX);
                j = random.nextInt(fieldSizeY);
            }
            while (!isCellEmpty(i, j));
            field[i][j] = DOT_AI;
        }
    }

//    private static void aiTurn() {
//        int x, y;
//        do {
//            x = random.nextInt(fieldSizeX);
//            y = random.nextInt(fieldSizeY);
//        }
//        while (!isCellEmpty(x, y));
//        field[x][y] = DOT_AI;
//    }

    static boolean checkWin(char c) {
        //Variant 2
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                // direct0
                if (isCellValid(x - 1, y + 1) && isCellValid(x - 2, y + 2) && isCellValid(x - 3, y + 3)) {
                    if (field[x][y] == c && field[x - 1][y + 1] == c && field[x - 2][y + 2] == c && field[x - 3][y + 3] == c)
                        return true;
                }
                // direct1
                if (isCellValid(x, y + 1) && isCellValid(x, y + 2) && isCellValid(x, y + 3)) {
                    if (field[x][y] == c && field[x][y + 1] == c && field[x][y + 2] == c && field[x][y + 3] == c)
                        return true;
                }
                // direct2
                if (isCellValid(x + 1, y + 1) && isCellValid(x + 2, y + 2) && isCellValid(x + 3, y + 3)) {
                    if (field[x][y] == c && field[x + 1][y + 1] == c && field[x + 2][y + 2] == c && field[x + 3][y + 3] == c)
                        return true;
                }
                // direct3
                if (isCellValid(x + 1, y) && isCellValid(x + 2, y) && isCellValid(x + 3, y)) {
                    if (field[x][y] == c && field[x + 1][y] == c && field[x + 2][y] == c && field[x + 3][y] == c)
                        return true;
                }

            }
        }

//Variant 1

//        for (int y = 0; y < fieldSizeY; y++) {
//            //check on horizontal
//            if (field[y][0] == c && field[y][1] == c && field[y][2] == c && field[y][3] == c) return true;
//            if (field[y][1] == c && field[y][1] == c && field[y][3] == c && field[y][4] == c)
//                return true;
//
//            //check on vertical
//
//            if (field[0][y] == c && field[1][y] == c && field[2][y] == c && field[3][y] == c) return true;
//            if (field[1][y] == c && field[2][y] == c && field[3][y] == c && field[4][y] == c)
//                return true;
//        }
//
//            //diagonal small check
//        int y = 3;
//                if (field[y][y - 3] == c && field[y - 1][y - 2] == c && field[y - 2][y - 1] == c && field[y - 3][y] == c)
//                    return true;
//                if (field[y + 1][y - 2] == c && field[y][y - 1] == c && field[y - 1][y] == c && field[y - 2][y + 1] == c)
//                    return true;
//
//                if (field[y - 2][y - 3] == c && field[y - 1][y - 2] == c && field[y][y - 1] == c && field[y + 1][y] == c)
//                    return true;
//                if (field[y - 3][y - 2] == c && field[y - 2][y - 1] == c && field[y - 1][y] == c && field[y][y + 1] == c)
//                    return true;
//
//                y = 4;
//
//            //diagonal big check
//                if (field[y][y - 4] == c && field[y - 1][y - 3] == c && field[y - 2][y - 2] == c && field[y - 3][y - 1] == c)
//                    return true;
//                if (field[y - 1][y - 3] == c && field[y - 2][y - 2] == c && field[y - 3][y - 1] == c && field[y - 4][y] == c)
//                    return true;
//
//                if (field[y - 4][y - 4] == c && field[y - 3][y - 3] == c && field[y - 2][y - 2] == c && field[y - 3][y - 3] == c)
//                    return true;
//                if (field[y - 3][y - 3] == c && field[y - 2][y - 2] == c && field[y - 1][y - 1] == c && field[y][y] == c)
//                    return true;

        //From seminar

//        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
//        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
//        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;
//
//        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
//        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;
//
//
//        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
//        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
//        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        return false;
    }

    static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    static boolean gameCheck(char c, String str) {
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья");
            return true;
        }
        return false;

    }
}