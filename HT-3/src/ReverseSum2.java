import java.util.*;

import java.lang.Math;


class FuckingJavaResizableIntArrayWithoutOnMetadataFuckingJavaWhyAreThereNoPrimitiveTypeTemplates {
    // not extends List<Integer>

    private int mSize;
    private int[] mArray;

    FuckingJavaResizableIntArrayWithoutOnMetadataFuckingJavaWhyAreThereNoPrimitiveTypeTemplates() {
        mSize = 0;
        mArray = new int[1];
    }

    public int size() {
        return mSize;
    }

    private void reallocate(int newCapacity) {
        mArray = Arrays.copyOf(mArray, newCapacity);
    }

    public void add(int element) {
        if (mSize + 1 == mArray.length) {
            reallocate(Math.max(mArray.length * 2, 4));
        }
        mArray[mSize++] = element;
    }
    public int get(int index) {
        return mArray[index];
    }
    public void set(int index, int element) {
        mArray[index] = element;
    }
}


public class ReverseSum2 {
    public static void main(String[] args) {
        FuckingJavaResizableIntArrayWithoutOnMetadataFuckingJavaWhyAreThereNoPrimitiveTypeTemplates
                prefix2dSum =
                new FuckingJavaResizableIntArrayWithoutOnMetadataFuckingJavaWhyAreThereNoPrimitiveTypeTemplates();

        Scanner inputReader = new Scanner(System.in);
        while(inputReader.hasNextLine()) {
            Scanner lineParser = new Scanner(inputReader.nextLine());

            int columnIndex = 0;
            int matrixSum = 0;
            while (lineParser.hasNextInt()) {
                int thisElement = lineParser.nextInt();

                if (columnIndex < prefix2dSum.size()) {
                    prefix2dSum.set(columnIndex, prefix2dSum.get(columnIndex) + thisElement);
                } else {
                    prefix2dSum.add(thisElement);
                }
                matrixSum += prefix2dSum.get(columnIndex);

                if (columnIndex != 0) {
                    System.out.print(" ");
                }
                System.out.print(matrixSum);

                columnIndex++;
            }
            System.out.println();
        }
    }
}
