import java.util.Scanner;

class Library {

    String[] books;
    boolean[] borrowed;
    String[] history;

    int historyCount = 0;

    // Constructor
    Library(String[] books) {

        this.books = books;
        borrowed = new boolean[books.length];
        history = new String[20];
    }

    // Show all books
    void showBooks() {

        System.out.println("\n===== LIBRARY BOOKS =====");

        for (int i = 0; i < books.length; i++) {

            if (borrowed[i]) {

                System.out.println(
                    (i + 1) + ". " + books[i] + " - Borrowed"
                );

                continue;
            }

            System.out.println(
                (i + 1) + ". " + books[i] + " - Available"
            );
        }
    }

    // Borrow book
    void borrowBook(int bookNumber) {

        if (bookNumber < 1 || bookNumber > books.length) {

            System.out.println("Invalid book number!");
            return;
        }

        int index = bookNumber - 1;

        if (borrowed[index]) {

            System.out.println(
                "Sorry! This book is already borrowed."
            );

            return;
        }

        borrowed[index] = true;

        addHistory(
            "Borrowed: " + books[index]
        );

        System.out.println(
            "You borrowed: " + books[index]
        );
    }

    // Return book
    void returnBook(int bookNumber) {

        if (bookNumber < 1 || bookNumber > books.length) {

            System.out.println("Invalid book number!");
            return;
        }

        int index = bookNumber - 1;

        if (!borrowed[index]) {

            System.out.println(
                "This book was not borrowed."
            );

            return;
        }

        borrowed[index] = false;

        addHistory(
            "Returned: " + books[index]
        );

        System.out.println(
            "Book returned successfully."
        );
    }

    // Search book
    void searchBook(String keyword) {

        boolean found = false;

        System.out.println("\n===== SEARCH RESULT =====");

        for (int i = 0; i < books.length; i++) {

            if (books[i].toLowerCase()
                .contains(keyword.toLowerCase())) {

                System.out.println(
                    (i + 1) + ". " + books[i]
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println("Book not found.");
        }
    }

    // Add history
    void addHistory(String text) {

        if (historyCount < history.length) {

            history[historyCount] = text;
            historyCount++;
        }
    }

    // Show history
    void showHistory() {

        System.out.println("\n===== BORROW HISTORY =====");

        if (historyCount == 0) {

            System.out.println("No history found.");
            return;
        }

        for (int i = 0; i < historyCount; i++) {

            System.out.println(
                (i + 1) + ". " + history[i]
            );
        }
    }
}

public class SmartLibrary {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Book list
        String[] books = {
            "Java Programming",
            "Clean Code",
            "The Alchemist",
            "Data Structures",
            "Algorithms",
            "Computer Networks",
            "Database Management"
        };

        // Create object
        Library library = new Library(books);

        // Main menu
        while (true) {

            System.out.println("\n==============================");
            System.out.println("       SMART LIBRARY");
            System.out.println("==============================");

            System.out.println("1. Show All Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book");
            System.out.println("5. Borrow History");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    library.showBooks();

                    break;

                case 2:

                    System.out.print(
                        "Enter book number: "
                    );

                    int borrow = sc.nextInt();

                    library.borrowBook(borrow);

                    break;

                case 3:

                    System.out.print(
                        "Enter book number: "
                    );

                    int returnBook = sc.nextInt();

                    library.returnBook(returnBook);

                    break;

                case 4:

                    System.out.print(
                        "Enter book name to search: "
                    );

                    String keyword = sc.next();

                    library.searchBook(keyword);

                    break;

                case 5:

                    library.showHistory();

                    break;

                case 0:

                    System.out.println(
                        "Thank you for using Smart Library!"
                    );

                    sc.close();

                    return;

                default:

                    System.out.println(
                        "Invalid choice! Try again."
                    );
            }
        }
    }
}