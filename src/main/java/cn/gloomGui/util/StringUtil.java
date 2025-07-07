package cn.gloomGui.util;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.util.Base64;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

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

    public static Optional<UUID> parseUUID(@NotNull String input) {
        try {
            return Optional.of(UUID.fromString(input));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<byte[]> parseBase64(@NotNull String input) {
        try {
            return Optional.of(Base64.getDecoder().decode(input));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
