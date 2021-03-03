package commands;


import exceptions.IncorrectValueException;

/**
 * Command 'help'.
 */
public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }
    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
         if (!argument.isEmpty())throw new IncorrectValueException();
            return true;
        }
         catch (IncorrectValueException e) {
            System.err.println("У этой команды нет параметров! Необходимо ввести: help");
        }
        return false;
    }
}

