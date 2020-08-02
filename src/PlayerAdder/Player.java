package PlayerAdder;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Player implements Serializable {
    public int id;
    public String name;
    public String department;
    public String age;
    public String format;
    
    public Player(int id, String name, String department, String age, String format){
        this.id         = id;
        this.name       = name;
        this.department = department;
        this.age        = age;
        this.format     = format;
    }
    
    public Player(){
        this(0,"","","","");
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", name='" + name + '\'' + ", format='" + format + '\'' + '}';
    }
}

