package main_package.request;
//import jakarta.validation.constraints.NotNull;
import jakarta.annotation.Nonnull;

public record BookCreateRequest(@Nonnull String name, @Nonnull Long pages, @Nonnull String author) {
}
