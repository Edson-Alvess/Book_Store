package com.bookstore.bookstore.services;


import com.bookstore.bookstore.Dtos.BookRecordDto;
import com.bookstore.bookstore.models.BookModel;
import com.bookstore.bookstore.models.ReviewModel;
import com.bookstore.bookstore.repositories.AuthorRepository;
import com.bookstore.bookstore.repositories.BookRepository;
import com.bookstore.bookstore.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<BookModel> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public BookModel saveBook (BookRecordDto bookRecordDto){
        BookModel book = new BookModel();
        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.IdPublisher()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDto.IdsAuthor()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }
}
