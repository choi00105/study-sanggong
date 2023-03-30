package com.test.exam03;

// hw
public class Member { // DTO (Data Transfer Object)
    private String userId;
    private String userName;
    private int age;

    public Member(){}
    public Member(String userId, String userName, int age){
        
        this.userId = userId;
        this.userName = userName;
        this.age = age;

    }

    //getter method
    public String getUserId() {
        return this.userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public int getAge() {
        return this.age;
    }

    // setter method 
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    // 내부클래스
    static class Builder{
        private String userid;
        private String username;
        private int age;

        public Builder userid(String userid) {
            this.userid = userid;
            return this;
        }

        public Builder username(String username) {
            this.username =username;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Member build() {
            if(userid == null || username == null || age == 0)
                throw new IllegalStateException("cannot create Member");
            
            return new Member(userid, username, age);
            
        }
    }

}
