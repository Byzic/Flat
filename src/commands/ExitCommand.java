package commands;


import exceptions.IncorrectValueException;

/**
 * Класс для команды "exit". Проверяет аргумент и дальше ничего не делает
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand(){
        super("exit","завершить программу (без сохранения в файл)")  ;
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */
    @Override
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty())throw new IncorrectValueException();
        return true;
        }
        catch (
                IncorrectValueException e) {
            System.err.println("У этой команды нет параметров! Необходимо ввести: exit");
        }
        return false;
    }
}
