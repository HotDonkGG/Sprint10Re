package filmorateapp.service.film;

import filmorateapp.model.Film;
import filmorateapp.storage.film.FilmStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Service
@RequestMapping("/films")
@Slf4j
public class FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public void addLike(long filmId, long userId) {
        filmStorage.getFilmById(filmId).getLikes().add((int) userId);
    }

    public void deleteLike(int userId, int filmId) {
        if (filmStorage.getFilmById(filmId).getLikes().contains(userId)) {
            filmStorage.getFilmById(filmId).getLikes().remove(userId);
        }
    }

    public List<Film> getBestFilms(int count) {
        List<Film> bestFilms = filmStorage.getBestFilms(count);
        return bestFilms != null ? bestFilms : Collections.emptyList();
    }
}