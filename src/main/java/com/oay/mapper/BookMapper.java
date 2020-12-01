package com.oay.mapper;

import com.oay.entity.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*********************************************************
 * @Package: com.oay.mapper
 * @ClassName: BookMapper.java
 * @Description：描述
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-11-30
 *********************************************************/
public interface BookMapper {

    //  增加一本书
    int addBook(Books books);

    //  删除一本书
    int deleteBook(@Param("bookID") int id);

    //  修改一本书
    int updateBook(Books books);

    //  查询一本书
    Books queryBook(@Param("bookID") int id);

    //  查询全部书籍
    List<Books> queryBookAll();
}
