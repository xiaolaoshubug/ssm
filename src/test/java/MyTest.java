import com.oay.entity.Books;
import com.oay.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/*********************************************************
 * @Package: PACKAGE_NAME
 * @ClassName: MyTest.java
 * @Description£∫√Ë ˆ
 * -----------------------------------
 * @author£∫ouay
 * @Version£∫v1.0
 * @Date: 2020-11-30
 *********************************************************/
public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = context.getBean("bookServiceImpl", BookService.class);
        List<Books> books = bookServiceImpl.queryBookAll();
        for (Books book : books) {
            System.out.println(book);
        }
    }
}
