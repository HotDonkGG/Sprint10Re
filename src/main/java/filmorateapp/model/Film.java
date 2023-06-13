package filmorateapp.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@ToString
public class Film {
    /**
     * Создание пользователя, не null, id положительный
     */
    @NonNull
    @Positive
    private int id;
    /**
     * Создание имени, не null, имя не может быть пустым
     */
    @NotBlank
    @NonNull
    private String name;
    /**
     * Создание описания, не null, описание не может быть пустым, длина 200
     */
    @Size(max = 200)
    @NonNull
    @NotBlank
    private String description;
    /**
     * Дата релиза в прошлом
     */
    @Past
    private LocalDate releaseDate;
    /**
     * Продолжительность положительная
     */
    @Positive
    @NonNull
    private Long duration;
    private int like;
    private Set<Integer> likes;
}