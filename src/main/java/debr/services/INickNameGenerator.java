package debr.services;

import java.util.concurrent.Future;

public interface INickNameGenerator {
    Future<String> generateNickName();
}
