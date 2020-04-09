package cn.jaminye.springbootdatajpa.controller;

import cn.jaminye.springbootdatajpa.entity.User;
import cn.jaminye.springbootdatajpa.service.UserService;
import cn.jaminye.springbootdatajpa.util.PageDTO;
import cn.jaminye.springbootdatajpa.values.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;

/**
 * @author Jamin
 * @date 2020/4/1 9:54
 */
@Controller
@RequestMapping("users")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("list")
  public String list() {
    return "list";
  }

  @GetMapping("pageList")
  @ResponseBody
  public Result pageList(
      @RequestParam(required = false) String name,
      @RequestParam Integer page,
      @RequestParam Integer limit) {
    PageDTO<User> userPage = userService.findByNameLike(name, page - 1, limit);
    return Result.success(userPage);
  }

  @GetMapping("add")
  public String add() {
    return "add";
  }

  @PostMapping("save")
  @ResponseBody
  public Result save(@RequestBody User user) {
    userService.save(user);
    return Result.success();
  }

  @GetMapping("del/{id}")
  @ResponseBody
  public Result delById(@PathVariable("id") Integer id) {
    userService.del(id);
    return Result.success();
  }

  @GetMapping("edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("user", user);
    return "add";
  }
}
