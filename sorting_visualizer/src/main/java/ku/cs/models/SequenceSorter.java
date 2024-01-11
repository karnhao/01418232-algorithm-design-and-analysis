package ku.cs.models;

import java.util.LinkedList;
import java.util.List;

public class SequenceSorter extends DefaultSorter {
    private final List<SortingAlgorithm> sorters;

    private SortingAlgorithm currentAlgorithm;

    public SequenceSorter(int[] array, int delay) {
        super(array, delay);
        this.sorters = new LinkedList<>();
    }

    public List<SortingAlgorithm> getSorters() {
        return sorters;
    }

    public void addAlgorithm(SortingAlgorithm algorithm) {
        this.sorters.add(algorithm);
    }

    @Override
    public void sort() {
        this.start();
    }

    @Override
    public SelectedIndex[] getSelectedIndices() {
        if (this.currentAlgorithm == null) return null;
        return this.currentAlgorithm.getSelectedIndices();
    }

    @Override
    public void run() {
        super.run();

        try {

            sorters.forEach((algorithm -> {
                if (Thread.interrupted()) throw new RuntimeException("Interrupted.");
                this.currentAlgorithm = algorithm;
                algorithm.initialize(this.array, this.delay);

                algorithm.clearChangeListener();
                this.listeners.forEach(algorithm::addChangeListener);

                algorithm.start();
                try {
                    algorithm.join();
                } catch (InterruptedException e) {
                    try {
                        algorithm.interrupt();
                    } catch (Exception ignored) {
                    }
                    throw new RuntimeException(e);
                }
            }));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public SortingAlgorithm getCurrentAlgorithm() {
        return this.currentAlgorithm;
    }
}
