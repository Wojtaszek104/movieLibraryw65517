package pl.projektw65517.movielibrary;
//aby nie tworzyc getterów,setterów i konstruktorów używamy biblioteki loombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//adnotacje do aby utowrzyc konstruktor bezparametrowy, konstruktor z argumentami dla wszystkich pól z klasy
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private int id;
    private String name;
    private int rating;
}
