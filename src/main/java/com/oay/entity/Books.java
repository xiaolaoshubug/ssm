package com.oay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*********************************************************
 * @Package: com.oay.entity
 * @ClassName: Books.java
 * @Description： 书籍实体类
 * -----------------------------------
 * @author：ouay
 * @Version：v1.0
 * @Date: 2020-11-30
 *********************************************************/
@Data
@AllArgsConstructor //  有参构造
@NoArgsConstructor  //  无参构造
public class Books implements Serializable {

    private int bookID;
    private String bookName;
    private int bookCounts;
    private String detail;
}
