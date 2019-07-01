package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Dish {

    private String name;
    private Integer calories;
    private Boolean isBio;
    private DishType type;

}

