package oldSchoolUseCase;

import contract.UseCaseContract;
import model.Dish;
import model.DishType;

import java.util.*;

public final class OldSchoolUseCase implements UseCaseContract<List<Dish>> {

    private static volatile OldSchoolUseCase oldSchoolUseCase;

    private OldSchoolUseCase() {
    }

    public static OldSchoolUseCase getInstance() {
        if (oldSchoolUseCase == null) {
            synchronized (OldSchoolUseCase.class) {
                if (oldSchoolUseCase == null) {
                    oldSchoolUseCase = new OldSchoolUseCase();
                }
            }
        }
        return oldSchoolUseCase;
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
        if (menu != null && !menu.isEmpty()) {
            for (Dish dish : menu) {
                System.out.println(dish.getName());

            }
        }
    }

    @Override
    public void printNameDishLowCallories(List<Dish> menu) {
        if (menu != null && !menu.isEmpty()) {

            for (Dish dish : menu) {
                if (dish.getCalories() < 150) {
                    System.out.println(dish.getName());
                }
            }
        }
    }

    @Override
    public void printNameDishThreeMostWithCallories(List<Dish> menu) {

        if (menu != null && !menu.isEmpty()) {

            Collections.sort(menu, new Comparator<Dish>() {
                        @Override
                        public int compare(Dish o1, Dish o2) {
                            return o1.getCalories().compareTo(o2.getCalories());
                        }
                    }
            );
            Collections.reverse(menu);
            for (int i = 0; i < menu.size() && i < 3; i++) {
                System.out.println(menu.get(i).toString());
            }
        }

    }

    @Override
    public void printByType(List<Dish> menu) {

        if (menu != null && !menu.isEmpty()) {

            menu.sort(new Comparator<Dish>() {
                @Override
                public int compare(Dish o1, Dish o2) {
                    return o1.getType().compareTo(o2.getType());
                }
            });
        }

        for (Dish dish : menu) {
            System.out.println(dish.toString());
        }
    }

    @Override
    public void printByName(List<Dish> menu) {

        if (menu != null && !menu.isEmpty()) {

            menu.sort(new Comparator<Dish>() {
                @Override
                public int compare(Dish o1, Dish o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }

        for (Dish dish : menu) {
            System.out.println(dish.toString());
        }
    }

    @Override
    public Map<DishType, Double> countAverageCalorieByType(List<Dish> menu) {
        Map<DishType, Double> dishTypeLongHashMap = new HashMap<>();
        double calorieBeef = 0.0;
        double calorieChicken = 0.0;
        double calorieVegetable = 0.0;
        int numberOfBeef = 0;
        int numberOfChicken = 0;
        int numberOfVegetables = 0;

        if (menu != null && !menu.isEmpty()) {
            for (Dish dish : menu) {
                switch (dish.getType()) {

                    case BEEF:
                        calorieBeef += dish.getCalories();
                        numberOfBeef++;
                        break;
                    case CHICKEN:
                        calorieChicken += dish.getCalories();
                        numberOfChicken++;
                        break;
                    case VEGETABLES:
                        calorieVegetable += dish.getCalories();
                        numberOfVegetables++;
                        break;
                    default:
                        break;
                }
            }
        }

        dishTypeLongHashMap.put(DishType.BEEF, calorieBeef / numberOfBeef);
        dishTypeLongHashMap.put(DishType.CHICKEN, calorieChicken / numberOfChicken);
        dishTypeLongHashMap.put(DishType.VEGETABLES, calorieVegetable / numberOfVegetables);
        return dishTypeLongHashMap;

    }

    @Override
    public Map<DishType, List<Dish>> groupByType(List<Dish> menu) {
        Map<DishType, List<Dish>> dishTypeLongHashMap = new HashMap<>();
        List<Dish> beefList = Collections.EMPTY_LIST;
        List<Dish> chickenList = Collections.EMPTY_LIST;
        List<Dish> vegetablesList = Collections.EMPTY_LIST;


        if (menu != null && !menu.isEmpty()) {
            for (Dish dish : menu) {
                switch (dish.getType()) {

                    case BEEF:
                        beefList.add(dish);
                        break;
                    case CHICKEN:
                        chickenList.add(dish);
                        break;
                    case VEGETABLES:
                        vegetablesList.add(dish);
                        break;
                    default:
                        break;
                }
            }
        }

        dishTypeLongHashMap.put(DishType.BEEF, beefList);
        dishTypeLongHashMap.put(DishType.CHICKEN, chickenList);
        dishTypeLongHashMap.put(DishType.VEGETABLES, vegetablesList);

        return dishTypeLongHashMap;

    }

    @Override
    public Map<DishType, List<String>> groupByTypeIsBio(List<Dish> menu) {
        Map<DishType, List<String>> dishTypeLongHashMap = new HashMap<>();
        List<String> beefList = Collections.EMPTY_LIST;
        List<String> chickenList = Collections.EMPTY_LIST;
        List<String> vegetablesList = Collections.EMPTY_LIST;


        if (menu != null && !menu.isEmpty()) {
            for (Dish dish : menu) {
                switch (dish.getType()) {

                    case BEEF:
                        if (dish.getIsBio()) beefList.add(dish.getName());
                        break;
                    case CHICKEN:
                        if (dish.getIsBio()) chickenList.add(dish.getName());
                        break;
                    case VEGETABLES:
                        if (dish.getIsBio()) vegetablesList.add(dish.getName());
                        break;
                    default:
                        break;
                }
            }
        }

        dishTypeLongHashMap.put(DishType.BEEF, beefList);
        dishTypeLongHashMap.put(DishType.CHICKEN, chickenList);
        dishTypeLongHashMap.put(DishType.VEGETABLES, vegetablesList);

        return dishTypeLongHashMap;

    }
}
