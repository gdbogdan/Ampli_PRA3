package Observer_Observable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Composite extends Task implements Observer {
    private final List<Task> subtasks;


    public Composite(List<Task> subtasks) {
        super(calculateInitialCost(subtasks));
        this.subtasks = new ArrayList<>(subtasks);

        for (Task t : this.subtasks) {
            t.addObserver(this);
        }
        recalculateCost();

    }

    // Calcula el coste inicial del composite antes de llamar al constructor
    private static BigDecimal calculateInitialCost(List<Task> subtasks) {
        BigDecimal total = BigDecimal.ZERO;
        for (Task task : subtasks) {
            total = total.add(task.costInEuros());
        }
        if (total.signum() <= 0) {
            throw new IllegalArgumentException("Initial composite cost must be positive");
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    private void recalculateCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (Task t : subtasks) {
            total = total.add(t.costInEuros());
        }
        this.cost = total.setScale(2, RoundingMode.HALF_UP);
    }

    public void update(Observable o, Object arg) {
        CostChanged change = (CostChanged) arg;
        if (!change.oldCost().equals(change.newCost())) {
            recalculateCost();
        }
    }

    //Esto permite que una tarea Composite tenga subtareas Composite dentro
    @Override
    public void addObserver(Observer obs) {
        super.addObserver(obs);
        for (Task t : subtasks) {
            t.addObserver(obs);
        }
    }
}

