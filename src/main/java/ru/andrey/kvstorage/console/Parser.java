package ru.andrey.kvstorage.console;

public class Parser {
    private String[] args;

    public enum argNames{
        databaseName, tableName, key, value
    }

    public void setArgs(int expectedNum, String... initArgs) throws IllegalArgumentException {
        int actualNum = initArgs.length;
        if (expectedNum != actualNum) {
            throw new IllegalArgumentException("Arguments expected: " + expectedNum + "  got: " + actualNum);
        }
        args = initArgs;
    }

    public String getArg(argNames name) {
        return args[name.ordinal()];
    }
}
