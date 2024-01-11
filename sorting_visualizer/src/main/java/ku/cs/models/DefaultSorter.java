package ku.cs.models;

import javax.swing.event.ChangeListener;
import java.util.LinkedList;
import java.util.List;

public abstract class DefaultSorter extends Thread implements Sorter {
    protected int[] array;
    protected int delay;
    protected List<ChangeListener> listeners;

    public DefaultSorter(int[] array, int delay) {
        this.array = array;
        this.delay = delay;
        listeners = new LinkedList<>();
    }

    @Override
    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void addChangeListener(ChangeListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeChangeListener(ChangeListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void clearChangeListener() {
        this.listeners.clear();
    }

    public List<ChangeListener> getChangeListener() {
        return listeners;
    }
}
