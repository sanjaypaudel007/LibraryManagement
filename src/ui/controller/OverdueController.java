package ui.controller;

import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class OverdueController {
	@FXML
	private TextField txtIsbn;
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblIsbn;
	@FXML
	private TableView<BookCopy> tblViewBookCopies;
	@FXML
	private TableColumn<BookCopy, String> columnCopyNumber;
	@FXML
	private TableColumn<BookCopy, String> columnMember;
	@FXML
	private TableColumn<BookCopy, String> columnDueDate;
	@FXML
	private Label lblMsg;
	private ObservableList<Book> bookDetails;

	@FXML
	protected void getOverdue(ActionEvent event) {
		SystemController sc = new SystemController();
		Book book = sc.searchBook(txtIsbn.getText());
		if (book == null) {
			try {
				throw new LibrarySystemException(
						"No book with isbn " + txtIsbn.getText() + " is in the library collection!");
			} catch (LibrarySystemException e) {
				lblTitle.setText(e.getMessage());
				lblTitle.setTextFill(Color.web("RED"));
			}
		}

		lblTitle.setText("");
		lblTitle.setTextFill(Color.web("RED"));

		BookCopy[] copies = book.getCopies();
		for (BookCopy copy : copies)

		{

			if (copy.isAvailable()) {
				// TableColumn<TestBed, String> name = new TableColumn<>();
				// name.setCellValueFactory(c-> new
				// SimpleStringProperty(c.getValue().getTestBedName()));
				System.out.println("" + copy.getCopyNum());
				columnCopyNumber.setCellValueFactory(cellData -> new SimpleStringProperty("" + copy.getCopyNum()));

			} else {
				columnCopyNumber.setCellValueFactory(cellData -> new SimpleStringProperty("" + copy.getCopyNum()));

			}
			// tblViewBookCopies.getColumns().addAll(columnCopyNumber);
		}

	}

}
