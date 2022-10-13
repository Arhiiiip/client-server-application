package command;

import request.RRHandler;

import java.io.IOException;
import java.io.Serializable;

/**
 * Абстрактный класс команды
 */

public abstract class CommandAbstract implements Serializable {

    /** Переменная команды name - означающая имя */
    final String name;
    /** Переменная команды description - означающая описание */
    final String description;
    /** Свойство показывающее нужен ли команде аргумент */
    final boolean isArgument;
    public String arg;
    RRHandler rrHandler;
    String login;

    public CommandAbstract(String name, String description, boolean isArgument, RRHandler rrHandler) {
        this.name = name;
        this.description = description;
        this.isArgument = isArgument;
        this.rrHandler = rrHandler;
        this.login = login;
    }

    public void execute(String arg) {
        try {
            rrHandler.req(this.getName(), arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        if (isArgument){
            return name + " - " + description + ". Так же команда требует аргумент";
        }else {
            return name + " - " + description;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArgument() {
        return isArgument;
    }

    public String getArg() {
        return arg;
    }

    public boolean executeR(String arg) {
        return false;
    }
}
