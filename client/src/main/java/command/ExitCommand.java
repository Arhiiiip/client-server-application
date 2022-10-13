package command;

import request.RRHandler;

import java.io.IOException;

public class ExitCommand extends CommandAbstract {


    RRHandler rrHandler;

    public ExitCommand(boolean isArgument, RRHandler rrHandler) {
        super("exit", "Завершить программу (без сохранения в файл)", isArgument,rrHandler);
    }

    @Override
    public void execute(String arg) {
        try {
            rrHandler.req("save", arg);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
