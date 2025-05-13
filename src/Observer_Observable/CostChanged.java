package Observer_Observable;

import java.math.BigDecimal;

public record CostChanged(BigDecimal oldCost, BigDecimal newCost) {}
