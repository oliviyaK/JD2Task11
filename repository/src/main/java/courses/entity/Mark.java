package courses.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "marks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mark")
    private int mark;

    @Column(name = "review")
    private String review;

    @OneToMany(mappedBy = "mark")
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Mark mark = (Mark) o;
        return id != null && Objects.equals(id, mark.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
