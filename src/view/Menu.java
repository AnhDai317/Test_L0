package view;

import java.util.Scanner;

public class Menu {
    public final static Scanner scanner = new Scanner(System.in);
    public void showMenu(){
        System.out.println("1. Create new student \n" +
                "2. Update student \n" +
                "3. Search student by id \n" +
                "4. View list student \n" +
                "5. Exit"
        );
    }
    public void inputStudent(){

    }
}
