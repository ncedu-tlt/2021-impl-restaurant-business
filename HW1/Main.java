import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        Operator operator = new Operator();
        while (start){
            System.out.println();
            System.out.println("Что вы хотите сделать?");
            System.out.println("Создать / Читать / Обновлять / Удалять / Выход");
            String choose = scanner.nextLine();
            switch(choose){
                case "Создать":
                    operator.create();
                    break;
                case "Читать":
                    operator.read();
                    break;
                case "Обновлять":
                    operator.remake();
                    break;
                case "Удалять":
                    operator.remove();
                    break;
                case "Выход":
                    start = false;
                    break;
                default:
                    System.err.println("Введи из предложенных");
                    break;
            }

        }

    }
}
