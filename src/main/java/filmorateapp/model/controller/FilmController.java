package filmorateapp.model.controller;

import filmorateapp.model.Film;
import filmorateapp.service.film.FilmService;
import filmorateapp.storage.user.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import java.util.*;

@RestController
@Slf4j
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @DeleteMapping("/films")
    public void deleteAllFilms() {
        log.info("Запрос: удалить все фильмы");
        filmService.deleteAllFilms();
    }

    @PostMapping("/films")
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Запрос: добавить фильм {}", film);
        return filmService.addFilm(film);
    }

    @PutMapping("/films")
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Запрос: обновить на фильм {}", film);
        return filmService.updateFilm(film);
    }

    @GetMapping("/films")
    public Collection<Film> getAllFilms() {
        log.info("Запрос: передать все фильмы");
        return filmService.getAllFilms().values();
    }

    @GetMapping("/films/{id}")
    public Film getFilmById(@PathVariable int id) {
        log.info("Запрос: передать фильм по id {}", id);
        return filmService.getFilmGyId(id);
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        log.info("Запрос: добавить фильму с id {} лайк от пользователя с id {}", id, userId);
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        log.info("Запрос: удалить фильму с id {} лайк от пользователя с id {}", id, userId);
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/films/popular")
    public List<Film> getMostLikedFilms(@RequestParam(defaultValue = "" + Constants.MOST_LIKED_FILMS_NUMBER) Integer count) {
        log.info("Запрос: вывести список самых популярных фильмов count = {}", count);
        return filmService.getMostLikedFilms(count);
    }

}
