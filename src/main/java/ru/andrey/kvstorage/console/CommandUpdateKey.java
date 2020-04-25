package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CommandUpdateKey implements DatabaseCommand {
    private static final int ARGS_NUM = 4;
    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;
    private final String value;

    public CommandUpdateKey(ExecutionEnvironment env, String... args) {
        environment = env;
        Parser parser = new Parser();
        parser.setArgs(ARGS_NUM, args);
        databaseName = parser.getArg(Parser.argNames.databaseName);
        tableName = parser.getArg(Parser.argNames.tableName);
        key = parser.getArg(Parser.argNames.key);
        value = parser.getArg(Parser.argNames.value);
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " doesn't exist");
        }
        try {
            database.get().write(tableName, key, value);
            return DatabaseCommandResult.success("Key updated");
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }
    }
}
