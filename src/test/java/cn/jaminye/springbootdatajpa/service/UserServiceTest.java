package cn.jaminye.springbootdatajpa.service;

import cn.jaminye.springbootdatajpa.entity.User;
import cn.jaminye.springbootdatajpa.util.PageDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jamin
 * @date 2020/4/3 11:27
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceTest {

  @Autowired
  UserService userService;

  @Test
  void findAllByNameOrderById() {
    PageDTO<User> test = userService.findByNameLike("测试", 1, 10);
      System.out.println(test);
      

  }

  @Test
  void save() {
    for (int i = 0; i <200; i++) {
      User user = new User();
      user.setName("张三"+i);
      user.setPhoneNumber("111111"+i);
      user.setAge(i);
      userService.save(user);
    }




  }
}