package commands;


import exceptions.EmptyArgumentException;

/**
 * Класс команды "execute_script"
 */
public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand(){
        super("execute_script file_name"," считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }
    /**
     * Выполнение команды
     * @param argument аргумент
     * @return состояние выполнения команды
     */
    @Override
    public boolean execute(String argument){
        try{
            if (argument.isEmpty()) throw new EmptyArgumentException();
            return true;
        }catch (
                EmptyArgumentException e) {
            System.err.println("У этой команды должен быть аргумент(имя файла для считывания скрипта)" );
        }
        return false;

    }
}
