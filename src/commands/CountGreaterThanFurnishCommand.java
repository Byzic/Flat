package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Класс для команды "count_greater_than_furnish"
 */
public class CountGreaterThanFurnishCommand extends AbstractCommand {
    CollectionManager collectionManager;
    public CountGreaterThanFurnishCommand(CollectionManager collectionManager){
        super("count_greater_than_furnish furnish","вывести количество элементов, значение поля furnish которых больше заданного");
        this.collectionManager=collectionManager;
    }

    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */
    @Override
    public boolean execute(String argument) {
        int count=0;
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            count=collectionManager.countFurnish(argument);
            System.out.println(count+"\u001B[30m"+"\u001B[33m"+"-столько элементов имеет значение furnish болше заданного"+"\u001B[33m"+"\u001B[30m");
            return true;
        }catch (EmptyArgumentException e){
            System.err.println("У этой команды должен быть аргумент(значение furnish)");
        }catch (IllegalArgumentException e){
            System.err.println("Такого значения нет в furnish");
        }
        return false;
    }
}
