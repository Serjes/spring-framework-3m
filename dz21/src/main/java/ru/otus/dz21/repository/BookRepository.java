package ru.otus.dz21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import ru.otus.dz21.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    //    @PostFilter("hasPermission(filterObject.id, 'READ')")
//    @PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, admin)")
//    @PostFilter(
//            value = "hasPermission(filterObject, 'READ')"
////            filterTarget = "Book"
//    )
//    @PostAuthorize("hasPermission(returnObject, 'READ')")
    @PostFilter("hasPermission(filterObject, 'READ')")
//    @Secured({"ROLE_ADMIN", "ROLE_ADVANCED_USER"})
    List<Book> findAll();

    //    @PostAuthorize("hasPermission(returnObject., 'READ')")
//    @PreFilter(
//            value = "hasPermission(filterObject, 'READ')",
//            filterTarget = "Integer"
//    )
    Optional<Book> findById(Integer id);

    void deleteById(Integer id);

    //    @PreAuthorize("hasPermission(#entityBook, 'WRITE')")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(#book, 'write')")
    @PreAuthorize("hasPermission(#book, 'write')")
    Book save(@Param("book") Book book);

}
