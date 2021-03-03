package commands;

import exceptions.EmptyArgumentException;
import data.Flat;
import utilities.CollectionManager;
import utilities.Creator;

import java.time.LocalDateTime;

/**
 * Команда "insert". Добавляет новый элемент в коллекцию
 */
public class InsertCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private Creator creator;
    public InsertCommand(CollectionManager collectionManager, Creator creator){
        super("insert null {element}","добавить новый элемент с заданным ключом");
        this.collectionManager=collectionManager;
        this.creator=creator;
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */

    public boolean execute(String argument){
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            int key =Integer.parseInt(argument);//доделать проверку на наличие уже такого ключа в коллекции
            collectionManager.Key(key);
            collectionManager.insertNew(key, new Flat(collectionManager.newId(), creator.newName(), creator.newCoordinates(),  LocalDateTime.now(), creator.newArea(),creator.newNumberOfRooms(),creator.newFurnish(),creator.newView(),creator.newTransport(),creator.newHouse()));
            System.out.println("\u001B[30m"+"\u001B[33m"+"Элемент с заданным ключом успешно добавлен"+"\u001B[33m"+"\u001B[30m");
            return true;}
        catch (EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(Ключ для добавления нового значения)" );
        }catch (NumberFormatException e){
            System.err.println("Формат введенного аргумента неверен. Он должен быть целым.");
        }catch(NullPointerException e){
            System.err.println("Элемент с таким ключом уже существует");
        }
        return false;
    }
}
