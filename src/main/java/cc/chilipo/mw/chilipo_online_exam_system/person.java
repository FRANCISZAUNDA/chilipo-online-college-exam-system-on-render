package cc.chilipo.mw.chilipo_online_exam_system;

public class person
{
    private String name;
    private int age;

    person(String name,int age)
    {
        this.name=name;
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
