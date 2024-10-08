package com.example.book_service.bookinfoAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
    public class BookController {
//        private static final Logger logger = LoggerFactory.getLogger(BookController.class);
        private final BookLoader bookLoader;
        private final BookClient bookClient;

//        public BookController(BookLoader bookLoader) {
//
//            this.bookLoader = bookLoader;
//        }
        public BookController(BookLoader bookLoader,BookClient bookClient) {

            this.bookLoader = bookLoader;
            this.bookClient = bookClient;
        }

//    @GetMapping("/load-books")
//    public ResponseEntity<BookInfoEntity> loadBookByIsbn(@RequestParam("isbn") String isbn) {
//        try {
//            long isbnLong = Long.parseLong(isbn);
//            List<BookInfoEntity> books = bookLoader.loadBooks("/share_best_books_data.json");
//            for (BookInfoEntity book : books) {
//                if (book.getIsbnThirteenNo() == isbnLong) {
//                    return ResponseEntity.ok(book);
//                }
//            }
//        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().build();  // 잘못된 ISBN이 전달된 경우
//        }
//        return ResponseEntity.notFound().build();
//    }

        @GetMapping("/load-books")
        public ResponseEntity<BookInfoEntity> loadBookByIsbn(@RequestParam("isbn") Long isbn) {

                // IOException 제거
                List<BookInfoEntity> books = bookLoader.loadBooks("/share_best_books_data.json");
                for (BookInfoEntity book : books) {
                    // ISBN을 문자열로 변환하여 비교
                    if (String.valueOf(book.getIsbnThirteenNo()).equals(String.valueOf(isbn))) {
                        return ResponseEntity.ok(book);
                    }
                }
                return ResponseEntity.notFound().build();
        }
        @GetMapping("/search-books")
        public Mono<ApiResponse> searchBooks(
                @RequestParam("type") String type,
                @RequestParam("value") String value) {
            return bookClient.searchBooks(type, value);
        }

    }



