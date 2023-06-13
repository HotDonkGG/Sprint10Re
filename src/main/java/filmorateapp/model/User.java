package filmorateapp.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@ToString
public class User {
    @NonNull
    @Positive
    private Long id;
    @Email
    @NonNull
    private String email;
    @NonNull
    private String login;
    @NonNull
    @NotBlank
    private String name;
    @Past
    private LocalDate birthday;
    private Set<Long> friends;
}