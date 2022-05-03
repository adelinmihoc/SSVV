import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;


public class TestBase {
    protected final StudentValidator studentValidator = new StudentValidator();
    protected final TemaValidator temaValidator = new TemaValidator();
    protected static final String filenameStudent = "fisiere/Studenti.xml";
    protected static final String filenameTema = "fisiere/Teme.xml";
    protected static final String filenameNota = "fisiere/Note.xml";

    protected final StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    protected final TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    protected final NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    protected final NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    protected final Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    @Before
    public void beforeEach() {
        for (Nota nota : this.notaXMLRepository.findAll()) {
            this.notaXMLRepository.delete(nota.getID());
        }
        for (Tema tema : this.temaXMLRepository.findAll()) {
            this.temaXMLRepository.delete(tema.getID());
        }
        for (Student student : this.studentXMLRepository.findAll()) {
            this.studentXMLRepository.delete(student.getID());
        }
    }
}
