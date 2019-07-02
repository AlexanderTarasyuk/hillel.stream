package rxJavaUseCase;

import contract.UseCaseContract;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import jdk.internal.org.jline.utils.Log;
import model.Dish;
import model.DishType;
import streamApiUseCase.StreamApiUseCase;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RxJavaUseCase implements UseCaseContract<List<Dish>> {

    private static volatile RxJavaUseCase rxJavaUseCase;
    private CompositeDisposable compositeDisposable;

    private RxJavaUseCase() {
        compositeDisposable = new CompositeDisposable();
    }

    public static RxJavaUseCase getInstance() {
        if (rxJavaUseCase == null) {
            synchronized (StreamApiUseCase.class) {
                if (rxJavaUseCase == null) {
                    rxJavaUseCase = new RxJavaUseCase();
                }
            }
        }
        Log.debug("Singleton RXJava UseCase created");
        return rxJavaUseCase;
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
        Observable<Dish> observable = Observable.fromIterable(menu);

        observable
                .subscribeOn(Schedulers.computation())
                .subscribe(dish -> {
                    System.out.println(dish.getName());
                }, Throwable::printStackTrace);


    }

    @Override
    public void printNameDishLowCallories(List<Dish> menu) {
        Observable<Dish> observable = Observable.fromIterable(menu);

        observable
                .subscribeOn(Schedulers.computation())
                .filter(dish -> dish.getCalories() < 150)
                .subscribe(dish -> {
                    System.out.println(dish.getName());
                }, throwable -> System.out.println(throwable.getStackTrace()));


    }

    @Override

    public void printNameDishThreeMostWithCallories(List<Dish> menu) {
        Observable<Dish> observable = Observable.fromIterable(menu);

        observable
                .subscribeOn(Schedulers.computation())
                .sorted((dish1, dish2) -> dish1.getCalories().compareTo(dish2.getCalories()))
                .take(3)
                .subscribe(dish -> {
                    System.out.println(dish.getName());
                }, Throwable::printStackTrace);




    }

    @Override
    public void printByType(List<Dish> menu) {
        Observable<Dish> observable = Observable.fromIterable(menu);

        observable
                .subscribeOn(Schedulers.computation())
                .sorted((dish1, dish2) -> dish1.getType().compareTo(dish2.getType()))
                .subscribe(dish -> {
                    System.out.println(dish.getName());
                }, Throwable::printStackTrace);


    }

    @Override
    public void printByName(List<Dish> menu) {

        Observable<Dish> observable = Observable.fromIterable(menu);
        observable
                .subscribeOn(Schedulers.computation())
                .sorted((dish1, dish2) -> dish1.getName().compareTo(dish2.getName()))
                .subscribe(dish -> {
                    System.out.println(dish.getName());
                }, Throwable::printStackTrace);


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
