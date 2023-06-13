package filmorateapp.storage.film;

import filmorateapp.model.Film;
import filmorateapp.model.exeption.NotFoundException;
import org.springframework.stereotype.Component;

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
     * Удаление фильма;
     */
    @Override
    public boolean deleteFilm(long id) {
        Iterator<Film> iterator = films.iterator();
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (film.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Получение 10 лучших фильмов;
     */
    @Override
    public List<Film> getBestFilms(int count) {
        List<Film> allFilms = getBestFilms(count);
        allFilms.sort(Comparator.comparingInt(Film::getLike).reversed());
        return  allFilms.stream()
                .limit(count)
                .collect(Collectors.toList());
    }
}