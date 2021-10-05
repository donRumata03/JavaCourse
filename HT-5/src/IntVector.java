import java.util.Arrays;

public class IntVector {
    private int mSize;
    private int[] mArray;

    public IntVector() {
        mSize = 0;
        mArray = new int[1];
    }

    public int size() {
        return mSize;
    }

    public boolean isEmpty() {
        return size() == 0;
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
