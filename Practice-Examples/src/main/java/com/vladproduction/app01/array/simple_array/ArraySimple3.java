package com.vladproduction.app01.array.simple_array;

public class ArraySimple3 {
    public static void main(String[] args) {
//        // Создание массивов myArrayInt1 и myArrayInt2 типа int и объявление переменных
//        int[] myArrayInt1;
//        int myArrayInt2[];
//
//        // Определение массива или выделение памяти
//        // Массив myArrayInt3 из 10 элементов с начальным значением 0 для каждого элемента
//        int[] myArrayInt3;
//        myArrayInt3 = new int[10];
//        // Массив myArrayInt4 из 20 элементов с начальным значением 0 для каждого элемента
//        int[] myArrayInt4 = new int[20];
//
//        //Инициализация массива
//        // Массив myArrayInt5 из 5 элементов со значениями элементов 1, 3, 8, 10, 4
//        int[] myArrayInt5 = {1, 3, 8, 10, 4};
//        // Массив myArrayInt6 из 10 элементов с начальным значением 0 для каждого элемента
//        int[] myArrayInt6 = new int[10];
//        // Присвоение второму элементу myArrayInt6 значения 14
//        myArrayInt6[2] = 14;
//        // Присвоение пятому элементу myArrayInt6 значения 8
//        myArrayInt6[5] = 8;
//
//        // Массив myArray1 из 5 элементов типа double.
//        double[] myArray1 = {2.4, 3.8, 11.2, 9.8, 1.18};
//        // Вывод длины массива myArray1 на экран.
//        System.out.println("Количество элементов в массиве myArray1: " + myArray1.length);
//
//        // Массив myArray2 из 4 элементов типа String.
//        String[] myArray2 = {"Java", "массив", "пример", "proglang.su"};
//        // Вывод размера массива myArray2 на экран.
//        System.out.println("Количество элементов в массиве myArray2: " + myArray2.length);
//
//        System.out.println("-------------------------------");
//        // Нахождение максимального элемента в массиве myArray из 4 элементов типа double.
//        double[] myArray = {11, 5.8, 11.1, 10.9};
//
//        // Переменной max задаем минимальное double-значение.
//        double max1 = Double.MIN_VALUE;
//        System.out.println("max " + max1);
//
//        // Перебираем все элементы массива.
//        for (int i = 0; i < myArray.length; i++) {
//            // Переменной max с помощью метода Math.max() присваиваем максимальное значение
//            // путем выбора наибольшего из двух значений ("старого" значения max и значения элемента).
//            max1 = Math.max(max1, myArray[i]);
//        }
//
//        // Выводим на экран наибольшее число массива myArray.
//        System.out.println("Максимальный элемент в массиве myArray: " + max1);
//
//        // Нахождение максимального значения в массиве myArray из 4 элементов типа double.
//        double[] myArray3 = {11, 5.8, 11.1, 10.9};
//
//        // Переменной max задаем значение нулевого элемента.
//        double max = myArray3[0];
//
//        // Перебираем все элементы массива.
//        for (int i = 1; i < myArray3.length; i++) {
//            // Сравниваем значение переменной max со значениями элемента массива.
//            // Если значение элемента массива больше значения переменной max,
//            // то новое значение переменной max будет равно значению этого элемента.
//            if (myArray3[i] > max) {
//                max = myArray3[i];
//            }
//        }
//
//        // Выводим на экран наибольший элемент массива myArray.
//        System.out.println("Максимальное значение в массиве myArray3: " + max);

//        System.out.println("--------------------------------------------");
//        // Нахождение минимального элемента в массиве myArray из 4 элементов типа double.
//        double[] myArray = {11, 5.8, 11.1, 10.9};
//
//        // Переменной min задаем максимальное double-значение.
//        double min = Double.MAX_VALUE;
//
//        // Перебираем все элементы массива.
//        for (int i = 0; i < myArray.length; i++) {
//            // Переменной min с помощью метода Math.min() присваиваем минимальное значение
//            // путем выбора наименьшего из двух значений ("старого" значения min и значения элемента).
//            min = Math.min(min, myArray[i]);
//        }
//
//        // Выводим на экран наименьшее число массива myArray.
//        System.out.println("Минимальный элемент в массиве myArray: " + min);
//
//        System.out.println("---------------------------------------------------");
//        // Нахождение минимального значения в массиве myArray из 4 элементов типа double.
//        double[] myArray = {11, 5.8, 11.1, 10.9};
//
//        // Переменной min задаем значение нулевого элемента.
//        double min = myArray[0];
//
//        // Перебираем все элементы массива.
//        for (int i = 1; i < myArray.length; i++) {
//            // Сравниваем значение переменной min со значениями элемента массива.
//            // Если значение элемента массива меньше значения переменной min,
//            // то новое значение переменной min будет равно значению этого элемента.
//            if (myArray[i] < min) {
//                min = myArray[i];
//            }
//        }
//
//        // Выводим на экран наименьший элемент массива myArray.
//        System.out.println("Минимальное значение в массиве myArray: " + min);

//        // Нахождение суммы элементов в массиве myArray из 5 элементов типа double.
//        double[] myList = {2.5, 1.8, 1.3, 6.5, 22.8};
//        // Инициализируем переменную total.
//        double total = 0;
//
//        // Прибавляем к переменной total каждый элемент массива.
//        for (int i = 0; i < myList.length; i++) {
//            total += myList[i];
//        }
//
//        // Вывод результата на экран.
//        System.out.println("Сумма элементов массива myList: " + total);

//        // Получение суммы элементов в массиве myArray из 5 элементов типа double.
//        double[] myList = {2.5, 1.8, 1.3, 6.5, 22.8};
//        // Инициализируем переменную total.
//        double total = 0;
//
//        // Прибавляем к переменной total каждый элемент массива с помощью улучшенного цикла for.
//        for(double element : myList) {
//            total += element;
//        }
//
//        // Вывод результата на экран.
//        System.out.println("Сумма массива myList: " + total);

//        // Вывод на экран массива myArray из 5 элементов типа double.
//        double[] myList = {11.5, 1.9, 5.32, 8.8, 15.8};
//
//        // Вывод массива на экран с помощью улучшенного цикла for.
//        System.out.print("Вывод элементов массива myList: ");
//        for(double element : myList) {
//            System.out.print(element + ", ");
//        }
//
//        // Вывод элементов массива на экран.
//        System.out.print("\nВывод элементов массива myList: ");
//        for (int i = 0; i < myList.length; i++) {
//            System.out.print(myList[i] + ", ");
//        }

//        // Массива myArray из 5 элементов типа double.
//        double[] myList = {2, 2.7, 3, 12, 23.8};
//
//        // Вывод четных элементов массива на экран.
//        System.out.print("Четные элементы массива myList: ");
//        for (int i = 0; i < myList.length; i++) {
//            if(myList[i]%2 == 0){
//                System.out.print(myList[i] + ", ");
//            }
//        }
//
//        // Вывод нечетных элементов массива на экран.
//        System.out.print("\nНечетные элементы массива myList: ");
//        for (int i = 0; i < myList.length; i++) {
//            if(myList[i]%2 != 0){
//                System.out.print(myList[i] + ", ");
//            }
//        }

//        // Массив myArray из 5 элементов типа double.
//        double[] myList = {11.5, 1.9, 5.32, 8.8, 15.8};
//
//        // Вывод элементов массива с четным индексом на экран.
//        System.out.print("Элементы массива myList с четным индексом: ");
//        for (int i = 0; i < myList.length; i++) {
//            if(i%2 == 0){
//                System.out.print("индекс " + i + " = " + myList[i] + ", ");
//            }
//        }
//
//        // Вывод элементов массива с нечетным индексом на экран.
//        System.out.print("\nЭлементы массива myList с нечетным индексом: ");
//        for (int i = 0; i < myList.length; i++) {
//            if(i%2 != 0){
//                System.out.print("индекс " + i + " = " + myList[i] + ", ");
//            }
//        }

        int[][] nums = new int[][]
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };
        for (int i = 0; i < nums.length; i++){
            for(int j=0; j < nums[i].length; j++){

                System.out.printf("%d ", nums[i][j]);
            }
            System.out.println();
        }
    }
}
