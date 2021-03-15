package commands;

import exceptions.IncorrectValueException;
import utilities.CollectionManager;

/**
 * команда "clear". Удаляет все элементы коллекции
 */
public class ClearCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager){
        super("clear","очистить коллекцию");
        this.collectionManager=collectionManager;
    }

    /**
     * Выполнение команды
     * @param argument аргумент
     * @return Статус выполнения программы
     */
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty())throw new IncorrectValueException();
        collectionManager.clear();
        System.out.println("\u001B[37m"+"\u001B[33m"+"Коллекция удалена"+"\u001B[33m"+"\u001B[37m");
        return true;
    }
         catch (
                 IncorrectValueException e) {
        System.err.println("У этой команды нет параметров! Необходимо ввести: clear");
    }
        return false;
    }
}
