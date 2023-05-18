package org.ficko.models;

public enum GroupTab {
    ACTUAL("Актуально"),
    NEWS("Новые");

    private final String name;

    GroupTab(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
