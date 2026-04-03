package com.library.library_management;
import com.library.library_management.model.Book;
import com.library.library_management.model.Borrower;
import com.library.library_management.repository.BookRepository;
import com.library.library_management.repository.BorrowerRepository;
import com.library.library_management.service.BookService;
import com.library.library_management.service.BorrowerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryManagementApplicationTests {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Mock
	private BorrowerRepository borrowerRepository;

	@InjectMocks
	private BorrowerService borrowerService;

	@Test
	void listBooks() {
		//Arrange
		Book book1 = new Book(1L, "Eragon", "Paolini", true, null);
		Book book2 = new Book(2L, "Eragon2", "Paolini", false, null);
		Book book3 = new Book(1L, "Eragon3", "Paolini", true, null);
		when(bookRepository.findByAvailableTrue()).thenReturn(List.of(book1, book3));

		//Act
		bookService.createBook(book1);
		bookService.createBook(book2);
		bookService.createBook(book3);

		List<Book> books = bookService.getAvailableBooks();

		//Assert
		assertNotNull(books);
		assertEquals(2, books.size());
		assertEquals("Eragon", books.get(0).getTitle());
		assertEquals("Eragon3", books.get(1).getTitle());

		//Verify
		verify(bookRepository, times(1)).findByAvailableTrue();
	}

	@Test
	void addBook() {
		//Arrange
		Book book = new Book(1L, "Eragon", "Paolini", true, null);
		when(bookRepository.save(book)).thenReturn(book);

		//Act
		Book createdBook = bookService.createBook(book);

		//Assert
		assertNotNull(createdBook);
		assertEquals("Eragon", createdBook.getTitle());
		assertEquals("Paolini", createdBook.getAuthor());
		assertTrue(createdBook.isAvailable());
		assertNull(createdBook.getBorrower());

		//Verify
		verify(bookRepository, times(1)).save(book);
	}



	@Test
	void borrowBook(){
		//Arrange
		Book book = new Book(1L, "Eragon", "Paolini", true, null);
		Borrower borrower = new Borrower(1L, "John", null);
		when(bookRepository.save(book)).thenReturn(book);
		when(borrowerRepository.save(borrower)).thenReturn(borrower);
		when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
		when(borrowerRepository.findById(borrower.getId())).thenReturn(Optional.of(borrower));

		//Act
		bookService.createBook(book);
		borrowerService.createBorrower(borrower);
		Book bowworedBook = bookService.borrowBook(1L, 1L);

		//Assert
		assertNotNull(bowworedBook);
		assertEquals("Eragon", bowworedBook.getTitle());
		assertEquals("Paolini", bowworedBook.getAuthor());
		assertFalse(bowworedBook.isAvailable());
		assertEquals(borrower, bowworedBook.getBorrower());

		//Verify
		verify(bookRepository, times(2)).save(book); //once for creation, once for saving
		verify(bookRepository, times(1)).findById(book.getId());
		verify(borrowerRepository, times(1)).save(borrower);
		verify(borrowerRepository, times(1)).findById(borrower.getId());
	}

	@Test
	void createBorrower(){
		//Arrange
		Borrower borrower = new Borrower(1L, "John", null);
		when(borrowerRepository.save(borrower)).thenReturn(borrower);

		//Act
		borrowerService.createBorrower(borrower);

		//Assert
		assertNotNull(borrower);
		assertEquals("John", borrower.getName());
		assertEquals(1L, borrower.getId());
		assertNull(borrower.getBooks());

		//Verify
		verify(borrowerRepository, times(1)).save(borrower);
	}

	@Test
	void getBorrower(){
		//Arrange
		Borrower borrower = new Borrower(1L, "John", null);
		when(borrowerRepository.save(borrower)).thenReturn(borrower);
		when(borrowerRepository.findById(borrower.getId())).thenReturn(Optional.of(borrower));

		//Act
		borrowerService.createBorrower(borrower);
		Borrower borrowerSearched = borrowerService.getBorrowerById(borrower.getId());

		//Assert
		assertNotNull(borrowerSearched);
		assertEquals("John", borrowerSearched.getName());
		assertEquals(1L, borrowerSearched.getId());
		assertNull(borrower.getBooks());

		//Verify
		verify(borrowerRepository, times(1)).save(borrower);
		verify(borrowerRepository, times(1)).findById(borrower.getId());
	}

	@Test
	void listBorrowedBooks() {
		// Arrange
		Borrower borrower = new Borrower(1L, "John", new ArrayList<>());
		Book book = new Book(1L, "Eragon", "Paolini", false, borrower);
		borrower.getBooks().add(book);

		when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));

		// Act
		List<Book> books = borrowerService.getBorrowerBooksById(1L);

		// Assert
		assertNotNull(books);
		assertEquals(1, books.size());
		assertEquals(book, books.get(0));

		// Verify
		verify(borrowerRepository, times(1)).findById(1L);
	}
}
