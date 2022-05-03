import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestIntegration {
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
    public void testAddStudent(){
        Student student1 = new Student("123","Gustavo",935,"gustavo@gmail.com");
        Student returned = this.service.addStudent(student1);
        Assert.assertNull("If a student is successfully added it should return null",returned);
        this.service.deleteStudent("123");
    }

    @Test
    public void testAddAssignment(){
        Tema assignment = new Tema("123","description",2,12);
        Assert.assertNull("When adding an assignment successfully, it should return null",  service.addTema(assignment));
        this.service.deleteTema("123");
    }
    
    @Test
    public void testAddGrade(){
        studentXMLRepository.save(new Student("123","Gustavo",935,"gustavo@gmail.com"));
        temaXMLRepository.save(new Tema("123","description",14,1));
        Nota nota = new Nota("123", "123", "123", 10D, LocalDate.of(2022,3,1));
        assertEquals(10D, service.addNota(nota, "Foarte frumos, Gustavo"), 1e-15);
        this.service.deleteNota("123");
        this.service.deleteTema("123");
        this.service.deleteStudent("123");
    }

    @Test
    public void testAll(){
        Student student = new Student("123","Gustavo",935,"gustavo@gmail.com");
        Tema tema = new Tema("123","description",2,12);
        Nota nota = new Nota("123", "123", "123", 10.0, LocalDate.of(2022,5,10));
        assertNull("If a student is successfully added it should return null",service.addStudent(student));
        assertNull("When adding an assignment successfully, it should return null",service.addTema(tema));
        assertEquals("The grade returned from service should be the same with the added one",10D,service.addNota(nota,"frumos"),1e-15);
    }


}
