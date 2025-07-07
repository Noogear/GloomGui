package cn.gloomGui.util;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;

public class StringUtil {

    public static String normalizeUpper(String string) {
        return string.toUpperCase(Locale.ROOT).replace(" ", "_");
    }

    public static Optional<BigDecimal> parseNumber(@NotNull String input) {
        try {
            return Optional.of(new BigDecimal(input));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> parseInteger(@NotNull String input) {
        try {
            return Optional.of(Integer.parseInt(input.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Boolean> parseBoolean(@NotNull String input) {
        try {
            return Optional.of(Boolean.parseBoolean(input.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<URL> parseURL(@NotNull String input) {
        try {
            return Optional.of(URI.create(input).toURL());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
