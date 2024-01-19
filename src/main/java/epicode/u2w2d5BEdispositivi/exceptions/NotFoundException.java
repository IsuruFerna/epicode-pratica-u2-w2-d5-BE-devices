package epicode.u2w2d5BEdispositivi.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Long id) {
        super(id + " didn't find!");
    }
}
