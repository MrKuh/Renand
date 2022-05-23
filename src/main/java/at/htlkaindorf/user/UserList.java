package at.htlkaindorf.user;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserList {

    @XmlElement(name = "user")
    private List<UserData> users = new ArrayList<>();

    public void addUser(UserData u){
        users.add(u);
    }

    public void removeUser(UserData u){
        users.remove(u);
    }

    public Optional<UserData> findUserByUsername(String username){
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    /*public List<UserData> getUserList(){
        return users;
    }*/
}
