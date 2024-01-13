package ku.cs.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class SelectedIndexCollection {
    List<SelectedIndex> selectedIndexes;


    public SelectedIndexCollection() {
        selectedIndexes = new LinkedList<SelectedIndex>();
    }

    public SelectedIndexCollection(List<SelectedIndex> selectedIndexes) {
        this.selectedIndexes = selectedIndexes;
    }

    public SelectedIndexCollection(SelectedIndex... selectedIndexes) {
        if (selectedIndexes == null) this.selectedIndexes = new LinkedList<SelectedIndex>();
        else this.selectedIndexes = new LinkedList<>(Arrays.asList(selectedIndexes));
    }

    public List<SelectedIndex> getList() {
        return this.selectedIndexes;
    }
}
