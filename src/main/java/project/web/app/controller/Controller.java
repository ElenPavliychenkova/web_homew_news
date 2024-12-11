package project.web.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.web.app.controller.concrete.Command;
import project.web.app.controller.concrete.CommandProvider;

import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doRequest(request, response);
    }


    private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userCommand = request.getParameter("command");
        System.out.println("fetched command " + userCommand);

        Command command = provider.takeCommand(userCommand);
        command.execute(request, response);
    }
}