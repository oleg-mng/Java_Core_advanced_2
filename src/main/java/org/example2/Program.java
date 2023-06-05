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

    }
    private static void initialize(){
        fieldSizeX = 3;
        fieldSizeY = 3;

        field = new char[fieldSizeX][fieldSizeY];
        for(int y =0; y < fieldSizeY; y++){
            for(int x = 0; x< fieldSizeX; x++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }
}