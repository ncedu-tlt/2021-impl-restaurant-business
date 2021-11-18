
import java.util.*;
/*
Konuhov Andrew Mihailovich
CRUD Interface
 */

class DispMenu {
    ArrayList<String> employees = new ArrayList<>();
    public void disp() {
        System.out.println("Выберите пункт для редактирования списка или выхода из меню:\n1.Create\n2.Read\n3.Update\n4.Delete\n5.Exit");
        Scanner mainScanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        Scanner scanner4 = new Scanner(System.in);
        int s = mainScanner.nextInt();
        switch (s) {
            case 1:
                System.out.print("Введите имя работника: ");
                String a = scanner1.nextLine();
                employees.add(a);
                disp();
            case 2:
                System.out.println(employees);
                disp();
            case 3:
                System.out.print("Введите номер сотрудника, которого необходимо заменить: ");
                int j = scanner2.nextInt();
                System.out.print("Введите имя нового сотрудника: ");
                String name = scanner3.nextLine();
                employees.set(j - 1, name);
                disp();
            case 4:
                System.out.print("Введите номер сотрудника, которого необходимо удалить: ");
                int i = scanner4.nextInt();
                employees.remove(i-1);
                disp();
            case 5:
                break;
        }
    }
}

public class RestaurantBusinessModel {
    public static void main(String[] args) {
        DispMenu menuInterface = new DispMenu();
        menuInterface.disp();
    }
}

