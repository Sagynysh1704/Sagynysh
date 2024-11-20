//java
//LibraryManagementSystem.java
import java.util.ArrayList;
import java.util.List;

class Book {
    String title;
    String author;
    String ISBN;
    boolean isAvailable;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Reader {
    String name;
    List<Book> rentedBooks;

    public Reader(String name) {
        this.name = name;
        this.rentedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void rentBook(Book book) {
        if (book.isAvailable()) {
            rentedBooks.add(book);
            book.setAvailable(false);
            System.out.println(name + " арендовал книгу: " + book.getTitle());
        } else {
            System.out.println("Книга " + book.getTitle() + " недоступна для аренды.");
        }
    }

    public void returnBook(Book book) {
        if (rentedBooks.contains(book)) {
            rentedBooks.remove(book);
            book.setAvailable(true);
            System.out.println(name + " вернул книгу: " + book.getTitle());
        } else {
            System.out.println(name + " не арендовал книгу: " + book.getTitle());
        }
    }
}

class Librarian {
    String name;

    public Librarian(String name) {
        this.name = name;
    }

    public void manageBooks(Library library, Book book, boolean addBook) {
        if (addBook) {
            library.addBook(book);
            System.out.println(name + " добавил книгу: " + book.getTitle());
        } else {
            library.removeBook(book);
            System.out.println(name + " удалил книгу: " + book.getTitle());
        }
    }
}

class Library {
    List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void displayAvailableBooks() {
        System.out.println("Доступные книги в библиотеке:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("Название: " + book.getTitle() + ", Автор: " + book.getAuthor() + ", ISBN: " + book.getISBN());
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {

        Library library = new Library();
        Book book1 = new Book("Абай жолы", "Мухтар Ауэзов", "123456");
        Book book2 = new Book("Махаббат қызық мол жылдар", "Әзілхан Нуршайыков", "789101");

        Librarian librarian = new Librarian("Сағыныш");
        librarian.manageBooks(library, book1, true);
        librarian.manageBooks(library, book2, true);

        Reader reader = new Reader("Совет");

        library.displayAvailableBooks();

        reader.rentBook(book1);

        library.displayAvailableBooks();

        reader.returnBook(book1);

        library.displayAvailableBooks();

        librarian.manageBooks(library, book2, false);

        library.displayAvailableBooks();
    }
}
