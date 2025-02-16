package main_package.request;

import jakarta.annotation.Nonnull;

public record UserCreateRequest(@Nonnull String name,@Nonnull Long age) {
}
