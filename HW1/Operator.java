import java.util.ArrayList;
import java.util.Scanner;

public class Operator {
    ArrayList<Name> arrayList = new ArrayList();
    Scanner scanner = new Scanner(System.in);
    public void create(){
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        arrayList.add(new Name(name));
    }
    public void read(){
        System.out.print("[");
        for (Name x :
                arrayList) {
            System.out.print(x.toString()+", ");
        }
        System.out.print("]");
    }
    public void remake(){
        System.out.println("Введите изначальное имя для изменения");
        String name = scanner.nextLine();
        System.out.println("Введите новое имя для изменения");
        String newName = scanner.nextLine();
        for (int i = 0; i <arrayList.size(); i++){
            if(arrayList.get(i).name.equals(name)){
                arrayList.get(i).name = newName;
            }else{
                System.out.println("Такого имени в списках нет");
            }

        }
    }
    public void remove(){
        System.out.println("Введите имя для удаления");
        String name = scanner.nextLine();
        int x = 0;

        for (int i = 0; i <arrayList.size(); i++){
            if(arrayList.get(i).name.equals(name)){
                arrayList.remove(i);
            }else{
                System.out.println("Такого имени в списках нет");
            }
        }
    }


    public String calc(){
        int x = 1;
        int y=23;
        return "x+y";
    }

}