package filmorateapp.storage.film;

import filmorateapp.model.Film;
import filmorateapp.model.exeption.NotFoundException;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final HashSet<Film> films = new HashSet<>();
    private int nextId = 0;

    /**
     * Добавление фильма, кладем фильм в Хэшсет
     */
    @Override
    public Film addFilm(Film film) {
        film.setId(nextId++);
        films.add(film);
        return film;
    }

    /**
     * Обновление фильма
     */
    @Override
    public Film updateFilm(Film film) {
        for (Film existingFilm : films) {
            if (existingFilm.getId() == film.getId()) {
                films.remove(existingFilm);
                films.add(film);
                return film;
            }
        }
        throw new NotFoundException("Фильм не найден");
    }

    /**
     * Получение фильма по Id;
     */
    @Override
    public Film getFilmById(long id) {
        for (Film film : films) {
            if (film.getId() == id) {
                return film;
            }
        }
        throw new NotFoundException("Фильм не найден");
    }

    /**
     * Получение 10 лучших фильмов;
     */
    @Override
    public List<Film> getBestFilms(int count) {
        List<Film> allFilms = getBestFilms(count);
        allFilms.sort(Comparator.comparingInt(Film::getLike).reversed());
        return allFilms.stream()
                .limit(count)
                .collect(Collectors.toList());
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