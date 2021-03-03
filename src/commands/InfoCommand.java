package commands;


import exceptions.IncorrectValueException;
import utilities.CollectionManager;

import java.time.LocalDateTime;

/**
 * Команда "info". Выводит информацию о коллекции
 */
public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public InfoCommand(CollectionManager cm){
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager=cm;
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */


    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())throw new IncorrectValueException();
            System.out.println("Информация о коллекции");
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();
            System.out.println(" Тип: " + collectionManager.collectionType());
            System.out.println(" Дата последней инициализации: " + lastInitTimeString);
            System.out.println(" Количество элементов: " + collectionManager.collectionSize());


        }
        catch (
                IncorrectValueException e) {
            System.err.println("У этой команды нет параметров! Необходимо ввести: info" );
        }
        return false;

    }
}
