package ku.cs.models;

import javax.swing.event.ChangeListener;
import java.util.List;

public interface Sorter {
    void addChangeListener(ChangeListener listener);
    void removeChangeListener(ChangeListener listener);
    void clearChangeListener();

    List<ChangeListener> getChangeListener();
    int getDelay();
    int[] getArray();
    void sort();
    List<SelectedIndex> getSelectedIndices();
}
