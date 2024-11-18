package app.controller.concrete;

import app.controller.concrete.impl.*;
import app.service.news.NewsService;
import app.service.news.NewsServiceImpl;
import app.service.user.UserService;
import app.service.user.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<>();

    private final UserService userDao = new UserServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();

    public CommandProvider() {

        commands.put(CommandName.DO_AUTH, new DoAuth(userDao));
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration(userDao));
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage(newsService));
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }

    public Command takeCommand(String commandEnum) {

        if (commandEnum == null) {
            return commands.get(CommandName.NO_SUCH_COMMAND);
        }

        try {
            CommandName commandName = CommandName.valueOf(commandEnum.toUpperCase());
            return commands.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("No such command " + commandEnum);
            return commands.get(CommandName.GO_TO_INDEX_PAGE);
        }
    }

}
