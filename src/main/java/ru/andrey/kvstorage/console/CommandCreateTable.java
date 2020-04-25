package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CommandCreateTable implements DatabaseCommand {
    private static final int ARGS_NUM = 2;
    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;

    public CommandCreateTable(ExecutionEnvironment env, String... args) {
        environment = env;
        Parser parser = new Parser();
        parser.setArgs(ARGS_NUM, args);
        databaseName = parser.getArg(Parser.argNames.databaseName);
        tableName = parser.getArg(Parser.argNames.tableName);
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + databaseName + " doesn't exist");
        }
        try {
            database.get().createTableIfNotExists(tableName);
            return DatabaseCommandResult.success("Table created");
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.error(ex.getMessage());
        }
    }
}
