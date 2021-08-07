package me.luucka.custombook.menus;

public abstract class PaginatedMenu extends Menu {

    protected int page;
    private final static int MAX_ITEM_PER_PAGE = 45;

    public PaginatedMenu(MenuUtility menuUtility, String name, int page) {
        super(menuUtility, name, MAX_ITEM_PER_PAGE);
        this.page = page;
    }
}
