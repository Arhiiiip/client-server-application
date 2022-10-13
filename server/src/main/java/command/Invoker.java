package command;

import utility.MovieFactory;
import utility.ObjectForServer;
import utility.RRHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Класс Invoker, принимающий на вход команду,
 * затем передающий её в Receiver
 */

public class Invoker implements Runnable {
    public Map<String, CommandAbstract> commands;

    private Receiver receiver;
    public HashSet<String> files = new HashSet<>();
    RRHandler rrHandler;
    ObjectForServer command;

    public Invoker(Receiver receiver, MovieFactory movieFactory, ObjectForServer command, RRHandler rrHandler) {
        commands = new HashMap<>();
        this.receiver = receiver;
        initCommands(movieFactory);
        this.command = command;
        this.rrHandler = rrHandler;
    }

    public void setRrHandler(RRHandler rrHandler) {
        this.rrHandler = rrHandler;
    }

    public void initCommands(MovieFactory movieFactory) {
        commands.put("add", new AddCommand(movieFactory, false, rrHandler));
        commands.put("help", new HelpCommand(movieFactory, commands, false, rrHandler));
        commands.put("show", new ShowCommand(movieFactory, false, rrHandler));
        commands.put("info", new InfoCommand(movieFactory, false, rrHandler));
        commands.put("save", new SaveCommand(movieFactory, false, rrHandler));
        commands.put("clear", new ClearCommand(movieFactory, false, rrHandler));
        commands.put("exit", new ExitCommand(movieFactory, false, rrHandler));
        commands.put("add_if_max", new AddIfMaxCommand(movieFactory, false, rrHandler));
        commands.put("add_if_min", new AddIfMinCommand(movieFactory, false, rrHandler));
        commands.put("average_of_oscars_count", new AverageOfOscarsCommand(movieFactory, false, rrHandler));
        commands.put("sum_of_oscars_count", new SumOfOscarsCommand(movieFactory, false, rrHandler));
        commands.put("remove_by_id", new RemoveByIdCommand(movieFactory, true, rrHandler));
        commands.put("remove_lower", new RemoveLowerCommand(movieFactory, true, rrHandler));
        commands.put("update_id", new UpdateIdCommand(movieFactory, true, rrHandler));
        commands.put("count_greater_than_genre", new CountGreaterGenreCommand(movieFactory, true, rrHandler));
//        commands.put("execute_script", new ExecuteScriptCommand("execute_script link_to_file", "Cчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме", movieFactory, (HashMap<String, CommandAbstract>) commands, this, files, true));
    }

    public void execute(ObjectForServer command) {
        String com = command.getCommand();
        receiver.execute(commands.get(com), command);
    }

    @Override
    public void run() {
        String com = command.getCommand();
        receiver.execute(commands.get(com), command);
    }
}

