package main_package.entity;

import jakarta.persistence.Entity;

@Entity
public record UserData(String name, Long age) {
}
