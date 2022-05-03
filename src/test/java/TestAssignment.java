import domain.Tema;
import org.junit.Assert;
import org.junit.Test;
import validation.ValidationException;


/**
 * Assignment
 * nrTema - numarul temei
 * descriere - descrierea unei teme
 * deadline - deadlineul unei teme
 * primire - saptamana de primirea unei teme
 * Class Constructor
 */

public class TestAssignment extends TestBase{
    @Test
    public void testAddAssignmentEmptyNumberAssignment(){
        Tema assignment = new Tema("","description",14,5);
        Assert.assertThrows("It should throw an exception because the number of the assignment is empty",ValidationException.class, ()->service.addTema(assignment));
    }

    @Test
    public void testAddAssignmentNullNumberAssignment(){
        Tema assignment = new Tema(null,"description",14,5);
        Assert.assertThrows("It should throw an exception because the number of the assignment is null",ValidationException.class, ()->service.addTema(assignment));
    }

    @Test
    public void testAddAssignmentEmptyDescription(){
        Tema assignment = new Tema("1","",14,5);
        Assert.assertThrows("It should throw an exception because the description of the assignment is empty",ValidationException.class, ()->service.addTema(assignment));
    }

    @Test
    public void testAddAssignmentNullDescription(){
        Tema assignment = new Tema("1",null,14,5);
        Assert.assertThrows("It should throw an exception because the description of the assignment is null",ValidationException.class, ()->service.addTema(assignment));
    }

    @Test
    public void testAddAssignmentNegativeDeadline(){
        Tema assignment = new Tema("1","description",-14,5);
        Assert.assertThrows("It should throw an exception because the deadline number is negative",ValidationException.class, ()->service.addTema(assignment));
    }

    @Test
    public void testAddAssignmentReceivedNotInRange(){
        Tema assignment = new Tema("1","description",2,19);
        Assert.assertThrows("It should throw an exception because the received hw number is not in correct range",ValidationException.class, ()->service.addTema(assignment));
    }

    @Test
    public void testAddExistentAssignment(){
        Tema assignment = new Tema("1","description",2,12);
        service.addTema(assignment);
        Tema assignmentCopy = this.service.addTema(assignment);
        Assert.assertEquals("When adding an assignment with the same id, it should return the existent assignment", assignment, assignmentCopy);
        this.service.deleteTema("1");
    }

    @Test
    public void testAddAssignmentSuccessful(){
        Tema assignment = new Tema("123","description",2,12);
        Assert.assertNull("When adding an assignment successfully, it should return null",  service.addTema(assignment));
    }


}
