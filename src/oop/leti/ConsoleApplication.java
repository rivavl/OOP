package oop.leti;

import java.util.Scanner;

public class ConsoleApplication {

    private Scanner scanner;
    private CustomType polinomType;
    private boolean exit = false;

    public ConsoleApplication() {
        this.scanner = new Scanner(System.in);
    }

    public void exec() {

        Polinom polinom = null;
        while (polinom == null) {
            chooseType();
            switch (polinomType) {
                case CUSTOM_INT -> {
                    System.out.println("Полином по умолчанию:");
                    polinom = new Polinom(new CustomInteger(1), new CustomInteger(0), new CustomInteger(0));
                    polinom.printEquation();
                }
            }
        }
        while (!exit) {
            polinom = menu(polinom);
        }
    }

    private void chooseType() {
        System.out.println("Выберите тип полинома");
        System.out.println("1.Целочисленный");
        int input = -1;
        while (input <= 0 || input > CustomType.values().length) {
            System.out.println("Введите номер от " + 1 + " до " + CustomType.values().length);
            input = getNumber();
        }
        polinomType = CustomType.values()[input - 1];
    }

    private int getNumber() {
        while (!scanner.hasNextInt()) {
            System.out.println("\nНе число, введите число\n".toUpperCase());
            scanner.next();
        }
        return scanner.nextInt();
    }

    private Polinom menu(Polinom polinom) {
        printOptionsMenu();
        int numOfInstruction = 0;

        numOfInstruction = getNumber();

        switch (numOfInstruction) {
            case 1:
                polinom = createPolinom();
                break;
            case 2:
                Polinom.Roots roots;
                try {
                    roots = polinom.getRoots();
                    System.out.println("x1 = " + roots.getX1());
                    System.out.println("x2 = " + roots.getX2());
                } catch (NoRootsException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                try {
                    System.out.println("При х = 0: " + polinom.solveWithX(new CustomInteger(0)));
                    System.out.print("x = ");
                    int x = getNumber();
                    System.out.println("При х = " + x + ": " + polinom.solveWithX(new CustomInteger(x)));
                } catch (RuntimeException e) {
                    System.out.println("\nСначала введите коэффициенты полинома\n".toUpperCase());
                }
                break;
            case 4:
                polinom.printEquation();
                break;
            case 5:
                polinom.printCanonical();
                break;
            case 6:
                exit = true;
                scanner.close();
                break;
            default:
                System.out.println("\nНедопустимый номер инструкции\n".toUpperCase());
                break;
        }
        return polinom;
    }

    private Polinom createPolinom() {
        System.out.print("a = ");
        int a = getNumber();
        System.out.print("b = ");
        int b = getNumber();
        System.out.print("c = ");
        int c = getNumber();
        return new Polinom(new CustomInteger(a), new CustomInteger(b), new CustomInteger(c));
    }

    private void printOptionsMenu() {
        System.out.println("1.Ввести коэффициенты a, b, c");
        System.out.println("2.Рассчитать корни и вывести в консоль");
        System.out.println("3.Ввecти х и рассчитать значения полинома");
        System.out.println("4.Текстовое представление полинома в форме a*x^2 + b*x + c");
        System.out.println("5.Вывод тестового представления полинома в канонической форме a(x - x1)(x - x2)");
        System.out.println("6.Выход");
    }
}
