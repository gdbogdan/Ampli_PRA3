package Observer_Observable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Simple extends Task {


    public Simple(BigDecimal cost) {
        super(cost);
    }


    public void changeCost(BigDecimal newCost) {
        newCost = newCost.setScale(2, RoundingMode.HALF_UP);
        if (newCost.signum() <= 0)
            throw new IllegalArgumentException("Cost must be positive");


        if (!newCost.equals(this.cost)) {
            BigDecimal old = this.cost;
            CostChanged change = new CostChanged(old, newCost);
            this.cost = newCost;
            notifyCostChanged(change);
        }
    }
}

