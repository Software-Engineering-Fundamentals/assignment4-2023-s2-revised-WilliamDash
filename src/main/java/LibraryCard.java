
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student 
 */
public class LibraryCard {
    /**
     * Card id 
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private LocalDate IssueDate;

    /**
     * Expiry Date of the Card
     */
    private LocalDate ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     *  Details about the cardholder
     */
    private Student student;




    public LibraryCard(Student student, LocalDate IssueDate, LocalDate ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
	   this.ExpiryDate = ExpiryDate;
	   this.ID = ID;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }


    public LocalDate getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(LocalDate IssueDate) {
        this.IssueDate = IssueDate;
    }

    public LocalDate getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(LocalDate ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    
    public List<Book> getBooks() {
        return borrowed;
    }

    

    /**
     * Issue a new book
     * @param Book: book to borrow 
     * @return true if the book is successfully borrowed, false otherwise
     */

    public boolean issueBook(Book book){
        // Get number of books borrowed
        int NumBooks = this.borrowed.size();
        System.out.println("BOOKS: "+NumBooks);
        // Check if greater than 4
        if (NumBooks >= 4) {
            return false;
        }
        // Check if book has already been issued
        if (this.borrowed.contains(book)) {
            return false;
        }
        // Check if library card is still valid (im assuming this is date)
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(this.ExpiryDate)) {
            return false;
        }
        // Check if book is still available
        boolean bookStatus = book.getStatus();
        if (bookStatus == false) {
            return false;
        }
        // Check for pending fine
        if (fine > 0) {
            return false;
        }
        // Issue the book
        // Check if high or low demand
        int demand = book.getDemand();
        if (demand <= 0) {
            // Low demand
            book.setDays(15);
        } else if (demand >= 1) {
            // High demand
            book.setDays(3);
        }
        book.setDemand(false);
        this.borrowed.add(book);
        return true;
    }

}
