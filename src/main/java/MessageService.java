import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by adnyre on 20.11.16.
 */
public class MessageService {
    private volatile List<Message> messages = new ArrayList<>();
    private static final Random RANDOM = new Random();
    private MessageGenerator generator = new MessageGenerator();

    public List<Message> getMessages() {
        return messages;
    }

    public Message getMessage(int id) {
        Optional<Message> message = messages.stream().filter(x -> x.getId() == id).findFirst();
        return message.isPresent() ? message.get() : null;
    }

    public List<Integer> getMessageIds() {
        List<Integer> ids = messages.stream().map(Message::getId).collect(Collectors.toList());
        return ids;
    }

    public void addMessage(Message message) {
        messages.add(new Message(message));
    }

    public void start() {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(() -> {
            while (true) {
                messages.add(generator.getMessage());
                try {
                    TimeUnit.SECONDS.sleep(RANDOM.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        exec.shutdown();
    }
}
