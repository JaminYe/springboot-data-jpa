package cn.jaminye.springbootdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.FacesRequestAttributes;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author Jamin
 * @date 2020/3/30 13:23
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
  /** id */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  /** name */
  private String name;
  /** 手机号 */
  private String phoneNumber;
  /** age */
  private int age;

  /** 性别 */
  private int sex;

  /** 爱好 */
  private String hobby;
  /**
   * 所属国籍
   */
  @ManyToOne(targetEntity = Nationality.class)
  @JoinColumn(name="nationality_id")
  private Integer nationality_id;


}
