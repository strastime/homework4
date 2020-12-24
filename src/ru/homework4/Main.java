package ru.homework4;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int SIZE = 5;
    private static final int NUMBER_CHips = 4;
    private static final char[][] map = new char[SIZE][SIZE];

    private static final char DOT_EMPTY = '·';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    public static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {
        intitiliazeGame();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (chechEndGame(DOT_X)) break;

            computerTurn();
            printMap();
            if (chechEndGame(DOT_O)) break;
        }
    }

    private static boolean chechEndGame(char symbol) {
        if (chechWin(symbol)){
            System.out.println(symbol == DOT_X ? "Человек победил!" : "Победил Компьютер");
            return true;
        }
        if (isMapFull()){
            System.out.println("Ничья");
            return true;
        }
        return false;
    }


    private static boolean isMapFull(){
        for (char[] row : map){
            for (char cellValue : row){
                if (cellValue == DOT_EMPTY){
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean chechWin(char symbol) {

        int countLine =0;
        int countVertical =0;
        for (int i = 0; i < map.length; i++) {
            if (map[i][0] == symbol && map[i][1] == symbol && map[i][2] ==symbol) return true;

            if (map[i][(map.length - 1) - i] == symbol) countLine++;

            if (map[(map.length - 1) - i][i] == symbol) countVertical++;

            if ((NUMBER_CHips == countLine) || (NUMBER_CHips == countVertical))    return true;

        }
        int countSlashRigth = 0;

        int countSlashLeft = 0;

        for (int h = 0; h < map.length;h++)
        {
               if (map[h][h] == symbol) countSlashRigth++;
             if (map[h][(map.length - 1) - h] == symbol) countSlashLeft++;
             if ((NUMBER_CHips == countSlashRigth) || (NUMBER_CHips == countSlashLeft))    return true;


        }

        return false;
    }

    private static void humanTurn() {
        int rowIndex = -1;
        int colIndex = -1;
        do {
            System.out.print("Введите номер строки: ");
            if (!SCANNER.hasNextInt()){
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }
            rowIndex = SCANNER.nextInt() - 1;

            System.out.print("Введите номер столбца: ");
            if (!SCANNER.hasNextInt()){
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }
            colIndex = SCANNER.nextInt() - 1;
        } while (!isCellValid(rowIndex,colIndex,DOT_X));

        map[rowIndex][colIndex] = DOT_X;
    }

    private static void computerTurn() {
        int rowIndex = -1;
        int colIndex = -1;
        Random rand = new Random();
        do {
             rowIndex = rand.nextInt(SIZE);
             colIndex = rand.nextInt(SIZE);
        } while (!isCellValid(rowIndex,colIndex, DOT_O));

        map[rowIndex][colIndex] = DOT_O;
    }

    private static boolean isCellValid(int rowIndex, int colIndex, char symbol) {
        if (!isArrayIndexValid(rowIndex) || !isArrayIndexValid(colIndex)) {
            if (symbol == DOT_X){
                System.out.println("Идексы строки и колонки должны быть в диапазоне от 0 до " + SIZE);
            }

            return false;
        }
        if (map[rowIndex][colIndex] != DOT_EMPTY){
            if (symbol == DOT_X){
                System.out.println("Данная ячейка уже занята.");
            }

            return false;
        }
        return true;

    }

    private static boolean isArrayIndexValid(int index) {
        return index >= 0 && index < SIZE;
    }

    private static void printMap() {
        printHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {

        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {

            System.out.print((rowIndex + 1) + " ");
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {

                System.out.print(map[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }
    }

    private static void printHeader() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void intitiliazeGame() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                map[rowIndex][colIndex] = DOT_EMPTY;
            }
        }
    }
}
