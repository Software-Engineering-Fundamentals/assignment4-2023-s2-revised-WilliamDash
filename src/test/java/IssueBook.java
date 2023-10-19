
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {

    // Testing objects
    private Student testStudent;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private LibraryCard testCard;
    private Book testBook;


    @BeforeEach
    public void setting() {
        testStudent = new Student("Dave", 10);
        issueDate = LocalDate.of(2023, 02, 11);
        expiryDate = LocalDate.of(2024, 12, 10);
        testCard = new LibraryCard(testStudent, issueDate, expiryDate, 10);
        testBook = new Book(11, "Dave's book", 0);
    }

    @Test
    public void True_ExpectedInputs() {
        // Used as a control to ensure the program works with excpeted inputs
        assertTrue(testCard.issueBook(testBook));
        // Check if book is added to borrowed
        List<Book> testBorrowed = new ArrayList<Book>();
        testBorrowed.add(testBook);
        assertEquals(testBorrowed, testCard.getBooks());
    }

    @Test
    public void True_FourIssuedBooks() {
        // Create more books objects
        Book testBook2 = new Book(12, "Dave's book 2", 0);
        Book testBook3 = new Book(13, "Dave's book 3", 0);
        Book testBook4 = new Book(14, "Dave's book 4", 0);
        // Issue books and check if all succeeded
        assertTrue(testCard.issueBook(testBook));
        assertTrue(testCard.issueBook(testBook2));
        assertTrue(testCard.issueBook(testBook3));
        assertTrue(testCard.issueBook(testBook4));
    }

    @Test
    public void False_FiveIssuedBooks() {
        // Create more books objects
        Book testBook2 = new Book(12, "Dave's book 2", 0);
        Book testBook3 = new Book(13, "Dave's book 3", 0);
        Book testBook4 = new Book(14, "Dave's book 4", 0);
        Book testBook5 = new Book(15, "Dave's book 5", 0);
        // Issue all books and check if all succeeded
        assertTrue(testCard.issueBook(testBook));
        assertTrue(testCard.issueBook(testBook2));
        assertTrue(testCard.issueBook(testBook3));
        assertTrue(testCard.issueBook(testBook4));
        assertFalse(testCard.issueBook(testBook5));
    }

    @Test
    public void False_BookAlreadyBorrowed() {
        // Issue the same book twice
        assertTrue(testCard.issueBook(testBook));
        assertFalse(testCard.issueBook(testBook));
    }

    @Test
    public void False_ExpiryPassed() {
        // Create new expired localdate object for expiry
        LocalDate testExpiryDate = LocalDate.of(2022, 01, 01);
        testCard.setExpiryDate(testExpiryDate);
        assertFalse(testCard.issueBook(testBook));
    }

    @Test
    public void True_ExpiryToday() {
        // Create new localdate object for today
        LocalDate testExpiryDate = LocalDate.now();
        testCard.setExpiryDate(testExpiryDate);
        assertTrue(testCard.issueBook(testBook));
    }

    @Test
    public void False_BookStatusFalse() {
        testBook.setDemand(false);
        assertFalse(testCard.issueBook(testBook));
    }

    @Test
    public void False_LibraryCardFineOverZero() {
        testCard.setFine(1);
        assertFalse(testCard.issueBook(testBook));
    }

    @Test
    public void True_LibraryCardFineUnderZero() {
        testCard.setFine(-1);
        assertTrue(testCard.issueBook(testBook));
    }

    @Test
    public void ThreeDays_BookDemandAtOne() {
        // Change testbook demand and check days
        testBook.setDemand(1);
        testCard.issueBook(testBook);
        assertEquals(3,testBook.days());
    }

    @Test
    public void FifteenDays_BookDemandAtZero() {
        // Change testbook demand and check days
        testBook.setDemand(0);
        testCard.issueBook(testBook);
        assertEquals(15,testBook.days());
    }

    @Test
    public void ThreeDays_BookDemandMoreThanOne() {
        // Change testbook demand and check days
        testBook.setDemand(2);
        testCard.issueBook(testBook);
        assertEquals(3,testBook.days());
    }

    @Test
    public void FifteenDays_BookDemandLessThanZero() {
        // Change testbook demand and check days
        testBook.setDemand(-1);
        testCard.issueBook(testBook);
        assertEquals(15,testBook.days());
    }

}
