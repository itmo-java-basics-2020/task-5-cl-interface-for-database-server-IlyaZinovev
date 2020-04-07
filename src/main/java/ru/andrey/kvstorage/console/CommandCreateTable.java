package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CommandCreateTable implements DatabaseCommand {
    private static final int ARGS_NUM = 2;
    private static final Parser PARSER = new Parser();
    private final ExecutionEnvironment environment;
    private final String databaseName;
    private final String tableName;

    public CommandCreateTable(ExecutionEnvironment env, String... args) {
        environment = env;
        PARSER.setArgs(ARGS_NUM, args);
        databaseName = PARSER.getArg(Parser.argNames.databaseName);
        tableName = PARSER.getArg(Parser.argNames.tableName);
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
