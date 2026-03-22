package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BookService bookService) {
        return args -> {

            if (bookService.list().isEmpty()) {

                Book b1 = new Book();
                b1.setTitle("Java Programming");
                b1.setAuthor("James Gosling");
                b1.setIsbn("111");
                b1.setTotalCopies(5);
                b1.setAvailableCopies(5);
                bookService.create(b1);

                Book b2 = new Book();
                b2.setTitle("Python Basics");
                b2.setAuthor("Guido van Rossum");
                b2.setIsbn("222");
                b2.setTotalCopies(3);
                b2.setAvailableCopies(3);
                bookService.create(b2);

                Book b3 = new Book();
                b3.setTitle("C++ Guide");
                b3.setAuthor("Bjarne Stroustrup");
                b3.setIsbn("333");
                b3.setTotalCopies(4);
                b3.setAvailableCopies(4);
                bookService.create(b3);

                Book b4 = new Book();
                b4.setTitle("Data Structures");
                b4.setAuthor("Mark Allen Weiss");
                b4.setIsbn("444");
                b4.setTotalCopies(6);
                b4.setAvailableCopies(6);
                bookService.create(b4);

                Book b5 = new Book();
                b5.setTitle("Algorithms");
                b5.setAuthor("Thomas Cormen");
                b5.setIsbn("555");
                b5.setTotalCopies(5);
                b5.setAvailableCopies(5);
                bookService.create(b5);

                Book b6 = new Book();
                b6.setTitle("Operating Systems");
                b6.setAuthor("Abraham Silberschatz");
                b6.setIsbn("666");
                b6.setTotalCopies(4);
                b6.setAvailableCopies(4);
                bookService.create(b6);

                Book b7 = new Book();
                b7.setTitle("Database Systems");
                b7.setAuthor("Ramakrishnan");
                b7.setIsbn("777");
                b7.setTotalCopies(5);
                b7.setAvailableCopies(5);
                bookService.create(b7);

                Book b8 = new Book();
                b8.setTitle("Computer Networks");
                b8.setAuthor("Andrew Tanenbaum");
                b8.setIsbn("888");
                b8.setTotalCopies(3);
                b8.setAvailableCopies(3);
                bookService.create(b8);

                Book b9 = new Book();
                b9.setTitle("Artificial Intelligence");
                b9.setAuthor("Stuart Russell");
                b9.setIsbn("999");
                b9.setTotalCopies(6);
                b9.setAvailableCopies(6);
                bookService.create(b9);

                Book b10 = new Book();
                b10.setTitle("Machine Learning");
                b10.setAuthor("Tom Mitchell");
                b10.setIsbn("1010");
                b10.setTotalCopies(5);
                b10.setAvailableCopies(5);
                bookService.create(b10);

                Book b11 = new Book();
                b11.setTitle("Compiler Design");
                b11.setAuthor("Aho");
                b11.setIsbn("1111");
                b11.setTotalCopies(4);
                b11.setAvailableCopies(4);
                bookService.create(b11);

                Book b12 = new Book();
                b12.setTitle("Software Engineering");
                b12.setAuthor("Ian Sommerville");
                b12.setIsbn("1212");
                b12.setTotalCopies(6);
                b12.setAvailableCopies(6);
                bookService.create(b12);

                Book b13 = new Book();
                b13.setTitle("Computer Graphics");
                b13.setAuthor("Hearn");
                b13.setIsbn("1313");
                b13.setTotalCopies(5);
                b13.setAvailableCopies(5);
                bookService.create(b13);

                Book b14 = new Book();
                b14.setTitle("Cyber Security");
                b14.setAuthor("William Stallings");
                b14.setIsbn("1414");
                b14.setTotalCopies(4);
                b14.setAvailableCopies(4);
                bookService.create(b14);

                Book b15 = new Book();
                b15.setTitle("Cloud Computing");
                b15.setAuthor("Rajkumar");
                b15.setIsbn("1515");
                b15.setTotalCopies(5);
                b15.setAvailableCopies(5);
                bookService.create(b15);

                System.out.println("15 Sample books added!");
            }
        };
    }
}