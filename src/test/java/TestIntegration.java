import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestIntegration extends TestBase {

    @Test
    public void testAddStudent(){
        Student student1 = new Student("123","Gustavo",935,"gustavo@gmail.com");
        Student returned = this.service.addStudent(student1);
        Assert.assertNull("If a student is successfully added it should return null", returned);
    }

    @Test
    public void testAddAssignment(){
        Tema assignment = new Tema("123","description",2,12);
        Assert.assertNull("When adding an assignment successfully, it should return null",  service.addTema(assignment));
    }
    
    @Test
    public void testAddGrade(){
        studentXMLRepository.save(new Student("123","Gustavo",935,"gustavo@gmail.com"));
        temaXMLRepository.save(new Tema("123","description",14,1));
        Nota nota = new Nota("123", "123", "123", 10D, LocalDate.of(2022,3,1));
        assertEquals(10D, service.addNota(nota, "Foarte frumos, Gustavo"), 1e-15);
    }

    @Test
    public void testAll(){
        Student student = new Student("123","Gustavo",935,"gustavo@gmail.com");
        Tema tema = new Tema("123","description",2,12);
        Nota nota = new Nota("123", "123", "123", 10.0, LocalDate.of(2022,3,1));
        assertNull("If a student is successfully added it should return null",service.addStudent(student));
        assertNull("When adding an assignment successfully, it should return null",service.addTema(tema));
        assertEquals("The grade returned from service should be the same with the added one",10D,service.addNota(nota,"frumos"),1e-15);
    }


}
