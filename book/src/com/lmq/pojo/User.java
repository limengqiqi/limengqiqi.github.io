package com.lmq.pojo;



public class User {
    private Integer sno;
    private String studentname;
    private String pwd;
    private String major;

    public User() {
    }

    public User(Integer sno, String studentname, String pwd, String major) {
        this.sno = sno;
        this.studentname = studentname;
        this.pwd = pwd;
        this.major = major;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "User{" +
                "sno=" + sno +
                ", studentname='" + studentname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
