package epicode.u2w2d5BEdispositivi.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record NewDeviceDTO(
        @NotEmpty(message = "type is required! (es: computer, smartphone...)")
        String type,
        @NotNull(message = "Availability is required! 0: Available, 1: In Maintain, 2: In use, 3: Removed")
        @Min(0)
        @Max(3)
        Integer availability,
        Long user
) {
}
