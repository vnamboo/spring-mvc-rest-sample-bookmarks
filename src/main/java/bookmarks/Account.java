package bookmarks;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vnamboo on 15/1/2017.
 */
@Entity
public class Account {

    @OneToMany(mappedBy = "account")
    private Set<Bookmark> bookmarks = new HashSet<>();


    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }


    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String password;
    private String userName;


    public Account(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    // JPA Only
    Account() {
    }
}
