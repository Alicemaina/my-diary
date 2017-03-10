import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class TaskTest {

  @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Task_instantiatesCorrectly_true() {
      Task task = new Task("name", "location", "description");
      assertEquals(true, task instanceof Task);
    }

    @Test
    public void getName_instantiatesWithName_name() {
      Task task = new Task("name", "location", "description");
      assertEquals("name", task.getName());
    }


    @Test
    public void getLocation_instantiatesWithName_location() {
      Task task = new Task("name", "location", "description");
      assertEquals("location", task.getLocation());
    }


    @Test
    public void getDescription_instantiatesWithName_description() {
      Task task = new Task("name", "location", "description");
      assertEquals("description", task.getDescription());
    }

    @Test
    public void equals_returnsTrueIfAllReturn_true() {
      Task firstTask = new Task("name", "location", "description");
      Task secondTask = new Task("name", "location", "description");
    }

    @Test
    public void save_insertsObjectToDB_Task() {
      Task task = new Task("name", "location", "description");
      task.save();
      assertTrue(Task.all().get(0).equals(task));
    }

    @Test 
    public void all_returnsAllInstances() {
      Task firstTask = new Task("name", "location", "description");
      firstTask.save();
      Task secondTask = new Task("second", "name", "is");
      secondTask.save();
      assertEquals(true, Task.all().get(0).equals(firstTask));
      assertEquals(true, Task.all().get(1).equals(secondTask));
    }

    @Test 
    public void save_assignsId() {
      Task task = new Task("name", "location", "description");
      task.save();
      Task savedTask = Task.all().get(0);
      assertEquals(task.getId(), savedTask.getId());
    }

    @Test 
    public void find_returnsTaskWithSameId_secondTask() {
        Task firstTask = new Task("name", "location", "description");
      firstTask.save();
      Task secondTask = new Task("second", "name", "is");
      secondTask.save();
      assertEquals(Task.find(secondTask.getId()), secondTask);  
    }

    @Test
    public void update_updatesName_true() {
      Task task = new Task("name", "location", "description");
      task.save();
      task.update("going to town");
      assertEquals("going to town", Task.find(task.getId()).getDescription());
    }

    @Test
    public void delete_deletesName_true() {
      Task task = new Task("name", "location", "description");
      task.save();
      int taskId = task.getId();
      task.delete();
      assertEquals(null, Task.find(taskId));
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
      Task task = new Task("name", "location", "description");
      task.save();
      Timestamp savedTaskTime = Task.find(task.getId()).getCurrent();
      Timestamp rightNow = new Timestamp(new Date().getTime());
      assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedTaskTime));    
    }
  
}