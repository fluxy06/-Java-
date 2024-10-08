import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
// 9, 18

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        ForPrakik task18 = new ForPrakik();
        int[] masNums = new int[10];
        for (int i = 0; i < masNums.length; i++) {
            masNums[i] = random.nextInt(2001) - 1000;
        }
        task18.prMass(masNums);
        /*
        forPrakik task9 = new forPrakik();

        int[] massOfNums = new int[20];
        for (int i = 0; i < massOfNums.length; i++) {
            massOfNums[i] = random.nextInt(2001) - 1000;
        }
        task9.prMass(massOfNums);
        System.out.println("Количество положительных чисел: ");
        System.out.println(task9.countPositive(massOfNums));

}}

class forPrakik {
    public void prMass(int[] mass) {
        for (int i = 0; i < mass.length; i++) {
            System.out.println("Элемент"+ i + ": " + mass[i]);
        }
    }
    public int countPositive(int[] mass) {
        int counter = 0;
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] >= 0) {
                counter++;
            }
        }
        return counter;
    }
    */

    }
}

class ForPrakik {
    public void prMass(int[] mass) {
        for (int i = 0; i < mass.length; i++) {
            System.out.println("Элемент" + i + ": " + mass[i]);
        }
    }

    public void sortMass(int[] mass) {
        Arrays.sort(mass);
        for (int i = 0; i < mass.length; i++) {
            System.out.println("Элемент" + i + ": " + mass[i]);
        }
    }


    }
