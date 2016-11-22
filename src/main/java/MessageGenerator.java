import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by adnyre on 20.11.16.
 */
public class MessageGenerator {
    private static final Random RANDOM = new Random();
    private static final String[] TEXT_POOL = {
            "Hello!",
            "Bye!",
            "LOL!",
            "whatever",
            "couldn't care less",
            "WOW!",
            "OMG!",
            "Here: www.example.com",
            "Have a look at http://www.example.com",
            "http://en.wikipedia.org - look here"
    };
    private static final List<User> USERS =
            Collections.unmodifiableList(Arrays.asList(User.values()));

    public Message getMessage() {
        return new Message(LocalDateTime.now(), randomUser(), randomText());
    }

    private User randomUser() {
        return USERS.get(RANDOM.nextInt(USERS.size()));
    }

    private String randomText() {
        return TEXT_POOL[RANDOM.nextInt(TEXT_POOL.length)];
    }
}
