package app.controller.concrete;

import java.util.HashMap;
import java.util.Map;

import app.controller.concrete.impl.DoAuth;
import app.controller.concrete.impl.DoRegistration;
import app.controller.concrete.impl.GoToIndexPage;
import app.controller.concrete.impl.GoToMainPage;
import app.controller.concrete.impl.GoToRegistrationPage;
import app.logic.UserRepository;

public class CommandProvider {
	
	private Map<CommandName, Command> commands = new HashMap<>();
	
	private UserRepository userRepository = new UserRepository();
	
	public CommandProvider() {
		
		commands.put(CommandName.DO_AUTH, new DoAuth(userRepository));
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration(userRepository));
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage());
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
		} catch(IllegalArgumentException | NullPointerException e) {
			System.out.println("No such command " + commandEnum);
			return commands.get(CommandName.GO_TO_INDEX_PAGE);
		}
	}

}
