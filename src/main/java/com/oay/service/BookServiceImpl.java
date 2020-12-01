package com.oay.service;

import com.oay.entity.Books;
import com.oay.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*********************************************************
 * @Package: com.oay.service
 * @ClassName: BookServiceImpl.java
 * @Description£∫√Ë ˆ
 * -----------------------------------
 * @author£∫ouay
 * @Version£∫v1.0
 * @Date: 2020-11-30
 *********************************************************/
@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    public int deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    public Books queryBook(int id) {
        return bookMapper.queryBook(id);
    }

    public List<Books> queryBookAll() {
        return bookMapper.queryBookAll();
    }
}
