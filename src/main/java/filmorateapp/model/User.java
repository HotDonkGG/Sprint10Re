package filmorateapp.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class User {
    @NonNull
    private Long id;
    @Email
    @NonNull
    private String email;
    @NonNull
    private String login;
    @NonNull
    private String name;
    @Past
    private LocalDate birthday;
    private Set<Long> friends;
}