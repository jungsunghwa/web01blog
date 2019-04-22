package kr.hs.dgsw.web01blog.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, unique = true, length = 20)
    private String account;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public void setPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(),0,password.getBytes().length);
            this.password = new BigInteger(1, md.digest()).toString();
        } catch (NoSuchAlgorithmException e) {
            Logger logger = LoggerFactory.getLogger(User.class);
            logger.warn(e.getMessage());
            this.password = null;
        }
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String profilePath;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String profileOriginalName;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updated;


    public void changeUserData(User user){
        if (user == null) return;
        if (user.name != null) this.name = user.name;
        if (user.email != null) this.email = user.email;
        if (user.password != null) this.password = user.password;
        if (user.profilePath != null) this.profilePath = user.profilePath;
        if (user.profileOriginalName != null) this.profileOriginalName = user.profileOriginalName;
    }
}
