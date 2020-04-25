package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CommandCreateDatabase implements DatabaseCommand {
    private static final int ARGS_NUM = 1;
    private final ExecutionEnvironment environment;
    private final String databaseName;

    public CommandCreateDatabase(ExecutionEnvironment env, String... args) {
        environment = env;
        Parser parser = new Parser();
        parser.setArgs(ARGS_NUM, args);
        databaseName = parser.getArg(Parser.argNames.databaseName);
    }

    @Override
    public DatabaseCommandResult execute() {
        environment.addDatabase(null);
        return DatabaseCommandResult.success("Database created");
    }
}
