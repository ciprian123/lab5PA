package com.ciprianursulean.lab5.commands;

public class InvalidCommandFormat extends Exception {
    public InvalidCommandFormat() {
        super("Wrong command or syntax error");
    }
}
