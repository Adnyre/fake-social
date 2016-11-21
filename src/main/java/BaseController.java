/**
 * Created by adnyre on 13.11.16.
 */

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

import static spark.Spark.*;

public class BaseController {
    private static final Logger LOGGER = Logger.getLogger(BaseController.class);
    private static MessageService messageService = new MessageService();

    public static void main(String[] args) {
//        LOGGER.setLevel(Level.INFO);

        messageService.start();

        staticFiles.location("/public"); // Static files

        get("/hw", (req, resp) -> "Hello world");
        get("/hello", (req, resp) -> new ModelAndView(new HashMap<>(), "index"), new ThymeleafTemplateEngine());
        get("/message", "application/json", (req, resp) -> messageService.getMessages(), new JsonTransformer());
        get("/message/*", "application/json", (req, resp) -> messageService.getMessage(Integer.parseInt(req.splat()[0])), new JsonTransformer());
        get("/message_id", "application/json", (req, resp) -> messageService.getMessageIds(), new JsonTransformer());
    }
}
