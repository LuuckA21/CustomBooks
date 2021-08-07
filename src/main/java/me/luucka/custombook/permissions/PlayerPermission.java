package me.luucka.custombook.permissions;

public enum PlayerPermission {

    CBOOK_CREATE("cbook.create"),
    CBOOK_DELETE("cbook.delete"),
    CBOOK_EDIT("cbook.edit"),
    CBOOK_GIVE("cbook.give"),
    CBOOK_OPEN("cbook.open"),
    CBOOK_RELOAD("cbook.reload"),
    CBOOK_UPDATE("cbook.update"),
    CBOOK_HELP("cbook.help"),
    CBOOK_LIST("cbook.list");

    private final String permission;

    PlayerPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
