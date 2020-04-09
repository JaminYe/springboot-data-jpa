package cn.jaminye.springbootdatajpa.service;

import cn.jaminye.springbootdatajpa.entity.User;
import cn.jaminye.springbootdatajpa.repository.UserRepository;
import cn.jaminye.springbootdatajpa.util.PageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

/**
 * @author Jamin
 * @date 2020/3/30 14:06
 */
@Service
public class UserService {
  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository UserRepository) {
    this.userRepository = UserRepository;
  };

  public Page<User> pageAll(String name, Integer page, Integer limit) {
    Pageable pageable1 = PageRequest.of(page, limit);
    return userRepository.findAll(pageable1);
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public PageDTO<User> findByNameLike(String name, Integer page, Integer limit) {
    Pageable pageable = PageRequest.of(page, limit, Sort.Direction.ASC, "id");
    Page<User> pageUsers = null;
    if (StringUtils.isNotBlank(name)) {
      String filter = "%" + name + "%";
      pageUsers = userRepository.findByNameLike(filter, pageable);
    } else {
      pageUsers = userRepository.findAll(pageable);
    }
    List<User> content = pageUsers.getContent();
    return new PageDTO<>(content, pageUsers.getTotalElements(), pageable);
  }

  public void del(Integer id) {
    userRepository.deleteById(id);
  }

  public User findById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }
}
