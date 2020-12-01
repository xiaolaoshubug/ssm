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
 * @Description：  书籍
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-11-30
 *********************************************************/
@Controller
@RequestMapping("books")
public class BooksController {

    //  注入service
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    //  查询所以
    @RequestMapping(value = "/allBooks")
    public String queryBooksAll(Model model) {
        List<Books> books = bookService.queryBookAll();
        model.addAttribute("books", books);
        return "allBooks";
    }

    //  新增重定向新页面，新增页面
    @RequestMapping(value = "/toAddBook")
    public String addBook() {
        return "addBook";
    }

    //  新增完成后重定向到查询页面
    @RequestMapping(value = "/addBook",method = RequestMethod.POST)
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        //	重定向
        return "redirect:/books/allBooks";
    }

    //  修改之前把数据封装起来
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBook(id);
        model.addAttribute("book", books);
        return "updateBook";
    }

    //  获取修改数据
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(Model model, Books book) {
        bookService.updateBook(book);
        Books books = bookService.queryBook(book.getBookID());
        model.addAttribute("books", books);
        return "redirect:/books/allBooks";
    }

    //  删除id
    @RequestMapping("/del/{bookId}")
    public String del(@PathVariable("bookId") int id) {
        bookService.deleteBook(id);
        return "redirect:/books/allBooks";
    }

}
