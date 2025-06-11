  package com.example.movie.entity;

  import com.fasterxml.jackson.annotation.JsonInclude;
  import jakarta.persistence.PrePersist;

  import java.util.Date;

  public class ResponseMessage {

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    private Date timestamp;

    @PrePersist
    private void onCreate() {
      if(this.timestamp == null) this.timestamp = new Date();
    }

    public ResponseMessage(String msg, Object data) {
      this.msg = msg;
      this.data = data;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }

    public Object getData() {
      return data;
    }

    public void setData(Object data) {
      this.data = data;
    }

    public Date getTimestamp() {
      return timestamp;
    }

    public void setTimestamp(Date timestamp) {
      this.timestamp = timestamp;
    }
  }
