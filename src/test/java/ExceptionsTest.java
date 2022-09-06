import org.example.ListOfPlants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

@Test
public void fileNotFoundTest() {
    Exception thrown = Assertions.assertThrows(Exception.class, () -> ListOfPlants.importFromFile("abcd.txt"));
    Assertions.assertEquals(thrown.getLocalizedMessage(), thrown.getMessage());
}

@Test
public void wrongDateTest() {
    Exception thrown = Assertions.assertThrows(Exception.class, () -> ListOfPlants.importFromFile("kvetiny-spatne-datum.txt"));
    Assertions.assertEquals(thrown.getLocalizedMessage(), thrown.getMessage());
}

@Test
    public void wrongFrekvencyTest() {
    Exception thrown = Assertions.assertThrows(Exception.class, () -> ListOfPlants.importFromFile("kvetiny-spatne-frekvence.txt"));
    Assertions.assertEquals(thrown.getLocalizedMessage(), thrown.getMessage());
}

}
