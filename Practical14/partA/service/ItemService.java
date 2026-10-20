package partA.service;

import partA.model.Item;
import java.util.ArrayList;

public class ItemService {
    private final ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public int count() {
        return items.size();
    }

    public void action(String action) {
        if (!action.equals("add")) {
            throw new IllegalArgumentException("Invalid action");
        }
    }
}
