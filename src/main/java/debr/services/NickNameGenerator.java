package debr.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Future;

@ApplicationScoped
public class NickNameGenerator implements Serializable, INickNameGenerator {
    @Futureable
    public Future<String> generateNickName() {
        System.out.println("Using default implementation of NickNameGenerator");

        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        UUID uuid = UUID.randomUUID();
        final String generatedNickname = uuid.toString();
        return new AsyncResult<>(generatedNickname);
    }
}
