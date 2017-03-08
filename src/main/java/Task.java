import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Task {
	private String name;
	private String location;
	private String description;
	private int id;
	// private Timestamp startingDate;
	// private Timestamp what;
	// private Timestamp endingDate;
	// private Timestamp at;


	public Task(String name, String description, String location) {
		this.name = name;
		this.description = description;
		this.location = location;
		// time = new Timestamp(new Date().getTime());

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

	public int getId() {
		return id;
	// }

	// public Timestamp getStartingDate(){
	// 	return startingDate;
	// }

	// public Timestamp getWhat() {
 //        return what;
 //    }

 //    public Timestamp getEndingDate(){
	// 	return endingDate;
	// }

	// public Timestamp getat() {
 //        return at;
    }
   

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
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.addParameter("description", this.description)
			.addParameter("location", this.location)
			.executeUpdate()
			.getKey();
		}
	}


	public static List<Task> all() {
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

	public void update(String name, String location, String description){
		try(Connection con = DB.sql2o.open()){
			String sql = "UPDATE tasks SET name = :name, description = :description, location = :location WHERE id = :id";
			con.createQuery(sql)
			.addParameter("name", this.name)
			.addParameter("description", this.description)
			.addParameter("location", this.location)
			.executeUpdate();
		}
	}

	public void delete() {
		try(Connection con = DB.sql2o.open()) {
			con.createQuery("DELETE FROM tasks WHER id = :id")
			.addParameter("id",id)
			.executeUpdate();
		}
	}

    // public String getFormattedDate(){
    //     SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd yyyy hh:mm a");
    //     return formatter.format(date);
    // }
}
