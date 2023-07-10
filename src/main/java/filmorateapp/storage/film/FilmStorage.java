package filmorateapp.storage.film;

import filmorateapp.model.Film;

import java.util.HashMap;

public interface FilmStorage {

    public HashMap<Integer, Film> getAllFilms();

    public Film getFilmById(int id);

    public Film addFilm(Film film);

    public Film updateFilm(Film film);

    public Film deleteFilm(int id);

    public void deleteAllFilms();
}