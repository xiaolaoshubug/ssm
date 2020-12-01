package com.oay.mapper;

import com.oay.entity.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*********************************************************
 * @Package: com.oay.mapper
 * @ClassName: BookMapper.java
 * @Description������
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-11-30
 *********************************************************/
public interface BookMapper {

    //  ����һ����
    int addBook(Books books);

    //  ɾ��һ����
    int deleteBook(@Param("bookID") int id);

    //  �޸�һ����
    int updateBook(Books books);

    //  ��ѯһ����
    Books queryBook(@Param("bookID") int id);

    //  ��ѯȫ���鼮
    List<Books> queryBookAll();
}
