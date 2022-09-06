import org.example.ListOfPlants;
import org.example.Main;
import org.example.Plant;
import org.example.PlantException;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkWithListsTest {

    ListOfPlants list;

    @BeforeAll
    public void setUp() throws PlantException {
        list = ListOfPlants.importFromFile("kvetiny.txt");
    }
    @Order(1)
    @Test
    public void listTest() {
        Assertions.assertEquals(3, list.getListOfPlants().size());
        Assertions.assertEquals("Fialka 1", list.getListOfPlants().get(0).getName());
        Assertions.assertEquals("Vánoční hvězda bez poznámky", list.getListOfPlants().get(1).getName());
        Assertions.assertEquals("Sukulent v koupelně", list.getListOfPlants().get(2).getName());
    }
    @Order(2)
    @Test
    public void listSortedByNameTest() {
        list.sortByName();
        Assertions.assertEquals("Fialka 1", list.getListOfPlants().get(0).getName());
        Assertions.assertEquals("Sukulent v koupelně", list.getListOfPlants().get(1).getName());
        Assertions.assertEquals("Vánoční hvězda bez poznámky", list.getListOfPlants().get(2).getName());
    }
    @Order(3)
    @Test
    public void listSortedByWateringTest() {
        list.sortByWatering();
        Assertions.assertEquals("Sukulent v koupelně", list.getListOfPlants().get(0).getName());
        Assertions.assertEquals("Vánoční hvězda bez poznámky", list.getListOfPlants().get(1).getName());
        Assertions.assertEquals("Fialka 1", list.getListOfPlants().get(2).getName());
    }

    //test method getSetOfWaterings
    @Order(4)
    @Test
    public void setOfWateringsTest() throws PlantException {

        Plant plant = new Plant("Bazalka v kuchyni", "", LocalDate.of(2021,4,4),
                LocalDate.of(2021,5,10), 3);
        list.addPlant(plant);

        Assertions.assertEquals(4, list.getListOfPlants().size());

        Set<LocalDate> set = list.getSetOfWaterings();

        Assertions.assertEquals(3, list.getSetOfWaterings().size());

        //generating list,whose items must be equal with items of returned set

        List <LocalDate> tempList = new ArrayList<>();
        tempList.add(LocalDate.of(2021,5,12));
        tempList.add(LocalDate.of(2021,5,10));
        tempList.add(LocalDate.of(2011,3,1));

        Assertions.assertEquals(true, set.containsAll(tempList));
    }


    //test method 'Main.getsetofwateringssevendays'
    @Order(5)
    @Test
    public void setOfWateringsSevenDaysTest() throws PlantException {

        //generating input paramether for method 'Main.getsetofwateringssevendays'

        Set<LocalDate> setOfWaterings = new HashSet<>();
        setOfWaterings.add(LocalDate.now().minusDays(7));
        setOfWaterings.add(LocalDate.now());
        setOfWaterings.add(LocalDate.now().minusDays(5));
        setOfWaterings.add(LocalDate.now().minusDays(8));
        setOfWaterings.add(LocalDate.of(2021,5,10));
        setOfWaterings.add(LocalDate.now().minusDays(5));

        Set<LocalDate> wateringsForLastSevenDays = Main.getsetofwateringssevendays(setOfWaterings);

        Assertions.assertEquals(3,wateringsForLastSevenDays.size());

        //generating list,whose items must be equal with items of returned set

        List <LocalDate> tempList = new ArrayList<>();
        tempList.add(LocalDate.now());
        tempList.add(LocalDate.now().minusDays(5));
        tempList.add(LocalDate.now().minusDays(7));

        Assertions.assertEquals(true, wateringsForLastSevenDays.containsAll(tempList));
    }

}