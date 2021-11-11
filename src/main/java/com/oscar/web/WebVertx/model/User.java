package com.oscar.web.WebVertx.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class User {


  private Integer idUsuario;
  private String name;
  private String nameUser;
  private String pass;
  private String dateCreate;

  public User() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
    this.dateCreate = sdf.format(new Date());
  }
}
