package command;

import utility.ObjectForServer;

/**
 * Абстрактный класс команды
 */

public abstract class CommandAbstract {

    /** Переменная команды name - означающая имя */
    String name;
    /** Переменная команды description - означающая описание */
    String description;
    /** Свойство показывающее нужен ли команде аргумент */
    final boolean isArgument;

    public CommandAbstract(boolean isArgument) {
        this.isArgument = isArgument;
    }

    public void execute(ObjectForServer arg) {
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
