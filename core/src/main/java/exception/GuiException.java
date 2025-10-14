package exception;

import org.jetbrains.annotations.NotNull;

public final class GuiException extends RuntimeException {

    public GuiException(final @NotNull String message) {
        super(message);
    }

    public GuiException(final @NotNull String message, final @NotNull Throwable cause) {
        super(message, cause);
    }

}
