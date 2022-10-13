package command;

import utility.ObjectForServer;

/**
 * Класс Receiver запускающий команды
 */

public class Receiver {

    /** Конструктор ресивера */
    public Receiver() {
    }

    public void execute(CommandAbstract command, ObjectForServer arg) {
        command.execute(arg);
    }
}
