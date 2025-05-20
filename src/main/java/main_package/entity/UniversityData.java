package main_package.entity;

import jakarta.persistence.Entity;

@Entity
public record UniversityData(String name, Long students, String location) {
}
