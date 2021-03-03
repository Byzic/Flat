import commands.*;
import utilities.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Главный класс, в котором создаются все экземпляры и запускается программа
 * @author Byzova Valeriia
 */
public class Main {
    public static void main(String[] args) {
        FileManager fileManager;
        CollectionManager collectionManager;
        final String myenv="FLAT_FILE";
        Scanner scanner = new Scanner(System.in);
        fileManager=new FileManager(myenv);
        collectionManager = new CollectionManager(fileManager);
        collectionManager.loadCollection();
        Creator creator=new Creator(scanner);
        CommandManager commandManager = new CommandManager(new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new InsertCommand(collectionManager, creator),
                new UpdateCommand(collectionManager, creator),
                new RemoveKeyCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ClearCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new ReplaceIfGreaterCommand(collectionManager, creator),
                new ReplaceIfLowerCommand(collectionManager, creator),
                new RemoveLowerKeyCommand(collectionManager),
                new RemoveAllByNumberOfRoomsCommand(collectionManager),
                new CountGreaterThanFurnishCommand(collectionManager),
                new FilterNameCommand(collectionManager));
        Console console = new Console(scanner,commandManager,creator);
        console.workMode();
        }

}
