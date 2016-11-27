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
            "Bye! (kiss)",
            "LOL! https://upload.wikimedia.org/wikipedia/commons/8/81/Comet_67P_on_19_September_2014_NavCam_mosaic.jpg",
            "whatever",
            "couldn't care less",
            "(wow)WOW!(wow)",
            "OMG! https://upload.wikimedia.org/wikipedia/commons/8/81/Comet_67P_on_19_September_2014_NavCam_mosaic.jpg",
            "Here: www.example.com (blushing)",
            "Have a look at http://www.example.com (cwl)",
            "http://en.wikipedia.org - look here",
            "behold: https://upload.wikimedia.org/wikipedia/commons/8/81/Comet_67P_on_19_September_2014_NavCam_mosaic.jpg"
    };
    private static final List<User> USERS =
            Collections.unmodifiableList(Arrays.asList(User.values()));

    public Message getMessage() {
        return new Message(LocalDateTime.now(), randomUser(), randomText());
    }

    private User randomUser() {
        return USERS.get(RANDOM.nextInt(USERS.size() - 1) + 1);
    }

    private String randomText() {
        return TEXT_POOL[RANDOM.nextInt(TEXT_POOL.length)];
    }
}
