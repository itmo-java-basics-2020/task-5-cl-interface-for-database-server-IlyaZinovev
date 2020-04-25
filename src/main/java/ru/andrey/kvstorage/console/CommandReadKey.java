package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CommandReadKey implements DatabaseCommand {
    private static final int ARGS_NUM = 3;
    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;
    private final String key;

    public CommandReadKey(ExecutionEnvironment env, String... args) {
        environment = env;
        Parser parser = new Parser();
        parser.setArgs(ARGS_NUM, args);
        databaseName = parser.getArg(Parser.argNames.databaseName);
        tableName = parser.getArg(Parser.argNames.tableName);
        key = parser.getArg(Parser.argNames.key);
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " doesn't exist");
        }
        try {
            return DatabaseCommandResult.success(database.get().read(tableName, key));
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }
    }
}
