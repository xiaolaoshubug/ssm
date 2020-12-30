package com.oay.service;

import com.oay.entity.Books;

import java.util.List;

/*********************************************************
 * @Package: com.oay.service
 * @ClassName: BookMapper.java
 * @Description������
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-11-30
 *********************************************************/
public interface BookService {
    //  ����һ����
    int addBook(Books books);

    //  ɾ��һ����
    int deleteBook(int id);

    //  �޸�һ����
    Books updateBook(Books books);

    //  ��ѯһ����
    Books queryBook(int id);

    //  ��ѯȫ���鼮
    List<Books> queryBookAll();
}
