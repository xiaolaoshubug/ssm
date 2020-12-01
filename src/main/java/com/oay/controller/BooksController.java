package com.oay.controller;

import com.oay.entity.Books;
import com.oay.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/*********************************************************
 * @Package: com.oay.controller
 * @ClassName: BooksController.java
 * @Description��  �鼮
 * -----------------------------------
 * @author��ouay
 * @Version��v1.0
 * @Date: 2020-11-30
 *********************************************************/
@Controller
@RequestMapping("books")
public class BooksController {

    //  ע��service
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    //  ��ѯ����
    @RequestMapping(value = "/allBooks")
    public String queryBooksAll(Model model) {
        List<Books> books = bookService.queryBookAll();
        model.addAttribute("books", books);
        return "allBooks";
    }

    //  �����ض�����ҳ�棬����ҳ��
    @RequestMapping(value = "/toAddBook")
    public String addBook() {
        return "addBook";
    }

    //  ������ɺ��ض��򵽲�ѯҳ��
    @RequestMapping(value = "/addBook",method = RequestMethod.POST)
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        //	�ض���
        return "redirect:/books/allBooks";
    }

    //  �޸�֮ǰ�����ݷ�װ����
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBook(id);
        model.addAttribute("book", books);
        return "updateBook";
    }

    //  ��ȡ�޸�����
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(Model model, Books book) {
        bookService.updateBook(book);
        Books books = bookService.queryBook(book.getBookID());
        model.addAttribute("books", books);
        return "redirect:/books/allBooks";
    }

    //  ɾ��id
    @RequestMapping("/del/{bookId}")
    public String del(@PathVariable("bookId") int id) {
        bookService.deleteBook(id);
        return "redirect:/books/allBooks";
    }

}
