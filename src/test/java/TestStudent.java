import domain.Student;
import org.junit.Assert;
import org.junit.Test;
import validation.ValidationException;

import static org.junit.Assert.assertThrows;

public class TestStudent extends TestBase{
    @Test
    public void testAddStudentAndSearchById() {
        Student student1 = new Student("123","Gustavo",935,"gustavo@gmail.com");
        this.service.addStudent(student1);
        Assert.assertEquals("The student with the id 123 should be added",student1,this.service.findStudent("123"));
    }

    @Test
    public void testAddStudentAndSearchByName(){
        Student student1 = new Student("123","Gustavo",935,"gustavo@gmail.com");
        this.service.addStudent(student1);
        Student studentAdded = this.service.findStudent("123");
        String studentName = studentAdded.getNume();
        Assert.assertEquals("The name should be Gustavo", studentName.toString(),"Gustavo");
    }

    @Test
    public void testAddStudentBVAGroupNegative() {
        Student student3 = new Student("125","Gustavo2",-1,"gustavo@gmail.com");
        assertThrows("An exception should be thrown for a student with negative group",ValidationException.class, () -> service.addStudent(student3));
    }

    @Test
    public void testAddStudentBVAGroup1(){
        Student student2 = new Student("124","Gustavo1",1,"gustavo@gmail.com");
        this.service.addStudent(student2);
        Assert.assertEquals("The student with the id 124 should be added",student2,this.service.findStudent("124"));
    }


    @Test
    public void testAddStudentBVAGroup0(){
        Student student1 = new Student("123","Gustavo",0,"gustavo@gmail.com");
        this.service.addStudent(student1);
        Assert.assertEquals("The student with the id 123 should be added",student1,this.service.findStudent("123"));
    }

    @Test
    public void testAddStudentEPIdEmpty() {
        Student student1 = new Student("","Gustavo",0,"gustavo@gmail.com");
        assertThrows("An exception should be thrown for a student with empty id",ValidationException.class, () -> service.addStudent(student1));
    }

    @Test
    public void testAddStudentEPIdNull(){
        Student student2 = new Student(null,"Gustavo1",1,"gustavo1@gmail.com");
        assertThrows("An exception should be thrown for a student with null id",ValidationException.class, () -> service.addStudent(student2));
    }

    @Test
    public void testAddStudentEPEmptyName() {
        Student student1 = new Student("123","",0,"gustavo@gmail.com");
        assertThrows("An exception should be thrown for a student with empty name",ValidationException.class, () -> service.addStudent(student1));
    }

    @Test
    public void testAddStudentEPNullName(){
        Student student2 = new Student("124",null,1,"gustavo1@gmail.com");
        assertThrows("An exception should be thrown for a student with null name",ValidationException.class, () -> service.addStudent(student2));
    }

    @Test
    public void testAddStudentEPEmptyEmail() {
        Student student1 = new Student("123","Gustavo",0,"");
        assertThrows(ValidationException.class, () -> service.addStudent(student1));
    }

    @Test
    public void testAddStudentEPNullEmail(){
        Student student2 = new Student("124","Gustavo1",1,null);
        assertThrows(ValidationException.class, () -> service.addStudent(student2));
    }

    @Test
    public void testAddExistentStudent(){
        Student student1 = new Student("123","Gustavo",935,"gustavo@gmail.com");
        this.service.addStudent(student1);
        Student existentStudent = this.service.addStudent(student1);
        Assert.assertEquals("When adding a student with the same id, it should return the existent student", student1, existentStudent);
    }

}
