import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
// import org.joda.time.format.DateTimeFormat;
import java.text.SimpleDateFormat;
// import org.apache.spark.sql.functions;

public class Task {
	private String name;
	private String location;
	private String description;
	private Timestamp time;


	public Task(String name, String description, String location) {
		this.name = name;
		this.description = description;
		this.location = location;
		time = new Timestamp(new Date().getTime());

	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	// public String getTime(){
 //    String pattern = "MM/dd/yyyy HH:mm:ss";
 //    SimpleDateFormat format = new SimpleDateFormat(pattern);
 //    System.out.println("Output: " + time);
 //    return format.format(time);
 //  }  time needed looking for best way to constact init

	@Override 
	public boolean equals(Object otherTask) {
		if(!(otherTask instanceof Task)) {
			return false;
		} else {
			Task newTask = (Task) otherTask;
			return this.getName().equals(newTask.getName())&&
			       this.getDescription().equals(newTask.getDescription())&&
			       this.getLocation().equals(newTask.getLocation());
		}
	}


	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO tasks (name, description, location) VALUES (:name, :description, :location)";
			con.createQuery(sql)
			.addParameter("name", this.name)
			.addParameter("description", this.description)
			.addParameter("location", this.location)
			.executeUpdate();
		}
	}


	public static List<Task> all() {
		String sql = "SELECT * FROM tasks";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql).executeAndFetch(Task.class);
		}
	}


}