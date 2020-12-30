package com.oay.service;

import com.oay.entity.Books;

import java.util.List;

/*********************************************************
 * @Package: com.oay.service
 * @ClassName: BookMapper.java
 * @Description：描述
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-11-30
 *********************************************************/
public interface BookService {
    //  增加一本书
    int addBook(Books books);

    //  删除一本书
    int deleteBook(int id);

    //  修改一本书
    Books updateBook(Books books);

    //  查询一本书
    Books queryBook(int id);

    //  查询全部书籍
    List<Books> queryBookAll();
}
