## 一、项目环境

`idea2020.1.1`

`mysql5.7.31`

`spring+mybatis+springmvc`

`maven3.6.1`

`官方文档地址`

spring：

1. https://docs.spring.io/spring-framework/docs/current/reference/html/core.html
2. https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference

springmvc

1. https://docs.spring.io/spring-framework/docs/5.2.0.RELEASE/spring-framework-reference/web.html#spring-web

mybatis

1. https://mybatis.org/mybatis-3/zh/getting-started.html
2. http://mybatis.org/spring/zh/index.html

## 二、项目搭建

- jar包依赖

- 代码

    - `entity`

        `Books.java`

        ```java
        @Data
        @AllArgsConstructor //  有参构造
        @NoArgsConstructor  //  无参构造
        public class Books implements Serializable {
        
            private int bookID;
            private String bookName;
            private int bookCounts;
            private String detail;
        }
        ```

    - `mapper`

        `BookMapper.java`

        ```java
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
        ```

    - `service`

        `BookService.java`

        ```java
        public interface BookService {
            //  增加一本书
            int addBook(Books books);
        
            //  删除一本书
            int deleteBook(int id);
        
            //  修改一本书
            int updateBook(Books books);
        
            //  查询一本书
            Books queryBook(int id);
        
            //  查询全部书籍
            List<Books> queryBookAll();
        }
        ```

        `BookServiceImpl.java`

        ```java
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
        ```

    - `controller`

        `BookController.java`

        ```java
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
        ```

- `allBooks.jsp`

    ```jsp
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>书籍列表</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 引入 Bootstrap -->
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>书籍列表 —— 显示所有书籍</small>
                    </h1>
                </div>
            </div>
        </div>
    
        <div class="row">
            <div class="col-md-4 column">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/books/toAddBook">新增</a>
            </div>
        </div>
    
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名字</th>
                        <th>书籍数量</th>
                        <th>书籍详情</th>
                        <th>操作</th>
                    </tr>
                    </thead>
    
                    <tbody>
                    <c:forEach var="book" items="${requestScope.get('books')}">
                        <tr>
                            <td>${book.getBookID()}</td>
                            <td>${book.getBookName()}</td>
                            <td>${book.getBookCounts()}</td>
                            <td>${book.getDetail()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/books/toUpdateBook?id=${book.getBookID()}">更改</a>
                                |
                                <a href="${pageContext.request.contextPath}/books/del/${book.getBookID()}">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    ```

- addBook.jsp

    ```jsp
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    
    <html>
    <head>
        <title>新增书籍</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 引入 Bootstrap -->
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>新增书籍</small>
                    </h1>
                </div>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/books/addBook" method="post">
            书籍名称：<input type="text" name="bookName" required><br><br><br>
            书籍数量：<input type="text" name="bookCounts" required><br><br><br>
            书籍详情：<input type="text" name="detail" required><br><br><br>
            <input type="submit" value="添加">
        </form>
    </div>
    ```

- updateBook.jsp

    ```jsp
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>修改信息</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 引入 Bootstrap -->
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>
                        <small>修改信息</small>
                    </h1>
                </div>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/books/updateBook" method="post">
            <input type="hidden" name="bookID" value="${book.getBookID()}"/>
            书籍名称：<input type="text" name="bookName" value="${book.getBookName()}"/>
            书籍数量：<input type="text" name="bookCounts" value="${book.getBookCounts()}"/>
            书籍详情：<input type="text" name="detail" value="${book.getDetail() }"/>
            <input type="submit" value="提交"/>
        </form>
    </div>
    ```

## 三、项目整合

- mybatis

    - mybatis-config.xml

        ```xml
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE configuration
                PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
                "http://mybatis.org/dtd/mybatis-3-config.dtd">
        <configuration>
        
            <settings>
                <!--日志输出-->
                <setting name="logImpl" value="LOG4J"/>
                <!--下划线驼峰自动转换-->
                <setting name="mapUnderscoreToCamelCase" value="true"/>
                <!--开启缓存：默认是开启的-->
                <setting name="cacheEnabled" value="true"/>
            </settings>
            
            <typeAliases>
                <!--
                    两种方法：第一种可以自定义别名，适用于实体类较少时，第二种只能使用默认别名(实体类首字母小写，也可以通过注解的方式起别名，注解方法在实体类上加注解@Alias("别名"))
                -->
                <!--实体类起别名，指定实体类-->
                <!--<typeAlias type="com.oay.entity.User" alias="user" />-->
                <!--在没有注解的情况下，会使用 Bean 的首字母小写的非限定类名来作为它的别名-->
                <package name="com.oay.entity"/>
            </typeAliases>
            <!--这里在整合在spring-dao.xml里面-->
            <!--<mappers>
                <mapper resource="mappers/BookMapper.xml"/>
            </mappers>-->
        </configuration>
        ```

