package team05.demo01;

public class TeamMember {

    private String name;
    private String email;

    public TeamMember(String name,String email){
        this.name=name;
        this.email=email;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setEmail(){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }

}
