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
public class Film {
    private int id;
    @NotBlank
    @NonNull
    private String name;
    @Size(max = 200)
    private String description;
    @Past
    private LocalDate releaseDate;
    @Positive
    private Long duration;
    private int like;
    private Set<Integer> likes;
}