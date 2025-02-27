package main_package.entity;

import java.util.ArrayList;

public record User(Long id, String name, Long age, ArrayList<BookData> books, ArrayList<CourseData> courses, ArrayList<UniversityData> universities) {
}
