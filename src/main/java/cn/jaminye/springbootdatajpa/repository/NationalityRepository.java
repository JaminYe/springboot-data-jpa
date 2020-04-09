package cn.jaminye.springbootdatajpa.repository;

import cn.jaminye.springbootdatajpa.entity.Nationality;
import cn.jaminye.springbootdatajpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jamin
 * @date 2020/4/9 11:15
 */
@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
    /**
     * 分页查询
     * @param name
     * @param pageable
     * @return
     */
    Page<Nationality> findByNameLike(String name, Pageable pageable);
}
