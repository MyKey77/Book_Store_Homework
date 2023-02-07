package org.progmatic;

import org.progmatic.Controls.Controller;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    private Controller controller;

    private void mainMenu(Scanner sc) throws ParseException {
        String option = "ghyt";
        do {
            switch (option) {
                case "a" -> {
                    controller.Add_auto_data();
                }
                case "km" -> {
                    System.out.println("Add meg a person azonositot: ");
                    long pid = sc.nextInt();
                    sc.nextLine();

                    System.out.println("isbn változás?");
                    String s = sc.nextLine();

                    System.out.println("Cím változás? ");
                    String c = sc.nextLine();

                    controller.Modify_Book(pid, s, c);
                }
                case  "bt"->{

                    System.out.println("which tittle you looking for?");
                    String name=sc.nextLine();
                    controller.Search_Book_Title(name);
                }

                case "nsz"->{
                    System.out.println("Add meg egy új szerző nevet: ");
                    String name = sc.nextLine();
                    controller.Add_Author(name);
                }
                case "msz"->{
                    System.out.println("Add meg a person azonositot: ");
                    long pid = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Milyen nevet? ");
                    String c = sc.nextLine();
                    controller.Modify_Author(pid,c);
                }
                case "dsz"->{
                    System.out.println("Add meg a person azonositot: ");
                    long pid = sc.nextInt();
                    sc.nextLine();
                    controller.Delete_Author(pid);
                }

                case "ns"->{
                    System.out.println("Aláírott??: ");
                    boolean b = sc.nextBoolean();
                    sc.nextLine();

                    System.out.println("Új store neve: ");
                    String name=sc.nextLine();
                    controller.Add_Store(b,name);
                }
                case "ms"->{
                    System.out.println("Add meg a Store azonositot: ");
                    long pid = sc.nextInt();
                    sc.nextLine();


                    System.out.println("true vagy false?");
                    boolean v=sc.nextBoolean();
                    sc.nextLine();

                    System.out.println("Company name?");
                    String n=sc.nextLine();


                    controller.Modify_Store(pid,v,n);
                }
            }
            printmenu();
            System.out.println("Válasz opciót");
        } while (!"q".equalsIgnoreCase(option = sc.nextLine()));
    }

    private void printmenu() {
        System.out.println("=".repeat(30));

        System.out.println("\tAddj automatikus_adatot - (a)");
        System.out.println("\tKönyv módísitása - (km)");
        System.out.println("\tKönyv cím keresések - (bt)");
        System.out.println();

        System.out.println("\tÚj szerző - (nsz)");
        System.out.println("\tModósít szerző - (msz)");
        System.out.println("\ttörlés szerző - (dsz)");
        System.out.println();

        System.out.println("\tNew Store - (ns)");
        System.out.println("\tModify Store - (ms)");
        System.out.println();

        System.out.println("\tQuit - (q)");
        System.out.println("=".repeat(30));
    }

    public static void main(String[] args) throws Exception {
        Main m = new Main();

        try (
                Scanner sc = new Scanner(System.in);
                Controller c = new Controller();
        ) {
            m.controller = c;
            m.mainMenu(sc);
        }

    }
}