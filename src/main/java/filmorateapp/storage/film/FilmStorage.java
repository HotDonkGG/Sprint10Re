package filmorateapp.storage.film;

import filmorateapp.model.Film;

import java.util.List;

public interface FilmStorage {
    /**
     * Добавление фильма
     *
     * @param film
     */
    Film addFilm(Film film);

    /**
     * Обновление фильма
     *
     * @param film
     */

    Film updateFilm(Film film);

    /**
     * Получение фильма по id
     *
     * @param id
     */
    Film getFilmById(long id);

    /**
     * Получение списка лучших фильмов
     *
     * @param count
     */
    List<Film> getBestFilms(int count);
}