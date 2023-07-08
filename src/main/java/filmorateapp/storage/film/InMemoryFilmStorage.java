package filmorateapp.storage.film;

import filmorateapp.model.Film;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();

    @Override
    public Film addFilm(Film film) {
       film.generateAndSetId();
       film.generateSetOfLikes();
       films.put(film.getId(), film);
       return film;
    }

    @Override
    public Film updateFilm(Film film) {
        film.setLikes(films.get(film.getId()).getLikes());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film getFilmById(int filmId) {
        return films.get(filmId);
    }


    @Override
    public void addLike(int filmId, int userId){
        films.get(filmId).addLike(userId);
    }

    @Override
    public void removeLike(int filmId, int userId){
        films.get(filmId).removeLike(userId);
    }

    @Override
    public List<Film> getAllFilms(){
        return new ArrayList<>(films.values());
    }

    public void validate(Film film) throws ValidationException {
        if (film.getName() == null || film.getName().isEmpty()) {
            throw new ValidationException("фильм не может быть пустым");
        } else if (film.getDescription() != null && film.getDescription().length() > 200) {
            throw new ValidationException("Описание фильма более 200 символов");
        } else if (film.getReleaseDate() != null && film.getReleaseDate().isBefore(ChronoLocalDate.from(LocalDateTime.of(1895, 12, 28, 0, 0)))) {
            throw new ValidationException("Дата релиза не может быть раньше чем 28/12/1895г.");
        } else if (film.getDuration() != 0 && film.getDuration() <= 0) {
            throw new ValidationException("Фильм должен иметь положительное значение");
        }
    }
}