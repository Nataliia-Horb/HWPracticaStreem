package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Employee(faker.random().nextInt(100), faker.name().firstName(), faker.random().nextInt(700, 8000)));
        }
        System.out.println(list);
        System.out.println("-----------------------------");

        /**
         * найти сущность с самым коротким именем
         */
        //1 вариант выводим какую-то одну сущность у которой длина имени минимальна
        System.out.println(list.stream().min(Comparator.comparing(e -> e.getName().length())).get());
        System.out.println("-----------------------------");

        // 2 вариант выводим всех сущностей с минимальными по длине именами
        int minLength = list.stream().map(e -> e.getName().length()).min(Integer::compareTo).get();
        System.out.println(list.stream().filter(e -> e.getName().length() == minLength).collect(Collectors.toList()));
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println("-----------------------------");

        /**
         *    Даны две коллекции стрингов
         *  Объединить их и добавить «!» к элементам первой
         *  Получить массив строк без дубликатов в верхнем регистре
         *  Преобразовать в Map, где первый символ ключ, а второй значение
         *  Преобразовать в Мапу сгруппировав по последнему символу строки
         *  Преобразовать в Мапу сгруппировав по первому символу строки и обьеденив вторые символу через « : »
         */

        List<String> list1 = Arrays.asList(
                "My family", "Mother", "Father", "baby", "Brother", "sister", "Brother", "All the people in my family");
        List<String> list2 = Arrays.asList(
                "Good night", "mother",
                "Good night", "father",
                "Good night", "sister",
                "Good night", "brother",
                "Good night", "everyone.");

        // Объединить их и добавить «!» к элементам первой
        List<String> mergedList = list1.stream().map(e -> e.concat("!")).collect(Collectors.toList());
        mergedList.addAll(list2);
        System.out.println(mergedList);
        System.out.println("-----------------------------");

        // Получить массив строк без дубликатов в верхнем регистре
        String[] mergedArr = mergedList.stream().map(String::toUpperCase).distinct().toArray(String[]::new);
        System.out.println(Arrays.toString(mergedArr));
        System.out.println("-----------------------------");

        // Преобразовать в Map, где первый символ ключ, а второй значение

        // 1вариант
        Map<String, String> map = Arrays.stream(mergedArr).collect(Collectors.toMap(key -> key.substring(0, 1),
                val -> val.substring(1, 2), (v1, v2) -> v2));
        System.out.println(map);
        // 2вариант
        Map<String, List<String>> map1 = Arrays.stream(mergedArr)
                .collect(Collectors.groupingBy((String key) -> key.substring(0, 1), Collectors.
                        mapping((String value) -> value.substring(1, 2), Collectors.toList())));
        System.out.println(map1);
        System.out.println("-----------------------------");

        // Преобразовать в Мапу сгруппировав по последнему символу строки
        Map<String, List<String>> map2 = Arrays.stream(mergedArr)
                .collect(Collectors.groupingBy((String key) -> key.substring(key.length() - 1)));
        System.out.println(map2);
        System.out.println("-----------------------------");

        // Преобразовать в Мапу сгруппировав по первому символу строки и обьеденив вторые символу через « : »
        Map<String, String> map3 = Arrays.stream(mergedArr)
                .collect(Collectors.groupingBy((String key) -> key.substring(0, 1),
                        Collectors.mapping((String value) -> value.substring(1, 2).concat(":"), Collectors.joining())));
        System.out.println(map3);
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println("-----------------------------");


        /**
         *  Даны две коллекции интов, сложить числа первой и второй
         */

        List<Integer> listInt1 = Arrays.asList(3, 35, 18, 45, 7, 22, 4, -1, 1);
        List<Integer> listInt2 = Arrays.asList(4, 25, 1, 1, 12, 0, 33, 4);
        Integer res = Stream.of(listInt1, listInt2).flatMap(m -> m.stream()).reduce(Integer::sum).get();
        System.out.println(res);
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println("-----------------------------");

        /**
         * Написать метод поиска чисел Фибоначчи стримами
         */
        System.out.println(searchFibNumber(10));
    }

    private static int searchFibNumber(int i) {
        int[] previousValues = new int[]{0, 1};
        List<Integer> listRes = Stream.iterate(previousValues, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(i).map(arr -> arr[0]).collect(Collectors.toList());
        System.out.println(listRes);
        return listRes.get(listRes.size() - 1);
    }
}