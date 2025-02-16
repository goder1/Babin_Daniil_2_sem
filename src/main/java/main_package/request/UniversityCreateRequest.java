package main_package.request;

import jakarta.annotation.Nonnull;

public record UniversityCreateRequest(@Nonnull String name, @Nonnull Long students,@Nonnull String location) {
}
