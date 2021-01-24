package team05.demo01;

public class TestMember {
    public static void main(String[] args) {
        TeamMember t1=new TeamMember("Haochen Zou","zouhaochenluke@gmail.com");
        System.out.println(t1.getName()+','+t1.getEmail());
        TeamMember t2=new TeamMember("Dejian Wang","wangdejianwang@gmail.com");
        System.out.println(t2.getName()+','+t2.getEmail());
    }
}
