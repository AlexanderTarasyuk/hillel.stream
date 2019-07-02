package restaraunt;

import contract.UseCaseContract;
import lombok.Data;
import model.Dish;
import model.DishType;
import oldSchoolUseCase.OldSchoolUseCase;
import rxJavaUseCase.RxJavaUseCase;
import streamApiUseCase.StreamApiUseCase;

import java.util.ArrayList;
import java.util.List;

@Data
public class Restaurant {


    private static List<Dish> menu = new ArrayList<>() {
        {
            add(new Dish("Beef", 100, false, DishType.BEEF));
            add(new Dish("Meat", 200, true, DishType.BEEF));
            add(new Dish("Chicken", 150, true, DishType.CHICKEN));
            add(new Dish("Potato", 200, true, DishType.VEGETABLES));
            add(new Dish("Tomato", 100, false, DishType.VEGETABLES));
        }
    };

    public static void main(String[] args) {
        UseCaseContract<List<Dish>> oldSchoolUseCaseAllInOne = OldSchoolUseCase.getInstance();
        UseCaseContract<List<Dish>> streamApiUseCase = StreamApiUseCase.getInstance();
        UseCaseContract<List<Dish>> reactiveUseCase= RxJavaUseCase.getInstance();

        oldSchoolUseCaseAllInOne.justDoIt(menu);
        streamApiUseCase.justDoIt(menu);
        reactiveUseCase.justDoIt(menu);



    }


}

