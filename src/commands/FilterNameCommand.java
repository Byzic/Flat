package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Команда "filter_starts_with_name". Выводит элементы с именем, начинающимся на подстроку
 */
public class FilterNameCommand extends AbstractCommand {
    CollectionManager collectionManager;
    public FilterNameCommand(CollectionManager collectionManager){
        super("filter_starts_with_name name","вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionManager=collectionManager;
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new EmptyArgumentException();
            collectionManager.filterName(argument);
            return true;
        } catch (
                EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(строка, с которой будет начинаться name)");
            System.out.println();
        }
        return false;
    }
}
