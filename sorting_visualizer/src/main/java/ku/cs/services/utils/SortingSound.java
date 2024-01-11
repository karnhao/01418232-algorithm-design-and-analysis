package ku.cs.services.utils;

public class SortingSound {
    private static final int MIN_HZ = 523;
    private static final int MAX_HZ = 1047;

    private int min_value;
    private int max_value;

    public SortingSound(int max_value) {
        this(0, max_value);
    }

    public SortingSound(int min_value, int max_value) {
        this.max_value = max_value;
        this.min_value = min_value;
    }

    public void setMaxValue(int max_value) {
        this.max_value = max_value;
    }

    public void setMinValue(int min_value) {
        this.min_value = min_value;
    }

    public int getHz(int x) {
        return (int)(MIN_HZ + ((float)(MAX_HZ - MIN_HZ) / (max_value - min_value) * (x - min_value)));
    }
}
