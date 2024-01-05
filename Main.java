import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int availableCopies;

    public Book(String title, String author, int availableCopies) {
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        } else {
            System.out.println("No available copies of '" + title + "' by " + author + " at the moment.");
        }
    }

    public void increaseAvailableCopies() {
        availableCopies++;
    }
}

class User {
    private String username;
    private String name;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private List<Book> books;
    private Map<String, User> users;

    public Library() {
        books = new ArrayList<>();
        users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public User findUserByUsername(String username) {
        return users.get(username);
    }

    public void borrowBook(String title, String username) {
        Book book = findBookByTitle(title);
        User user = findUserByUsername(username);

        if (book != null && user != null) {
            book.decreaseAvailableCopies();
            System.out.println(user.getName() + " has successfully borrowed '" + book.getTitle() + "'.");
        } else {
            System.out.println("Book or user not found.");
        }
    }

    public void returnBook(String title, String username) {
        Book book = findBookByTitle(title);
        User user = findUserByUsername(username);

        if (book != null && user != null) {
            book.increaseAvailableCopies();
            System.out.println(user.getName() + " has successfully returned '" + book.getTitle() + "'.");
        } else {
            System.out.println("Book or user not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", 5));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 3));
        // Add users
        library.addUser(new User("john_doe", "John Doe"));
        library.addUser(new User("jane_smith", "Jane Smith"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Borrow a Book");
            System.out.println("2. Return a Book");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the book title: ");
                    String borrowTitle = scanner.nextLine();
                    System.out.print("Enter your username: ");
                    String borrowUsername = scanner.nextLine();
                    library.borrowBook(borrowTitle, borrowUsername);
                    break;
                case 2:
                    System.out.print("Enter the book title: ");
                    String returnTitle = scanner.nextLine();
                    System.out.print("Enter your username: ");
                    String returnUsername = scanner.nextLine();
                    library.returnBook(returnTitle, returnUsername);
                    break;
                case 3:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}