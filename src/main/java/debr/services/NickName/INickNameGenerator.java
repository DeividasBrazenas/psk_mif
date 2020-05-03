package debr.services.NickName;

import java.util.concurrent.Future;

public interface INickNameGenerator {
    Future<String> generateNickName();
}
