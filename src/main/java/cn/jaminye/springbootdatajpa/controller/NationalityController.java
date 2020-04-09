package cn.jaminye.springbootdatajpa.controller;

import cn.jaminye.springbootdatajpa.entity.Nationality;
import cn.jaminye.springbootdatajpa.entity.User;
import cn.jaminye.springbootdatajpa.service.NationalityService;
import cn.jaminye.springbootdatajpa.util.PageDTO;
import cn.jaminye.springbootdatajpa.values.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jamin
 * @date 2020/4/9 11:21
 */
@Controller
@RequestMapping("/nationality")
public class NationalityController {
  @Autowired NationalityService nationalityService;

  @GetMapping("/pageList")
  @ResponseBody
  public Result pageList(
      @RequestParam(required = false) String name,
      @RequestParam Integer page,
      @RequestParam Integer limit) {
    PageDTO<Nationality> nationalityPage = nationalityService.findByNameLike(name, page - 1, limit);
    return Result.success(nationalityPage);
  }
}
