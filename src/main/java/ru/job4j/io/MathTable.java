package ru.job4j.io;

import java.io.FileOutputStream;

public class MathTable {
    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                array[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int size = 9;
        int[][] multipleTable = multiple(size);
        try (FileOutputStream out = new FileOutputStream("Math table.txt")) {
            for (int col = 0; col < size; col++) {
                for (int row = 0; row < size; row++) {
                    out.write(String.valueOf(multipleTable[col][row]).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
