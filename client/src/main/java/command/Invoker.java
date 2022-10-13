package command;

import request.RRHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Класс Invoker, принимающий на вход команду,
 * затем передающий её в Receiver
 */

public class Invoker {
    public Map<String, CommandAbstract> commands;

    private Receiver receiver;
    public HashSet<String> files = new HashSet<>();
    private final RRHandler rrHandler;

    public Invoker(Receiver receiver, RRHandler rrHandler) {
        commands = new HashMap<>();
        this.receiver = receiver;
        this.rrHandler = rrHandler;
        initCommands();

    }

    public void initCommands() {
        commands.put("add", new AddCommand(false, rrHandler));
        commands.put("help", new HelpCommand(commands, false,  rrHandler));
        commands.put("show", new ShowCommand(false, rrHandler));
        commands.put("info", new InfoCommand(false, rrHandler));
        commands.put("clear", new ClearCommand(false, rrHandler));
        commands.put("exit", new ExitCommand(false, rrHandler));
        commands.put("add_if_max", new AddIfMaxCommand(false, rrHandler));
        commands.put("add_if_min", new AddIfMinCommand(false, rrHandler));
        commands.put("average_of_oscars_count", new AverageOfOscarsCommand(false, rrHandler));
        commands.put("sum_of_oscars_count", new SumOfOscarsCommand(false, rrHandler));
        commands.put("remove_by_id", new RemoveByIdCommand(true, rrHandler));
        commands.put("remove_lower", new RemoveLowerCommand(true, rrHandler));
        commands.put("update_id", new UpdateIdCommand(true, rrHandler));
        commands.put("count_greater_than_genre", new CountGreaterGenreCommand(true, rrHandler));
        commands.put("execute_script", new ExecuteScriptCommand((HashMap<String, CommandAbstract>) commands, this, files, true, rrHandler));
    }

    public void execute(String command) throws IOException {
        String[] parts = command.split(" ");
        if (commands.containsKey(parts[0])) {
            if (parts.length == 2) {
                if (commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(parts[0]), parts[1]);
                } else {
                    System.out.println("Команда не требует аргумента, смотреть help");
                    throw new RuntimeException();
                }
            } else if (parts.length == 1){
                if (!commands.get(parts[0]).isArgument) {
                    receiver.execute(commands.get(parts[0]), "");
                } else {
                    System.out.println("Команде требуется аргумент, смотреть help");
                    throw new RuntimeException();
                }
            } else {
                System.out.println("Команда точно не требует столько аргументов.");
                throw new RuntimeException();
            }
        } else {
            System.out.println("Такой команды нет");
            throw new RuntimeException();
        }
    }
}

