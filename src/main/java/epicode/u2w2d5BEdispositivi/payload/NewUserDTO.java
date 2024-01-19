package epicode.u2w2d5BEdispositivi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "user name required!")
        @Size(min = 3, max = 30, message = "Name must contain around 3 to 30 charachters!")
        String username,
        @NotEmpty(message = "first name required!")
        @Size(min = 3, max = 30, message = "Name must contain around 3 to 30 charachters!")
        String firstName,
        @NotEmpty(message = "last name required!")
        @Size(min = 3, max = 30, message = "Name must contain around 3 to 30 charachters!")
        String lastName,
        @Email
        @NotEmpty(message = "email required!")
        String email
) {
}
