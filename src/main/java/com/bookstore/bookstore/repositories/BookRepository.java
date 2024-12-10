package com.bookstore.bookstore.repositories;

import com.bookstore.bookstore.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {

    BookModel findBookModelByTitle(String title);

    @Query(value = "SELECT * FROM tb_book WHERE id_publisher = :id", nativeQuery = true)
    List<BookModel> findBooksByPublisherId(@Param("id")UUID id);
}
