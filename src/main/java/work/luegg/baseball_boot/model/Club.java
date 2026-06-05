package work.luegg.baseball_boot.model;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "club")
public class Club {

    @Id
    @Column(nullable = false, unique = true)
    private String team;   // 主鍵，使用者輸入，不自動生成

    @Column(nullable = false)
    private String password;  // 密碼
    
 // ADMIN 或 USER
    private String role;

    

    // Getter / Setter
    public String getRole() {
        return role;
    }    
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // equals 和 hashCode 改為以 team 為主鍵判斷
    @Override
    public int hashCode() {
        return Objects.hash(team);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Club other = (Club) obj;
        return Objects.equals(team, other.team);
    }
}