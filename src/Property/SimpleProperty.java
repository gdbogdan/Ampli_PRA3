package Property;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleProperty extends TaskProperty {

    public SimpleProperty(BigDecimal cost) {
        super(cost);
    }

    public void changeCost(BigDecimal newCost) {
        newCost = newCost.setScale(2, RoundingMode.HALF_UP);
        if (newCost.signum() <= 0){
            throw new IllegalArgumentException("Cost must be positive");
        }
        if (!newCost.equals(this.cost)) {
            BigDecimal old = this.cost;
            this.cost = newCost;
            changes.firePropertyChange("cost", old, newCost);
        }
    }
}

