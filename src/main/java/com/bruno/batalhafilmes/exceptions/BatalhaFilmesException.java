package com.bruno.batalhafilmes.exceptions;

/**
 * @author Bruno Henrique
 **/
public class BatalhaFilmesException extends RuntimeException{

  private String msg;

  public BatalhaFilmesException(String msg) {
    super(msg);
    this.msg = msg;
  }
}
