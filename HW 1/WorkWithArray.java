import java.util.Random;

/**
 * Created by Pol on 4/27/2016.
 */
public class WorkWithArray {
    private static int[] innerArray;
    private static Random generator = new Random();

    public static void setInnerArray(int[] innerArray) {
        WorkWithArray.innerArray = innerArray;
    }

    public static void main(String[] args) {
        initRandomArray(15);
        printArray();
        int key = innerArray[(innerArray.length-1) / 2];
        System.out.println("Set key = " + key);
        quickSort(0, innerArray.length-1);
        printArray();
        binarySearch(innerArray, 0, innerArray.length-1, key);
    }

    public static void initRandomArray(int length) {
        innerArray = new int[length];
        for (int i = 0; i < length; i++) {
            innerArray[i] = generator.nextInt(100);
        }
        System.out.println("Array Initiation");
    }

    public static void printArray() {
        int length = innerArray.length;
        for (int i = 0; i < length - 1; i++) {
            System.out.print(innerArray[i] + ", ");
        }
        System.out.println(innerArray[length - 1]);
    }

    public static void quickSort(int first, int last){
        int i = first;
        int j = last;
        if (i >= j)
            return;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (innerArray[i] <= innerArray[cur])) {
                i++;
            }
            while (j > cur && (innerArray[cur] <= innerArray[j])) {
                j--;
            }
            if (i < j) {
                int temp = innerArray[i];
                innerArray[i] = innerArray[j];
                innerArray[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        quickSort(first, cur);
        quickSort(cur+1, last);
    }

    public static void binarySearch(int[] array, int fist, int last, int key) {
        int position;
        position = (fist + last) / 2;

        while ((array[position] != key) && (fist <= last)) {
            if (array[position] > key) {
                last = position - 1;
            } else {
                fist = position + 1;
            }
            position = (fist + last) / 2;
        }

        if (fist <= last) {
            System.out.println("Key position = " + position);
        } else {
            System.out.println("Key not in this array.");
        }
    }

}
