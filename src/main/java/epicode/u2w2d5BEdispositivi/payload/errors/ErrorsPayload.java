package epicode.u2w2d5BEdispositivi.payload.errors;

import java.util.Date;

public record ErrorsPayload(
        String message,
        Date timestamp
) {
    public ErrorsPayload(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
