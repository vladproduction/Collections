package com.vladproduction.app10.iterator_listerator.iterator.fail_fast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fail_Fast_example_remove_in_for_each {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        Iterator<String> stringIterator = list.iterator();
        while (stringIterator.hasNext()){
            String value = stringIterator.next();
            System.out.println(value);
        }
        System.out.println("-------------------------------");

        for (String item: list) {
            System.out.println(item);
            if("c".equals(item)){
                list.remove("c");
            }
        }
    }
}

/*
* Iterator - это обьект, который позволяет проходится по каждому элементу коллекции.
*   Содержит методы: boolean hasNext(); возвращает тру если есть в коллекции следующий элемент;
*                    <example String> next(); уже идет к следующему
*                    remove(); возвращает Обьект <> ; удаляет последний элемент который нам вернул
*                               метод некст; для того чтобы удалить-сначала нужно получить элемент
*                               с помощью метода некст, вызов ремув без вызова некст, даст рантайм*/
