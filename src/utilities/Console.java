package utilities;

import exceptions.ScriptRecursionException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, который управляет вводом команд
 */
public class Console {

    private Scanner scanner;
    private CommandManager commandManager;
    private Creator creator;
    private List<String> scriptFileNames = new ArrayList<String>();
    public Console(Scanner scanner,CommandManager commandManager,Creator creator){
        this.scanner=scanner;
        this.commandManager=commandManager;
        this.creator=creator;
    }

    /**
     *Метод, который отвечает за ввод пользовательских команд
     */
    public void workMode(){
        String[] userCommand = {"", ""};
        int status=0;
        try{
        do{
            System.out.println("Введите желаемую команду");
            userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
            userCommand[1] = userCommand[1].trim();
            status=executeCommand(userCommand);
            System.out.println();

        }while(status!=2);
        }catch(NoSuchElementException e) {
        }
        }

    /**
     * Метод для считывания комманд из файла
     * @param fileName-файл, из которого считываются команды
     * @return Exit code
     */

    private int scriptMode(String fileName) {
        String[] userCommand = {"", ""};
        scriptFileNames.add(fileName);
        try (Scanner scanner2 = new Scanner(new File(fileName))) {
            if (!scanner2.hasNext()) throw new NoSuchElementException();
            Scanner oldScanner = creator.getScanner();
            int status=0;
            creator.setScanner(scanner2);
            do{
                userCommand = (scanner2.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                System.out.println("\u001B[30m"+"\u001B[33m"+" Выполняется команда "+userCommand[0]+"\u001B[33m"+"\u001B[30m");
                if (userCommand[0].equals("execute_script")){
                    for (String name: scriptFileNames){
                        if (name.equals(userCommand[1])) throw new ScriptRecursionException();
                    }

                }
                status=executeCommand(userCommand);
            }while(status==0 && scanner2.hasNextLine());
            creator.setScanner(oldScanner);


        }catch(FileNotFoundException e){
            System.err.println("Файл со скриптом не найден :(");
        }catch(NoSuchElementException e){
            System.err.println("Файл пуст...");
        }catch(ScriptRecursionException e){
            System.err.println("Скрипты не вызываются рекурсивно");}
        return 1;


    }

    /**
     * Метод определяет введеную команду и запускает ее
     * @param userCommand введеная команда для запуска
     * @return Exit code
     */
    private int executeCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "insert":
                if (!commandManager.insert(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_key":
                if (!commandManager.removeKey(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            case "replace_if_greater":
                if (!commandManager.replaceIfGreater(userCommand[1])) return 1;
                break;
            case "replace_if_lower":
                if (!commandManager.replaceIfLower(userCommand[1])) return 1;
                break;
            case "remove_lower_key":
                if (!commandManager.removeLowerKey(userCommand[1])) return 1;
                break;
            case "remove_all_by_number_of_rooms":
                if (!commandManager.removeAllByNumber(userCommand[1])) return 1;
                break;
            case "count_greater_than_furnish":
                if (!commandManager.countFurnish(userCommand[1])) return 1;
                break;
            case "filter_starts_with_name":
                if (!commandManager.filterName(userCommand[1])) return 1;
                break;
            default:
                System.out.println("Команда "+userCommand[1]+" не найдена. Введите help для справки.");
        }
        return 0;
    }

}
