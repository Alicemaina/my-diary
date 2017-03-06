import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class TaskTest {

	@Rule
  public DatabaseRule databaseRule = new DatabaseRule();

  // constructor

  @Test 
  public void task_instantiatesCorrectly_true() {
  	Task task = new Task("Java", "description", "moringa");
  	assertEquals(true, task instanceof Task);
  }

  @Test 
  public void getName_taskInstiatesWithName_Java() {
  	Task task = new Task("Java", "description", "moringa");
  	assertEquals("Java", task.getName());
  }

  @Test 
  public void getDescription_taskInstiatesWithDescription_String() {
  	Task task = new Task("Java", "description", "moringa");
  	assertEquals("description", task.getDescription());
  }

  @Test
  public void getLoctaion_taskInstantiatesWithLocation_string() {
  	Task task = new Task("Java", "description", "moringa");
  	assertEquals("moringa", task.getLocation());
  }
  
  @Test 
  public void equals_returnTrueIfNameAndDescriptionAreSame_true() {
  	Task firstTask = new Task("Java", "description", "moringa");
  	Task anotherTask = new Task("Java", "description", "moringa");
  	assertTrue(firstTask.equals(anotherTask));
  }
  }

}