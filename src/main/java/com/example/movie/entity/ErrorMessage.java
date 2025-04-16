package com.example.movie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorMessage {

  private int status;
  private Object msg;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date timestamp;

  public ErrorMessage() {
  }

  public ErrorMessage(int status, Object msg, Date timestamp) {
    this.status = status;
    this.msg = msg;
    this.timestamp = timestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getMsg() {
    return msg;
  }

  public void setMsg(Object msg) {
    this.msg = msg;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}
