package at.htlkaindorf.user;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class UserData {
    @XmlElement(name = "userName")
    private String username;
    @XmlElement(name = "highScores")
    private HashMap<Integer, Date> scores = new HashMap<>();

    // And more...
}
