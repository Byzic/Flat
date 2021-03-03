package commands;

import exceptions.EmptyArgumentException;
import utilities.CollectionManager;

/**
 * Команда "remove_lower_key". Удаляет эл-ты с меньшим ключом
 */
public class RemoveLowerKeyCommand extends AbstractCommand {
    CollectionManager collectionManager;
    public RemoveLowerKeyCommand(CollectionManager collectionManager){
        super("remove_lower_key null","удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        this.collectionManager=collectionManager;
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */
    public boolean execute(String argument){
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            Integer key=Integer.parseInt(argument);
            int i=collectionManager.removeLowerKey(key);
            System.out.println("\u001B[30m"+"\u001B[33m"+"Было удалено "+i+" квартир с ключом меньше "+key+"\u001B[33m"+"\u001B[30m");
            return true;
        }catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(ключ для удаления элементов)" );
        }catch (NumberFormatException e){
            System.err.println("Формат введенного аргумента неверен. Он должен быть целым.");
        }
        return false;
    }
}
