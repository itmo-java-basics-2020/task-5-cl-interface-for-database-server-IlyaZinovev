package ru.andrey.kvstorage.console;

public enum DatabaseCommands {

    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CommandCreateDatabase(env, args);
        };
    },
    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CommandCreateTable(env, args);
        };
    },
    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CommandUpdateKey(env, args);
        };
    },
    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) {
            return new CommandReadKey(env, args);
        };
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);
    }
