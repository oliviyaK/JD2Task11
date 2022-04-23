package courses.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"tasks","teachers","students"})
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name="hours")
    private String hours;

    @ManyToMany(mappedBy = "courses")
    private  Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private  Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Task> tasks = new HashSet<>();
}
