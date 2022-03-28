import domain.Student;
import org.junit.Assert;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertThrows;

public class TestStudent {
    private final StudentValidator studentValidator = new StudentValidator();
    private final TemaValidator temaValidator = new TemaValidator();
    private static final String filenameStudent = "fisiere/Studenti.xml";
    private static final String filenameTema = "fisiere/Teme.xml";
    private static final String filenameNota = "fisiere/Note.xml";

    private final StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    private final TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    private final NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    private final NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    private final Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Test
    public void testAddStudentAndSearchById() {
        Student student1 = new Student("123","Gustavo",935,"gustavo@gmail.com");
        this.service.addStudent(student1);
        Assert.assertEquals("The student with the id 123 should be added",student1,this.service.findStudent("123"));
        this.service.deleteStudent("123");
    }

    @Test
    public void testAddStudentAndSearchByName(){
        Student student1 = new Student("123","Gustavo",935,"gustavo@gmail.com");
        this.service.addStudent(student1);
        Student studentAdded = this.service.findStudent("123");
        String studentName = studentAdded.getNume();
        Assert.assertEquals("The name should be Gustavo", studentName.toString(),"Gustavo");
        this.service.deleteStudent("123");
    }

    @Test
    public void testAddStudentNegativeGroup() {
        Student student1 = new Student("123","Gustavo",-20,"gustavo@gmail.com");
        assertThrows(ValidationException.class, () -> service.addStudent(student1));
    }

    @Test
    public void testAddStudentBVAGroup() {
        Student student1 = new Student("123","Gustavo",0,"gustavo@gmail.com");
        Student student2 = new Student("124","Gustavo1",1,"gustavo@gmail.com");
        Student student3 = new Student("125","Gustavo2",-1,"gustavo@gmail.com");
        this.service.addStudent(student1);
        Assert.assertEquals("The student with the id 123 should be added",student1,this.service.findStudent("123"));
        this.service.addStudent(student2);
        Assert.assertEquals("The student with the id 124 should be added",student2,this.service.findStudent("124"));
        assertThrows(ValidationException.class, () -> service.addStudent(student3));
        this.service.deleteStudent("123");
        this.service.deleteStudent("124");
    }

    @Test
    public void testAddStudentEPId() {
        Student student1 = new Student("","Gustavo",0,"gustavo@gmail.com");
        Student student2 = new Student(null,"Gustavo1",1,"gustavo1@gmail.com");
        assertThrows(ValidationException.class, () -> service.addStudent(student1));
        assertThrows(ValidationException.class, () -> service.addStudent(student2));
    }

    @Test
    public void testAddStudentEPName() {
        Student student1 = new Student("123","",0,"gustavo@gmail.com");
        Student student2 = new Student("124",null,1,"gustavo1@gmail.com");
        assertThrows(ValidationException.class, () -> service.addStudent(student1));
        assertThrows(ValidationException.class, () -> service.addStudent(student2));
    }

    @Test
    public void testAddStudentEPEmail() {
        Student student1 = new Student("123","Gustavo",0,"");
        Student student2 = new Student("124","Gustavo1",1,null);
        assertThrows(ValidationException.class, () -> service.addStudent(student1));
        assertThrows(ValidationException.class, () -> service.addStudent(student2));
    }

}
