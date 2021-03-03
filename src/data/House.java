package data;
/**
 * Класс домов для квартиры
 */
public class House {
    private String name; //Поле не может быть null
    private Long year; //Максимальное значение поля: 846, Значение поля должно быть больше 0
    private Long numberOfFloors; //Значение поля должно быть больше 0
    public House(String name,Long year,Long numberOfFloors){
        this.name=name;
        this.year=year;
        this.numberOfFloors=numberOfFloors;
    }

    @Override
    public String toString() {
        return "Название комплекса: " + name  +
                ", год сдачи: " + (2021-year) +
                ", количество этажей: " + numberOfFloors ;
    }
}
