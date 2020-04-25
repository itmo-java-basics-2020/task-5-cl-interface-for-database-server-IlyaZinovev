package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.logic.Database;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    public DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText.isEmpty()) {
            return DatabaseCommandResult.error("Nothing to execute");
        }
        String[] args = commandText.split(" ");
        DatabaseCommands commands;
        try {
            commands = DatabaseCommands.valueOf(args[0]);
        } catch (IllegalArgumentException ex) {
            return DatabaseCommandResult.error("No such command as " + args[0]);
        }
        DatabaseCommand command;
        try {
            command = commands.getCommand(env, Arrays.copyOfRange(args, 1, args.length));
        } catch (IllegalArgumentException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }
        return command.execute();
    }
}
