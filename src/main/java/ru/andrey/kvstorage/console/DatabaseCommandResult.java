package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImplementation(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResultImplementation(DatabaseCommandStatus.FAILED, message);
    }

    class DatabaseCommandResultImplementation implements DatabaseCommandResult {
        private final DatabaseCommandStatus status;
        private final String message;

        private DatabaseCommandResultImplementation(DatabaseCommandStatus initStatus, String initMessage) {
            status = initStatus;
            message = initMessage;
        }

        @Override
        public Optional<String> getResult() {
            if (isSuccess()) {
                return Optional.of(message);
            }
            return Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status == DatabaseCommandStatus.SUCCESS;
        }

        @Override
        public String getErrorMessage() {
            return message;
        }
    }
}