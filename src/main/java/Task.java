import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Task {
  private String name;
  private String location;
  private String description;
  private Timestamp current;
  private int id;

  public Task(String name, String location, String description) {
    this.name = name;
    this.location = location;
    this.description = description;
  }

  public String getName() {
    return name;
  }


  public String getLocation() {
    return location;
  }


  public String getDescription() {
    return description;
  }

  public int getId() {
    return id;
  }

  public Timestamp getCurrent() {
    return current;
  }

  @Override
  public boolean equals(Object otherTask){
    if(!(otherTask instanceof Task)) {
      return false;
    }   else {
      Task newTask = (Task) otherTask;
      return this.getName().equals(newTask.getName()) &&
             this.getLocation().equals(newTask.getLocation()) &&
             this.getDescription().equals(newTask.getDescription());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT into tasks (name, location, description, current) VALUES (:name, :location, :description, now())";
      this.id = (int)con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("location", this.location)
        .addParameter("description", this.description)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Task>all() {
    String sql = "SELECT * FROM tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }

  public static Task find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks where id=:id";
      Task task = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Task.class);
      return task;  
    }
  }

  public void update(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET description =:description WHERE id = :id";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }



  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tasks WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}