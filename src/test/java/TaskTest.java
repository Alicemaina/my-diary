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
      task.update("noni");
      assertEquals("noni", Task.find(task.getId()).getName());
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

// import java.util.ArrayList;
// import java.util.List;
// import org.sql2o.*;
// import org.junit.*;
// import java.sql.Timestamp;
// import java.util.Date;
// import static org.junit.Assert.*;


// public class TaskTest {

//   @Rule
//   public DatabaseRule databaseRule = new DatabaseRule();

//   // constructor

//   @Test 
//   public void task_instantiatesCorrectly_true() {
//     Task task = new Task("Java", "description", "moringa");
//     assertEquals(true, task instanceof Task);
//   }

//   @Test 
//   public void getName_taskInstiatesWithName_Java() {
//     Task task = new Task("Java", "description", "moringa");
//     assertEquals("Java", task.getName());
//   }

//   @Test 
//   public void getDescription_taskInstiatesWithDescription_String() {
//     Task task = new Task("Java", "description", "moringa");
//     assertEquals("description", task.getDescription());
//   }

//   @Test
//   public void getLoctaion_taskInstantiatesWithLocation_string() {
//     Task task = new Task("Java", "description", "moringa");
//     assertEquals("moringa", task.getLocation());
//   }

//   // equals
  
  

//   @Test
//   public void equals_FalseIfAttributesAreDifferent_false() {
//     Task firstTask = new Task("Java", "description", "moringa");
//     Task anotherTask = new Task("Java", "description", "moringa");
//     assertTrue(firstTask.equals(anotherTask));

//   }

//   @Test 
//   public void save_instartsObjectIntoDatabase_Task() {
//     Task task = new Task("Java", "description", "moringa");
//     task.save();
//     assertTrue(Task.all().get(0).equals(task));
//   }

//   @Test
//   public void all_returnAllInstancesOfTask_true() {
//     Task firstTask = new Task("Java", "description", "moringa");
//     firstTask.save();
//     Task secondTask = new Task("ruby", "description", "moringa");
//     secondTask.save();
//     assertEquals(true, Task.all().get(0).equals(firstTask));
//     assertEquals(true, Task.all().get(1).equals(secondTask));
//   }

//   @Test 
//   public void save_assignsIdToObject() {
//     Task task = new Task("Java", "description", "moringa");
//     task.save();
//     Task saveTask = Task.all().get(0);
//     assertEquals(task.getId(), saveTask.getId());
//   }

//   @Test
//   public void find_returnTaskWithSameId_secondTask() {
//     Task firstTask = new Task("Java", "description", "moringa");
//     firstTask.save();
//     Task secondTask = new Task("Ruby", "description", "moringa");
//     secondTask.save();
//     assertEquals(Task.find(secondTask.getId()), secondTask);

//   }

  // @Test
//   public void setName_updateNameAppropriately_true() {
//     // String expectedOutput = "Java";
//     Task task = new Task ("Java", "description", "moringa");
//     task.save();
//     // task.update(expectedOutput);
//     task.update("Ruby", "description", "moringa");
//     assertEquals("Ruby", Task.find(task.getId()).getName());
//   }

// //   @Test 
// //   public void save_recordsTimeOfCreatinInDatabase() {
// //     Task task = new Task("Java", "description", "moringa");
// //     task.save();
// //     Timestamp savedTaskWhen = task.find(task.getId()).getwhen();
    
// //   }
}