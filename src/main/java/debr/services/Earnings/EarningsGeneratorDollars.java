package debr.services.Earnings;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@Production
@Decorator
public class EarningsGeneratorDollars implements IEarningsGenerator {
    @Inject
    @Delegate
    @Production
    IEarningsGenerator earningsGenerator;

    public String generateEarnings() {
        System.out.println("Using decorated implementation of EarningsGenerator");

        return earningsGenerator.generateEarnings() + " $";
    }
}
