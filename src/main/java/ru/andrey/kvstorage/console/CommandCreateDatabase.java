package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CommandCreateDatabase implements DatabaseCommand {
    private static final int ARGS_NUM = 1;
    private static final Parser PARSER = new Parser();
    private final ExecutionEnvironment environment;
    private final String databaseName;

    public CommandCreateDatabase(ExecutionEnvironment env, String... args) {
        environment = env;
        PARSER.setArgs(ARGS_NUM, args);
        databaseName = PARSER.getArg(Parser.argNames.databaseName);
    }

    @Override
    public DatabaseCommandResult execute() {
        environment.addDatabase(null);
        return DatabaseCommandResult.success("Database created");
    }
}
