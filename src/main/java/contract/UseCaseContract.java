package contract;

import model.Dish;
import model.DishType;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UseCaseContract<T> {



    void justDoIt(T t);

    void printNameDish(T t);

    void printNameDishLowCallories(T t);

    void printNameDishThreeMostWithCallories(T t);

    void printByType(T t);

    void printByName(T t);

    Map<DishType, Double> countAverageCalorieByType(T t);

    Map<DishType, List<Dish>> groupByType(T t);

    Map<DishType, List<String>> groupByTypeIsBio(T t);
}
