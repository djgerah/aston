package module_1;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Student> students = List.of(
            new Student("Richard", List.of(
                new Book("Carrie", 199, 1974),
                new Book("The Shining", 447, 1977),
                new Book("It", 1138, 1986),
                new Book("Misery", 320, 1987),
                new Book("11/22/63", 849, 2011))),
            new Student("john", List.of(
                new Book("The Stand", 1153, 1978),
                new Book("Pet Sematary", 374, 1983),
                new Book("The Green Mile", 400, 1996),
                new Book("Doctor Sleep", 531, 2013),
                new Book("Billy Summers", 528, 2021))));

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted((lhs, rhs) -> Integer.compare(lhs.getYear(), rhs.getYear()))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                // .map(book -> book.getYear())
                .findFirst()
                .ifPresentOrElse(book -> System.out.println("First found book is " + book.getTitle() + " released in " + book.getYear() + "."), () -> System.out.println("No books released after 2000."));
    }
}
