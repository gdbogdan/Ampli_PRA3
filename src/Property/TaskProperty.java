package Property;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class TaskProperty{
    protected PropertyChangeSupport changes;
    protected BigDecimal cost;


    protected TaskProperty(BigDecimal cost) {
        this.changes = new PropertyChangeSupport(this);
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0)
            throw new IllegalArgumentException("cost must be positive");
    }


    public final BigDecimal costInEuros(){
        return this.cost;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener){
        changes.addPropertyChangeListener(listener);
    }
}

