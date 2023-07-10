package filmorateapp.storage.film;

import filmorateapp.model.Film;
import filmorateapp.model.exeption.DataNotFoundException;
import filmorateapp.model.exeption.InstanceAlreadyExistException;
import filmorateapp.model.exeption.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    private final HashMap<Integer, Film> films = new HashMap<>();
    private int filmCount = 1;

    @Override
    public HashMap<Integer, Film> getAllFilms() {
        log.info("Передан список всех фильмов");
        return films;
    }

    @Override
    public Film getFilmById(int id) {
        if (!films.containsKey(id)) {
            throw new DataNotFoundException("Фильм с id " + id + " не найден.");
        }
        log.info("Передан фильм id = {}", id);
        return films.get(id);
    }

    @Override
    public Film addFilm(Film film) {
        checkFilmValidation(film);
        if (films.containsValue(film)) {
            throw new InstanceAlreadyExistException("Не удалось добавить фильм: фильм уже существует");
        }
        film.setId(filmCount);
        film.setLikedUsersId(new HashSet<>());
        films.put(filmCount, film);
        filmCount++;
        log.info("Добавлен фильм {}", film);
        return films.get(filmCount - 1);
    }

    @Override
    public Film updateFilm(Film film) {

        checkFilmValidation(film);
        if (films.containsKey(film.getId())) {
            film.setLikedUsersId(films.get(film.getId()).getLikedUsersId());
            films.replace(film.getId(), film);
            log.info("Обновлен фильм {}", film);
            return film;
        }
        throw new DataNotFoundException("Не удалось обновить фильм: фильм не найден.");
    }

    @Override
    public Film deleteFilm(int id) {

        if (!films.containsKey(id)) {
            throw new DataNotFoundException("Не удалось удалить фильм: фильм не найден.");
        }
        Film removingFilm = films.get(id);
        films.remove(id);
        log.info("Удален фильм {}", removingFilm);
        return removingFilm;
    }

    @Override
    public void deleteAllFilms() {
        filmCount = 1;
        films.clear();
        log.info("Список фильмов очищен");
    }

    private void checkFilmValidation(Film film) {
        StringBuilder message = new StringBuilder().append("Не удалось добавить фильм: ");
        boolean isValid = true;

        if (film.getDescription().length() > 200) {
            message.append("описание не должно превышать 200 символов; ");
            isValid = false;
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            message.append("фильм не мог быть выпущен до рождения кино; ");
            isValid = false;
        }
        if (film.getDuration() <= 0) {
            message.append("длительность фильма должна быть положительной; ");
            isValid = false;
        }
        if (!isValid) {
            throw new ValidationException(message.toString());
        }
    }
}