- spring

    - spring-dao.xml配置(mapper/dao层)

        ```xml
        <!-- 1.关联数据库文件 -->
        <context:property-placeholder location="classpath:database.properties"/>
        ```

        ```xml
        <!-- 2.数据库连接池 这里使用c3p0 -->
         <!--数据库连接池
            dbcp 半自动化操作 不能自动连接
            c3p0 自动化操作（自动的加载配置文件 并且设置到对象里面）
        -->
         <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
             <!-- 配置连接池属性 -->
             <property name="driverClass" value="${jdbc.driver}"/>
             <property name="jdbcUrl" value="${jdbc.url}"/>
             <property name="user" value="${jdbc.username}"/>
             <property name="password" value="${jdbc.password}"/>
             <!-- c3p0连接池的私有属性 -->
             <property name="maxPoolSize" value="30"/>
             <property name="minPoolSize" value="10"/>
             <!-- 关闭连接后不自动commit -->
             <property name="autoCommitOnClose" value="false"/>
             <!-- 获取连接超时时间 -->
             <property name="checkoutTimeout" value="10000"/>
             <!-- 当获取连接失败重试次数 -->
             <property name="acquireRetryAttempts" value="2"/>
         </bean>
        ```

        ```xml
        <!-- 3.配置SqlSessionFactory对象 -->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- 注入数据库连接池 -->
            <property name="dataSource" ref="dataSource"/>
            <!-- 配置Mybatis全局配置文件:mybatis-config.xml ；这个也可以省掉,下面配置了自动扫描mapping.xml文件 -->
            <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>
            <!-- 自动扫描mapping.xml文件 -->
            <property name="mapperLocations">
                <list>
                    <value>classpath:mappers/*/*.xml</value>
                </list>
            </property>
        </bean>
        ```

        ```xml
        <!-- 4.配置扫描Dao接口包，动态实现Dao接口注入到spring容器中 -->
        <!--解释 ：https://www.cnblogs.com/jpfss/p/7799806.html-->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <!-- 注入sqlSessionFactory -->
            <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
            <!-- 给出需要扫描Dao接口包 -->
            <property name="basePackage" value="com.**.mapper;com.**.dao"/>
        </bean>
        ```

        `mapper(dao)层的配置文件已完成。`

    - spring-mvc.xml配置(controller层)

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:context="http://www.springframework.org/schema/context"
               xmlns:mvc="http://www.springframework.org/schema/mvc"
               xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/mvc
           https://www.springframework.org/schema/mvc/spring-mvc.xsd">
        
            <!-- 1.开启SpringMVC注解驱动 -->
            <mvc:annotation-driven/>
            <!-- 2.静态资源默认servlet配置-->
            <mvc:default-servlet-handler/>
            <!-- 3.配置jsp 显示ViewResolver视图解析器 -->
            <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                <!-- <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />-->
                <property name="prefix" value="/WEB-INF/jsp/"/>
                <property name="suffix" value=".jsp"/>
            </bean>
            <!-- 4.扫描web相关的bean -->
            <context:component-scan base-package="com.oay.controller"/>
        
            <!--关于拦截器的配置-->
            <mvc:interceptors>
                <mvc:interceptor>
                    <!--/** 包括路径及其子路径-->
                    <!--/admin/* 拦截的是/admin/add等等这种 , /admin/add/user不会被拦截-->
                    <!--/admin/** 拦截的是/admin/下的所有-->
                    <mvc:mapping path="/**"/>
                    <!--bean配置的就是拦截器-->
                    <bean class="com.oay.interceptor.LoginInterceptor"/>
                </mvc:interceptor>
            </mvc:interceptors>
        
            <!--文件上传配置-->
            <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
                <!-- 请求的编码格式，必须和jSP的pageEncoding属性一致，以便正确读取表单的内容，默认为ISO-8859-1 -->
                <property name="defaultEncoding" value="utf-8"/>
                <!-- 上传文件大小上限，单位为字节（10485760=10M） -->
                <property name="maxUploadSize" value="10485760"/>
                <property name="maxInMemorySize" value="40960"/>
            </bean>
        
        </beans>
        ```

    - 添加web

    - 配置web.xml

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
                 version="4.0">
            <!--DispatcherServlet-->
            <servlet>
                <servlet-name>DispatcherServlet</servlet-name>
                <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
                <init-param>
                    <param-name>contextConfigLocation</param-name>
                    <!--一定要注意:我们这里加载的是总的配置文件，之前被这里坑了！-->
                    <param-value>classpath:applicationContext.xml</param-value>
                </init-param>
                <load-on-startup>1</load-on-startup>
            </servlet>
            <servlet-mapping>
                <servlet-name>DispatcherServlet</servlet-name>
                <url-pattern>/</url-pattern>
            </servlet-mapping>
        
            <!--encodingFilter-->
            <filter>
                <filter-name>encodingFilter</filter-name>
                <filter-class>
                    org.springframework.web.filter.CharacterEncodingFilter
                </filter-class>
                <init-param>
                    <param-name>encoding</param-name>
                    <param-value>utf-8</param-value>
                </init-param>
            </filter>
            <filter-mapping>
                <filter-name>encodingFilter</filter-name>
                <url-pattern>/*</url-pattern>
            </filter-mapping>
        
            <!--Session过期时间，单位分钟-->
            <session-config>
                <session-timeout>15</session-timeout>
            </session-config>
        
        </web-app>
        ```

    - spring-service.xml(service层)

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:context="http://www.springframework.org/schema/context"
               xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd">
        
            <!-- 扫描service相关的bean -->
            <context:component-scan base-package="com.oay.service"/>
        
            <!--BookServiceImpl注入到IOC容器中 , 这里可以用注解方式也可以用bean方式，这里采用了注解 -->
            <!--<bean id="bookServiceImpl" class="com.oay.service.BookServiceImpl">
                <property name="bookMapper" ref="bookMapper"/>
            </bean>-->
        
            <!-- 配置事务管理器 -->
            <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                <!-- 注入数据库连接池 -->
                <property name="dataSource" ref="dataSource"/>
            </bean>
        </beans>
        ```

- spring全局配置文件

    - applicationContext.xml

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns:context="http://www.springframework.org/schema/context"
               xmlns:aop="http://www.springframework.org/schema/aop"
               xsi:schemaLocation="http://www.springframework.org/schema/beans
                    http://www.springframework.org/schema/beans/spring-beans.xsd
                    http://www.springframework.org/schema/context
                    http://www.springframework.org/schema/context/spring-context.xsd
                    http://www.springframework.org/schema/aop
                    http://www.springframework.org/schema/aop/spring-aop.xsd">
            
            <!--
                导入spring配置文件
                必须导入spring-dao.xml，spring-service.xml，spring-mvc.xml
            -->
            <import resource="spring/spring-dao.xml"/>
            <import resource="spring/spring-service.xml"/>
            <import resource="spring/spring-mvc.xml"/>
            <import resource="spring/spring-bean.xml"/>
        
        </beans>
        ```

- 数据源配置文件

    `database.properties`

    ```properties
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=false&useUnicode=true&characterEncoding=utf8
    jdbc.username=root
    jdbc.password=root123456
    ```

- log4j配置文件

    `log4j.properties`

    ```properties
    #将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
    log4j.rootLogger=DEBUG,console,file
    #控制台输出的相关设置
    log4j.appender.console = org.apache.log4j.ConsoleAppender
    log4j.appender.console.Target = System.out
    log4j.appender.console.Threshold=DEBUG
    log4j.appender.console.layout = org.apache.log4j.PatternLayout
    log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
    #文件输出的相关设置
    log4j.appender.file = org.apache.log4j.RollingFileAppender
    log4j.appender.file.File=./logs/log.log
    log4j.appender.file.MaxFileSize=10mb
    log4j.appender.file.Threshold=DEBUG
    log4j.appender.file.layout=org.apache.log4j.PatternLayout
    log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
    #日志输出级别
    log4j.logger.org.mybatis=DEBUG
    log4j.logger.java.sql=DEBUG
    log4j.logger.java.sql.Statement=DEBUG
    log4j.logger.java.sql.ResultSet=DEBUG
    log4j.logger.java.sql.PreparedStatement=DEBUG
    log4j.logger.com.mchange.v2.async.ThreadPoolAsynchronousRunner = ERROR
    ```

