package filmorateapp.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = {"id"})
public class Film {
    private static int identification = 0;
    /**
     * Создание пользователя, не null, id положительный
     */
    @Positive
    private int id;
    /**
     * Создание имени, не null, имя не может быть пустым
     */
    @NotBlank
    private String name;
    /**
     * Создание описания, не null, описание не может быть пустым, длина 200
     */
    @Size(max = 200)
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
    private Long duration;
    private int like;
    private Set<Integer> likes;

    public void generateAndSetId(){
        setId(++identification);
    }

    public void generateSetOfLikes(){
        this.likes = new HashSet<Integer>();
    }

    public void addLike(int userId){
        likes.add(userId);
    }

    public void removeLike(int userId){
        likes.remove(userId);
    }
}