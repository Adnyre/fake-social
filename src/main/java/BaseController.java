/**
 * Created by adnyre on 13.11.16.
 */

import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

import static spark.Spark.*;

public class BaseController {
    private static final Logger LOGGER = Logger.getLogger(BaseController.class);
    private static final Gson GSON = new Gson();
    private static MessageService messageService = new MessageService();

    public static void main(String[] args) {
//        LOGGER.setLevel(Level.INFO);

        messageService.start();

        staticFiles.location("/public"); // Static files

        port(8080);
        get("/hw", (req, resp) -> "Hello world");
        get("/hello", (req, resp) -> new ModelAndView(new HashMap<>(), "index"), new ThymeleafTemplateEngine());
        get("/message", "application/json", (req, resp) -> messageService.getMessages(), new JsonTransformer());
        get("/message/*", "application/json", (req, resp) -> messageService.getMessage(Integer.parseInt(req.splat()[0])), new JsonTransformer());
        post("/message", (req, resp) -> {
            String body = req.body();
            Message message = null;
            try {
                message = GSON.fromJson(body, Message.class);
            } catch (Exception e ) {
                LOGGER.error("ERROR in post: ", e);
            }
//            resp.type("application/json");
            messageService.addMessage(message);
            return "";
        });
        get("/message_id", "application/json", (req, resp) -> messageService.getMessageIds(), new JsonTransformer());
    }
}
