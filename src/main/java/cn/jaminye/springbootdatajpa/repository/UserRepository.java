package cn.jaminye.springbootdatajpa.repository;

import cn.jaminye.springbootdatajpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jamin
 * @date 2020/3/30 13:33
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    /**
     * 分页查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User>findByNameLike(String name,Pageable pageable);
}
