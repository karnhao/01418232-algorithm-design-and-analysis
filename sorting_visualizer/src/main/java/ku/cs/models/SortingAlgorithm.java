package ku.cs.models;

import ku.cs.services.utils.Beep;
import ku.cs.services.utils.SortingSound;

import javax.swing.event.ChangeEvent;

public abstract class SortingAlgorithm extends DefaultSorter {
    protected int beep_mSec;
    protected float volume;

    protected SortingSound sortingSound;

    private SelectedIndex[] selectedIndices;

    protected int minValue;
    protected int maxValue;

    public SortingAlgorithm() {
        super(null, 8);
    }

    public void initialize(int[] array, int delay, int beep_mSec, float volume) {
        this.array = array;
        this.delay = delay;
        this.beep_mSec = beep_mSec;
        this.volume = volume;
        this.selectedIndices = null;
        minValue = Integer.MAX_VALUE;
        maxValue = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > maxValue) maxValue = i;
            if (i < minValue) minValue = i;
        }
        sortingSound = new SortingSound(minValue, maxValue);
    }

    public void initialize(int[] array, int delay) {
        this.initialize(array, delay, delay, 0.1f);
    }

    @Override
    final public SelectedIndex[] getSelectedIndices() {
        return selectedIndices;
    }

    final protected void setSelectedIndices(SelectedIndex[] selectedIndices) {
        this.selectedIndices = selectedIndices;
    }

    final protected void onChange() {
        listeners.forEach(t->t.stateChanged(new ChangeEvent(this)));
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            sort();
        } catch (Exception e) {
            System.out.println("Exception in sorting algorithm : " + e.getMessage());
        }
    }

    final protected void beepValue(int value) {
        Beep.tone(sortingSound.getHz(value), beep_mSec, volume);
    }

    final protected void beep(int index) {
        this.beepValue(array[index]);
    }

    final protected void setBeep_mSec(int beep_mSec) {
        this.beep_mSec = beep_mSec;
    }

    final protected void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
