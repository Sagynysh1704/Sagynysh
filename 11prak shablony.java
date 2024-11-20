//11prak
//java

//LibrarySystem.java
public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book("Абай жолы", "Мухтар Ауэзов", "История", "123456");
        Book book2 = new Book("Махаббат қызық мол жылдар", "Әзілхан Нуршайыков", "Романтика", "654321");

        library.addBook(book1);
        library.addBook(book2);

        Reader reader1 = new Reader("Сагыныш", "Совет", "R123");
        Librarian librarian = new Librarian("Жансая");

        librarian.issueBook(book1, reader1);
        librarian.acceptReturnedBook(book1, reader1);

        Report report = new Report();
        report.generatePopularBooksReport(library);
    }
}



//Book.java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private boolean isAvailable;

    public Book(String title, String author, String genre, String ISBN) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    public void changeAvailabilityStatus(boolean status) {
        isAvailable = status;
    }

    public String getBookInfo() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", ISBN: " + ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}


//Report.java
import java.util.List;

public class Report {
    public void generatePopularBooksReport(Library library) {
        System.out.println("Popular Books Report:");
        for (Book book : library.getBooks()) {
            System.out.println(book.getBookInfo());
        }
    }

    public void generateReaderActivityReport(List<Reader> readers) {
        System.out.println("Reader Activity Report:");
        for (Reader reader : readers) {
        }
    }
}


//Library.java
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getBookInfo().contains(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }
}


//Loan.java
import java.util.Date;

public class Loan {
    private Book book;
    private Reader reader;
    private Date loanDate;
    private Date returnDate;

    public Loan(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.loanDate = new Date();
        this.returnDate = null;
    }

    public void issueLoan() {
        System.out.println("Loan issued for book: " + book.getBookInfo() + " to " + reader);
    }

    public void returnBook() {
        this.returnDate = new Date();
        System.out.println("Book returned: " + book.getBookInfo());
    }
}


//Librarian.java
public class Librarian implements User {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    @Override
    public void register() {
    }

    @Override
    public void login() {
        
    }

    public void issueBook(Book book, Reader reader) {
        if (book.isAvailable()) {
            reader.borrowBook(book);
            System.out.println(name + " issued the book: " + book.getBookInfo());
        }
    }

    public void acceptReturnedBook(Book book, Reader reader) {
        reader.returnBook(book);
        System.out.println(name + " accepted the return of book: " + book.getBookInfo());
    }
}




//Reader.java
public class Reader implements User {
    private String name;
    private String surname;
    private String ticketNumber;

    public Reader(String name, String surname, String ticketNumber) {
        this.name = name;
        this.surname = surname;
        this.ticketNumber = ticketNumber;
    }

    @Override
    public void register() {
    }

    @Override
    public void login() {
      
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            System.out.println(name + " borrowed the book: " + book.getBookInfo());
            book.changeAvailabilityStatus(false);
        } else {
            System.out.println("The book is not available.");
        }
    }

    public void returnBook(Book book) {
        System.out.println(name + " returned the book: " + book.getBookInfo());
        book.changeAvailabilityStatus(true);
    }
}


//User.java
public interface User {
    void register();
    void login();
}

