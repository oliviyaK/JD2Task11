package courses.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "TEACHER")
@ToString(exclude = "courses")
public class Teacher extends Person implements Serializable {

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "teacher_courses", joinColumns = {@JoinColumn(name = "id_teacher")},
            inverseJoinColumns = {@JoinColumn(name = "id_course")}
    )
    private Set<Course> courses = new HashSet<>();
}
