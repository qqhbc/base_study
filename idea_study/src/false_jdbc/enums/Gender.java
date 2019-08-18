package false_jdbc.enums;

public enum Gender {
    MALE("男"),FEMALE("女");
    private String msg;
    Gender(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
