import partA.model.Item;
import partA.service.ItemService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartATest {
    @Test
    void addingIncreasesCount() {
        ItemService service = new ItemService();
        service.add(new Item("Book"));
        assertEquals(1, service.count());
    }

    @Test
    void invalidActionThrows() {
        ItemService service = new ItemService();
        assertThrows(IllegalArgumentException.class, () -> service.action("remove"));
    }
}
