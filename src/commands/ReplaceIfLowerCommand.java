package commands;

import exceptions.EmptyArgumentException;
import data.Flat;
import utilities.CollectionManager;
import utilities.Creator;

import java.time.LocalDateTime;

public class ReplaceIfLowerCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Creator creator;
    public ReplaceIfLowerCommand(CollectionManager collectionManager, Creator creator){
        super("replace_if_lower null {element}","заменить значение по ключу, если новое значение меньше старого");
        this.collectionManager=collectionManager;
        this.creator=creator;
    }
    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            Integer key=Integer.parseInt(argument);
            collectionManager.checkKey(key);
            collectionManager.replaceIfLower(key);
            return true;
        }catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(ключ для удаления элементов)" );
        }catch (NumberFormatException e){
            System.err.println("Формат введенного аргумента неверен . Он должен быть целым.");
        }catch (NullPointerException e){
            System.err.println("Элемента с таким ключом не существует");
        }
        return false;
    }
}
