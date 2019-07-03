package oldSchoolUseCase;

import model.Dish;
import model.DishType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class OldSchoolUseCaseTest {
    private static List<Dish> menu;
    OldSchoolUseCase oldSchoolUseCase;


    @Before
    public void setUp() throws Exception {
        oldSchoolUseCase=OldSchoolUseCase.getInstance();
        menu = new ArrayList<>() {
            {
                add(new Dish("Beef", 100, false, DishType.BEEF));
                add(new Dish("Meat", 200, true, DishType.BEEF));
                add(new Dish("Chicken", 150, true, DishType.CHICKEN));
                add(new Dish("Potato", 200, true, DishType.VEGETABLES));
                add(new Dish("Tomato", 100, false, DishType.VEGETABLES));
            }
        };
    }

    @After
    public void tearDown() throws Exception {

        oldSchoolUseCase.groupByType(menu);

    }

    @Test
    public void justDoIt() {
    }

    @Test
    public void printNameDish() {


    }

    @Test
    public void printNameDishLowCallories() {
    }

    @Test
    public void printNameDishThreeMostWithCallories() {
    }

    @Test
    public void printByType() {
    }

    @Test
    public void printByName() {
    }

    @Test
    public void countAverageCalorieByType() {
    }

    @Test
    public void groupByType() {
    }

    @Test
    public void groupByTypeIsBio() {
    }
}