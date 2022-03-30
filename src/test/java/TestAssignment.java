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
import validation.ValidationException;

import static org.junit.Assert.assertThrows;

/**
 * Assignment
 * nrTema - numarul temei
 * descriere - descrierea unei teme
 * deadline - deadlineul unei teme
 * primire - saptamana de primirea unei teme
 * Class Constructor
 */

public class TestAssignment {
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
    public void testAddAssignmentNullNumberAssignment(){
        Tema assignment = new Tema("","description",14,5);
        Assert.assertThrows("It should throw an exception because the number of the assignment is empty",ValidationException.class, ()->service.addTema(assignment));
    }

    @Test
    public void testAddAssignmentNegativeDeadline(){
        Tema assignment = new Tema("1","description",-14,5);
        Assert.assertThrows("It should throw an exception because the deadline number is negative",ValidationException.class, ()->service.addTema(assignment));
    }

}
