package managment;

import courses.dao.EntityDaoImpl;
import courses.entity.Course;
import courses.entity.Mark;
import courses.entity.Person;
import courses.entity.Student;
import courses.entity.Task;
import courses.entity.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * Hello world!
 */
public class Manager {
    public static void main(String[] args) {

        Course course1 = Course.builder()
                .description("Math")
                .hours("140")
                .build();

        Course course2 = Course.builder()
                .description("Gym")
                .hours("150")
                .build();

        EntityDaoImpl daoCourse = new EntityDaoImpl(Course.class);
        daoCourse.insert(course1);
        daoCourse.insert(course2);
        daoCourse.select();

        Student student1 = Student.builder()
                .name("Mihail")
                .surname("Lutikov")
                .courses(Set.of(course1, course2))
                .build();

        Student student2 = Student.builder()
                .name("Maria")
                .surname("Cvetkova")
                .courses(Set.of(course1))
                .build();

        EntityDaoImpl daoStudents = new EntityDaoImpl(Student.class);
        daoStudents.insert(student1);
        daoStudents.insert(student2);
        daoStudents.select();

        Teacher teacher1 = Teacher.builder()
                .name("Valeria")
                .surname("Petrova")
                .courses(Set.of(course1, course2))
                .build();

        Teacher teacher2 = Teacher.builder()
                .name("Galina")
                .surname("Ivanova")
                .courses(Set.of(course1))
                .build();

        EntityDaoImpl daoTeacher = new EntityDaoImpl(Teacher.class);
        daoTeacher.insert(teacher1);
        daoTeacher.insert(teacher2);
        daoTeacher.select();

        Mark mark1 = Mark.builder()
                .mark(3)
                .review("++++++")
                .build();

        Mark mark2 = Mark.builder()
                .mark(9)
                .build();

        Mark mark3 = Mark.builder()
                .mark(7)
                .build();

        EntityDaoImpl daoMark = new EntityDaoImpl(Mark.class);
        daoMark.insert(mark1);
        daoMark.insert(mark2);
        daoMark.insert(mark3);
        daoMark.select();
        daoMark.deleteById(3);
        daoMark.select();

        Task task1 = Task.builder()
                .description("Task1")
                .course(course1)
                .mark(mark2)
                .build();

        Task task2 = Task.builder()
                .description("Task2")
                .course(course2)
                //   .mark(mark1)
                .build();

        EntityDaoImpl daoTask = new EntityDaoImpl(Task.class);
        daoTask.insert(task1);
        daoTask.insert(task2);
        daoTask.select();
        task2.setMark(mark1);
        daoTask.update(task2);

    }
}
