package Observer_Observable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Observable;

public class Task extends Observable {
    protected BigDecimal cost;
    protected Task(BigDecimal cost) {
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0)
            throw new IllegalArgumentException("cost must be positive");
    }
    public final BigDecimal costInEuros() {
        return this.cost;
    }


    protected void notifyCostChanged(CostChanged change) {
        setChanged(); // Marca el objeto Observable como cambiado
        notifyObservers(change); // Notifica a los observadores pasando el nuevo coste
    }
}

