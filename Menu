package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> island = new ArrayList<>();
        island.add("Салат Цезарь");
        island.add("Борщ");
        island.add("Свинина на кости");
        island.add("Картофель фри");
        island.add("Мохито");
        Scanner sc = new Scanner(System.in);
        System.out.println("Приветствую! Сейчас в меню " + island.size() + " блюд(а)");
        System.out.println("Выбери действие и введи соответствующий номер:");
        System.out.println("1 - Изучить меню");
        System.out.println("2 - Удалить блюдо");
        System.out.println("3 - Добавить блюдо");
        System.out.println("4 - Редактирование меню");
        int x = sc.nextInt();
        while (true) {
            if (x == 1) {
                for (String i : island) {
                    System.out.println("-" + i + "-");
                }
            }
            if (x == 2) {
                System.out.println("Какое блюдо хочешь удалить (введи номер)?");
                for (String i : island) {
                    System.out.println("-" + i + "-");
                }
                int n = sc.nextInt();
                island.remove(n - 1);
                System.out.println("Теперь меню выглядит так:");
                for (String i : island) {
                    System.out.println("-" + i + "-");
                }
            }
            if (x == 3) {
                System.out.println("Какое блюдо хочешь добавить (новое блюдо добавиться в конец меню)?");
                String l = sc.next();
                island.add(island.size(), l);
                System.out.println("Теперь меню выглядит так:");
                for (String i : island) {
                    System.out.println("-" + i + "-");
                }
            }
            if (x==4){
                System.out.println("Какое блюдо хочешь изменить (введи номер)?");
                for (String i : island) {
                    System.out.println("-" + i + "-");
                }
                int z = sc.nextInt();
                System.out.println("Окей, на что хочешь изменить?");
                String q = sc.next();
                island.set(z-1,q);
                System.out.println("Теперь меню выглядит так:");
                for (String i : island) {
                    System.out.println("-" + i + "-");
                }
            }
            if (x<=0 || x>4){
                System.out.println("Ошибка! Введи номер действия (1,2,3 или 4)");
            }
            System.out.println("Сейчас в меню " + island.size() + " блюд(а)");
            System.out.println("Выбери действие и введи соответствующий номер:");
            System.out.println("1 - Изучить меню");
            System.out.println("2 - Удалить блюдо");
            System.out.println("3 - Добавить блюдо");
            System.out.println("4 - Редактирование меню");
            x = sc.nextInt();
        }
    }
}
