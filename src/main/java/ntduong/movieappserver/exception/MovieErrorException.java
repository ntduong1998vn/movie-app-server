package ntduong.movieappserver.exception;

public class MovieErrorException extends RuntimeException {

    public MovieErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieErrorException(Throwable cause) {
        super(cause);
    }

    protected MovieErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
