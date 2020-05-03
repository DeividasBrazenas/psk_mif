package debr.services.Earnings;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Specializes;
import java.util.Random;

@ApplicationScoped
@Default
@Alternative
@Specializes
public class MockEarningsGenerator extends EarningsGenerator {
    public String generateEarnings() {
        System.out.println("Using mock implementation of EarningsGenerator");

        Random rand = new Random();

        return "Mock " + (rand.nextInt() & Integer.MAX_VALUE);
    }
}
