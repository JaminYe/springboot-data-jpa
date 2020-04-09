package cn.jaminye.springbootdatajpa.service;

import cn.jaminye.springbootdatajpa.entity.Nationality;
import cn.jaminye.springbootdatajpa.entity.User;
import cn.jaminye.springbootdatajpa.repository.NationalityRepository;
import cn.jaminye.springbootdatajpa.util.PageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jamin
 * @date 2020/4/9 11:18
 */
@Service
public class NationalityService {
  @Autowired NationalityRepository nationalityRepository;

  public PageDTO<Nationality> findByNameLike(String name, Integer page, Integer limit) {
    Pageable pageable = PageRequest.of(page, limit, Sort.Direction.ASC, "id");
    Page<Nationality> pageUsers = null;
    if (StringUtils.isNotBlank(name)) {
      String filter = "%" + name + "%";
      pageUsers = nationalityRepository.findByNameLike(filter, pageable);
    } else {
      pageUsers = nationalityRepository.findAll(pageable);
    }
    List<Nationality> content = pageUsers.getContent();
    return new PageDTO<Nationality>(content, pageUsers.getTotalElements(), pageable);
  }
}
