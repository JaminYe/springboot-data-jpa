package cn.jaminye.springbootdatajpa.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamin
 * @date 2020/4/2 15:02
 * @deprecated
 */
@Getter
@Setter
@ToString
public class PageDTO<T> {
private final long totalElements;
private final List<T> content=new ArrayList<T>();
private final int number;
private final Pageable pageable;
public PageDTO(List<T> content, long totalElement, Pageable pageable){
    this.totalElements=totalElement;
    this.content.addAll(content);
    this.pageable=  pageable;
    this.number=pageable.getPageNumber();
}
}
