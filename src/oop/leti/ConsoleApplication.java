package oop.leti;

import oop.leti.numbers.CustomInteger;
import oop.leti.numbers.CustomNumber;
import oop.leti.numbers.CustomRational;
import oop.leti.numbers.CustomType;
import oop.leti.polinom.NoRootsException;
import oop.leti.polinom.Polinom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleApplication {

    private Scanner scanner;
    private CustomType polinomType;
    private boolean exit = false;

    public ConsoleApplication() {
        this.scanner = new Scanner(System.in);
    }

    public void exec() {

        Polinom<CustomNumber> polinom = null;
        while (polinom == null) {
            chooseType();
            switch (polinomType) {
                case CUSTOM_INT -> {
                    System.out.println("Полином по умолчанию:");
                    polinom = new Polinom(new CustomInteger(1), new CustomInteger(0), new CustomInteger(0));
                    polinom.printEquation();
                }
                case CUSTOM_RATIONAL -> {
                    System.out.println("Полином по умолчанию:");
                    polinom = new Polinom(
                            new CustomRational(1, 1),
                            new CustomRational(0, 1),
                            new CustomRational(0, 1)
                    );
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
        System.out.println("2.Рациональный");
        int input = -1;
        while (input <= 0 || input > CustomType.values().length) {
            System.out.println("Введите номер от " + 1 + " до " + CustomType.values().length);
            input = getNumber();
        }
        polinomType = CustomType.values()[input - 1];
    }

    private List<Integer> getCoefficient() {
        List<Integer> list = new ArrayList<>();
        while (true) {
            switch (polinomType) {
                case CUSTOM_INT -> {
                    while (!scanner.hasNextInt()) {
                        System.out.println("\nНе число, введите число\n".toUpperCase());
                        scanner.next();
                    }
                    list.add(scanner.nextInt());
                    return list;
                }
                case CUSTOM_RATIONAL -> {
                    String reg = "^-?\\d{1,}/\\d{1,}";
                    Pattern pattern = Pattern.compile(reg);
                    String str = scanner.next();
                    Matcher matcher = pattern.matcher(str);
                    while (!matcher.matches()) {
                        System.out.println("Дробь не соответствует формату (пример: 5/3)");
                        System.out.println("Попытайтесь еще раз:");
                        str = scanner.next();
                        matcher = pattern.matcher(str);
                    }
                    list.add(Integer.valueOf(str.split("/")[0]));
                    list.add(Integer.valueOf(str.split("/")[1]));
                    return list;
                }
            }
        }
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

                    switch (polinomType) {
                        case CUSTOM_INT -> {
                            System.out.println("При х = 0: " + polinom.solveWithX(new CustomInteger(0)));
                            System.out.print("x = ");
                            int n = getCoefficient().get(0);
                            int d = getCoefficient().get(1);
                            System.out.println("При х = " + n + ": " + polinom.solveWithX(new CustomInteger(n)));
                        }
                        case CUSTOM_RATIONAL -> {
                            System.out.println("При х = 0: " + polinom.solveWithX(new CustomRational(0, 1)));
                            System.out.print("x = ");
                            List<Integer> list = getCoefficient();
                            int n = list.get(0);
                            int d = list.get(1);
                            System.out.println("При х = " + n + "/" + d + ": " + polinom.solveWithX(new CustomRational(n, d)));

                        }
                    }

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
        while (true) {
            switch (polinomType) {
                case CUSTOM_INT -> {
                    System.out.print("a = ");
                    int a = getCoefficient().get(0);
                    System.out.print("b = ");
                    int b = getCoefficient().get(0);
                    System.out.print("c = ");
                    int c = getCoefficient().get(0);
                    return new Polinom(new CustomInteger(a), new CustomInteger(b), new CustomInteger(c));
                }
                case CUSTOM_RATIONAL -> {
                    System.out.print("a = ");
                    List<Integer> a = getCoefficient();
                    System.out.print("b = ");
                    List<Integer> b = getCoefficient();
                    System.out.print("c = ");
                    List<Integer> c = getCoefficient();
                    return new Polinom(new CustomRational(a.get(0), a.get(1)),
                            new CustomRational(b.get(0), b.get(1)),
                            new CustomRational(c.get(0), c.get(1))
                    );
                }
            }
        }
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
