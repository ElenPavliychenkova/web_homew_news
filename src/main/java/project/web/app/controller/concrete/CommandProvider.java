package project.web.app.controller.concrete;

import project.web.app.dao.connections.ConnectionPool;
import project.web.app.dao.connections.ConnectionPoolException;
import project.web.app.controller.concrete.impl.*;
import project.web.app.dao.news.NewsDaoImpl;
import project.web.app.dao.user.UserDaoImpl;
import project.web.app.service.news.NewsService;
import project.web.app.service.news.NewsServiceImpl;
import project.web.app.service.user.UserService;
import project.web.app.service.user.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

    private ConnectionPool connectionPool;
    private final UserService userService;
    private final NewsService newsService;

    public CommandProvider() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
        try {
            connectionPool = ConnectionPool.create(
                    "jdbc:mysql://localhost:3306/news_meincoon?allowPublicKeyRetrieval=true&useSSL=false",
                    "root",
                    "admin",
                    10
            );
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        userService = new UserServiceImpl(new UserDaoImpl(connectionPool));
        newsService = new NewsServiceImpl(new NewsDaoImpl(connectionPool));

        commands.put(CommandName.DO_AUTH, new DoAuth(userService));
        commands.put(CommandName.DO_REGISTRATION, new DoRegistration(userService));
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage(newsService));
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.DELETE_NEWS, new DeleteNews(newsService));
        commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
        commands.put(CommandName.ADD_NEWS, new AddNews(newsService));
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