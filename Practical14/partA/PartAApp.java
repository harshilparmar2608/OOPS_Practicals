package partA;

import partA.model.Item;
import partA.service.ItemService;

public class PartAApp {
    public static void main(String[] args) {
        ItemService service = new ItemService();
        service.add(new Item("Book"));
        service.add(new Item("Pen"));
        System.out.println("Count: " + service.count());
        service.action("add");
    }
}
