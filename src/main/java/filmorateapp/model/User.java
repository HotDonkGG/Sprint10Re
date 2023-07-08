package filmorateapp.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@ToString
@EqualsAndHashCode(of = {"id"})
@Getter
public class User {
    private static int identification = 0;
    @Positive
    private int id;
    @Email
    private String email;
    @NotBlank
    private String login;
    private String name;
    @Past
    @NotBlank
    private LocalDate birthday;
    private Set<Integer> friends;

    public void generateAndSetId() {
        setId(++identification);
    }

    public void generateOfSetFriends() {
        this.friends = new HashSet<Integer>();
    }

    public void addFriend(int friendId) {
        friends.add(friendId);
    }
    public void removeFriend(int friendId) {
        friends.remove(friendId);
    }
}
