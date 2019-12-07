package org.toyrobot.runtime;

public class DefaultRuntime implements Runtime {
    @Override
    @SuppressWarnings("squid:S106")
    public void print(String message) {
        System.out.print(message);
    }
}
