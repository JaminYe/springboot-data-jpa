package cn.jaminye.springbootdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 国籍
 *
 * @author Jamin
 * @date 2020/4/9 10:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nationality {

  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  /** name */
  private String name;

  @OneToMany
  @JoinColumn(name ="nationality_id")
  private List<User> users = new ArrayList<User>();
}
