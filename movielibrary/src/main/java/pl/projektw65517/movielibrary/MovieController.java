package pl.projektw65517.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController to adnotacja dzieki ktorej klasa obsługuje rządania REST
@RestController
//dzieki requestMapping mozemy podac adres pod którym beda dostepne wszystkie metody
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    /*
     * funkcja pozwalająca wybierać wszystkie filmy
     * */
    @GetMapping("/")
    public List<Movie> getAll() {
        //wywołujemy metode getAll
        return movieRepository.getAll(); }
    /*
     * funkcja pozwalająca wybierać dane filmy po jego id
     * */
    @GetMapping("/{id}")
    //pathVariable przekazuje nam zmienna
    public Movie getById(@PathVariable("id") int id){
        //wywołujemy metode getById
        return movieRepository.getById(id);
    }
    /*
     * funkcja pozwalająca dodawać dane filmy do naszej bazy
     * */
    @PostMapping("/")
    public int add(@RequestBody List<Movie> movies){
        return movieRepository.save(movies);
    }

    /*
     * funkcja pozwalająca wszystkie pola z danego filmu po jego id
     * */
    // PuttMapping podmienia cały obiekt
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updateMovie ){
        //pobieramy film z bazy danych
        Movie movie = movieRepository.getById(id);

        if(movie != null){
            //ustawiamy nowe wartosci
            movie.setName(updateMovie.getName());
            movie.setRating(updateMovie.getRating());

            movieRepository.update(movie);

            //ustawiamy 1
            return 1;
        }else{
            return  -1;
        }
    }

    /*
     * funkcja pozwalająca edytować dane pole dotyczącego filmu po jego id
     * */

    //patchMapping pozwala na edycje tylko wybranych pól
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
        Movie movie = movieRepository.getById(id);
if(movie != null){
    //poniższe ify sprawdzają czy wartosci zostały podane
if(updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
if(updatedMovie.getRating() >0) movie.setRating(updatedMovie.getRating());

movieRepository.update(movie);
return 1;
}else{
    return -1;
}
    }

    /*
    * funkcja pozwalająca usuwać dane filmy po jego id
    * */
@DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
      return movieRepository.delete(id);
    }

}
