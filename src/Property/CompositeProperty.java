package Property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CompositeProperty extends TaskProperty implements PropertyChangeListener {
    private final List<TaskProperty> subtasks;


    public CompositeProperty(List<TaskProperty> subtasks) {
        super(calculateInitialCost(subtasks));

        this.subtasks = new ArrayList<>(subtasks);

        for (TaskProperty t : subtasks) {
            t.addPropertyChangeListener(this);
        }

        recalculateCost();
    }

    // Calcula el coste inicial del composite antes de llamar al constructor
    private static BigDecimal calculateInitialCost(List<TaskProperty> subtasks) {
        BigDecimal total = BigDecimal.ZERO;
        for (TaskProperty task : subtasks) {
            total = total.add(task.costInEuros());
        }
        if (total.signum() <= 0) {
            throw new IllegalArgumentException("Initial composite cost must be positive");
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }


    private void recalculateCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (TaskProperty t : subtasks) {
            total = total.add(t.costInEuros());
        }
        this.cost = total.setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("cost")){
            var old_cost = cost;
            //Update the local cost
            recalculateCost();
            //Notify father observer the cost of the updated task
            changes.firePropertyChange("cost", old_cost, this.cost);
        }
    }
}

