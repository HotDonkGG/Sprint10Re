package filmorateapp.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "\\S+")
    private String login;
    @NonNull
    @Pattern(regexp = "\\S+")
    private String name;
    @PastOrPresent
    private LocalDate birthday;
    private Set<Long> friends;
}