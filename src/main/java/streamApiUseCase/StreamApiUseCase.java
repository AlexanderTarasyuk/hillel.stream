package streamApiUseCase;

import contract.UseCaseContract;
import jdk.internal.org.jline.utils.Log;
import model.Dish;
import model.DishType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApiUseCase implements UseCaseContract<List<Dish>> {
    private static volatile StreamApiUseCase streamApiUseCase;

    private StreamApiUseCase() {
    }

    public static StreamApiUseCase getInstance() {
        if (streamApiUseCase == null) {
            synchronized (StreamApiUseCase.class) {
                if (streamApiUseCase == null) {
                    streamApiUseCase = new StreamApiUseCase();
                }
            }
        }
        Log.error("");
        return streamApiUseCase;
    }


    @Override
    public void justDoIt(List<Dish> list) {
        printNameDish(list);
        printNameDishLowCallories(list);
        printNameDishThreeMostWithCallories(list);
        printByType(list);
        printByName(list);
        countAverageCalorieByType(list);
        groupByType(list);
        groupByTypeIsBio(list);

    }

    @Override
    public void printNameDish(List<Dish> menu) {
        menu.stream()
                .map(Dish::getName)
                .forEach(System.out::println);

    }

    @Override
    public void printNameDishLowCallories(List<Dish> menu) {
        menu.stream()
                .filter(dish -> dish.getCalories() < 150)
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    @Override

    public void printNameDishThreeMostWithCallories(List<Dish> menu) {


        menu.stream()
                .sorted((dish1, dish2) -> dish2.getCalories().compareTo(dish1.getCalories()))
                .limit(3)
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    @Override
    public void printByType(List<Dish> menu) {
        menu.stream()
                .sorted((dish1, dish2) -> dish1.getType().compareTo(dish2.getType()))
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    @Override
    public void printByName(List<Dish> menu) {

        menu.stream()
                .sorted((dish1, dish2) -> dish1.getName().compareTo(dish2.getName()))
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    @Override
    public Map<DishType, Double> countAverageCalorieByType(List<Dish> menu) {

        return menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.averagingDouble(Dish::getCalories)));
    }

    @Override
    public Map<DishType, List<Dish>> groupByType(List<Dish> menu) {

        return menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));


    }

    @Override
    public Map<DishType, List<String>> groupByTypeIsBio(List<Dish> menu) {

        return menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping((Dish dish) -> dish.getName(), Collectors.toList())));


    }
}
