package command;

/**
 * Класс Receiver запускающий команды
 */

public class Receiver {

    /** Конструктор ресивера */
    public Receiver() {
    }

    public void execute(CommandAbstract command, String arg) {
        command.execute(arg);
    }
}
