package me.luucka.custombook.permissions;

public enum PlayerPermission {

    CBOOK_CREATE("cbook.create"),
    CBOOK_DELETE("cbook.create"),
    CBOOK_EDIT("cbook.edit"),
    CBOOK_GIVE("cbook.give"),
    CBOOK_OPEN("cbook.open"),
    CBOOK_RELOAD("cbook.reload"),
    CBOOK_UPDATE("cbook.update"),
    CBOOK_HELP("cbook.help"),
    CBOOK_LIST("cbook.list");

    private final String permssion;

    PlayerPermission(String permssion) {
        this.permssion = permssion;
    }

    public String getPermssion() {
        return permssion;
    }
}
