package org.ficko.base;

public abstract class LoadablePage {
    public LoadablePage() {
        isLoaded();
    }

    protected abstract void isLoaded();
}
