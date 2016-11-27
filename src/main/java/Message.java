import java.time.LocalDateTime;

/**
 * Created by adnyre on 20.11.16.
 */
public class Message {
    private static int counter = 0;
    private int id;
    private LocalDateTime time;
    private User user;
    private String text;

    public Message(LocalDateTime time, User user, String text) {
        this.time = time;
        this.user = user;
        this.text = text;
        id = ++counter;
    }

    public Message(Message orig) {
        this(orig.getTime(), orig.getUser(), orig.getText());
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message mesage = (Message) o;

        if (id != mesage.id) return false;
        if (time != null ? !time.equals(mesage.time) : mesage.time != null) return false;
        if (user != null ? !user.equals(mesage.user) : mesage.user != null) return false;
        return text != null ? text.equals(mesage.text) : mesage.text == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", time=" + time +
                ", user=" + user +
                ", text='" + text + '\'' +
                '}';
    }
}
