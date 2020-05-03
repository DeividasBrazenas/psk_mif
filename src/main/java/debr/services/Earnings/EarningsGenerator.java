package debr.services.Earnings;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.Random;

@ApplicationScoped
@Default
@Production
public class EarningsGenerator implements IEarningsGenerator {
    public String generateEarnings() {
        System.out.println("Using default implementation of EarningsGenerator");

        Random rand = new Random();

        return Integer.toString(rand.nextInt() & Integer.MAX_VALUE);
    }
}
