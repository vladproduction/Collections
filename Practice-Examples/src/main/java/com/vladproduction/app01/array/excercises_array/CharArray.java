package com.vladproduction.app01.array.excercises_array;

public class CharArray {
    public static void main(String[] args) {

        // Размер массива (количество строк) - случайное число от 2 до 4:
        int size = 2 + (int)(Math.random()*3);
        // Переменная для записи количества элементов строке массива:
        int n;
        // Начальная буква для заполнения массива:
        char s = 'a';
        // Текст для вывода сообщения:
        String text = "\"Рваный\" символьный массив:";
        // Создание массива - задано количество строк:
        char[][] symbolsTable=new char[size][];
        // Добавление строк в массив
        for(int k = 0; k<size; k++){
            // Случайное число от 1 до 8:
            n=1+(int)(Math.random()*8);
            // Создание строки случайной длины:
            symbolsTable[k] = new char[n];}
        // Заполнение массива буквами:
        for(int i=0; i<symbolsTable.length; i++){
            // Переход на новую строку и символ вертикальной черты:
            text+="\n| ";
            // Переменная j для разных значений i имеет разные ограничения:
            for(int j=0; j<symbolsTable[i].length; j++){
                // Заполнение элемента символом:
                symbolsTable[i][j]=s;
                // Следующий символ:
                s++;
                // Добавление записи в текст:
                text+=symbolsTable[i][j]+" | ";}
        }
        // Вывод сообщения на экран:
        System.out.println(text);

    }
}
