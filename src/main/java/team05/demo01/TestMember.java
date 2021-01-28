package team05.demo01;

public class TestMember {
    public static void main(String[] args) {
        TeamMember t1=new TeamMember("Haochen Zou","zouhaochenluke@gmail.com");
        System.out.println(t1.getName()+','+t1.getEmail());
        TeamMember t2=new TeamMember("Dejian Wang","wangdejianwang@gmail.com");
        System.out.println(t2.getName()+','+t2.getEmail());
        TeamMember t3=new TeamMember("Jiaming Han","jiamingtom@gmail.com");
        System.out.println(t3.getName()+','+t3.getEmail());
        TeamMember t4=new TeamMember("Yongtang Lu","wilsonluyongtang_2005@hotmail.com");
        System.out.println(t4.getName()+','+t4.getEmail());
        TeamMember t5=new TeamMember("Zitao Wang","wangzitao9@gmail.com");
        System.out.println(t4.getName()+','+t5.getEmail());
    }
}
