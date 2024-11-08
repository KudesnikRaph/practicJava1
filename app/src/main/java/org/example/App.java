package org.example;
import java.sql.Date;
import java.sql.SQLException;
public class App {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        try {
            dbManager.connect();
            dbManager.addBook("Learning Java", "Daniel Leuck", Date.valueOf("2020-06-21"), "9781449372477");
            dbManager.addBook("Java servlet programming", "Jason Hunter", Date.valueOf("2001-05-08"), "9780596000400");
            System.out.println("List of all books:");
            dbManager.getAllBooks().forEach(book -> System.out.println(" - " + book.getTitle()));
            Book foundBook = dbManager.findBookByTitle("Learning Java");
            if (foundBook != null) {
                System.out.println("Book found: " + foundBook.getTitle());
            }
            if (foundBook != null) {
                dbManager.deleteBook(foundBook.getId());
                System.out.println("Book removed: " + foundBook.getTitle());
            }
            dbManager.addReader("User1", "user1@example.com");
            dbManager.addReader("User2", "user2@example.com");
            System.out.println("\nList of all readers:");
            dbManager.getAllReaders().forEach(reader -> System.out.println(" - " + reader.getName()));
            Reader foundReader = dbManager.findReaderByEmail("user1@example.com");
            if (foundReader != null) {
                System.out.println("Reader found: " + foundReader.getName());
            }
            if (foundReader != null) {
                dbManager.deleteReader(foundReader.getId());
                System.out.println("Reader removed: " + foundReader.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbManager.disconnect();
        }
    }
}