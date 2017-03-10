// import java.util.*;
// import org.sql2o.*;

// public class Login {
// 	private int id;
// 	private String fname;
// 	private String lname;
// 	private static String email;
// 	private static String password;
// 	private static boolean login;
// 	// private int id;

// 	public Login(String email, String pword) {
// 		this.email 		= email;
// 		this.password 	= pword;
// 		login = false;
// 	}

// 	public static String getEmail() {
// 		return email;
// 	}

// 	public static String getPword() {
// 		return password;
// 	}

// 	public static boolean login() {
// 		try(Connection con = DB.sql2o.open()) {
// 			String sql = "SELECT * FROM admin WHERE email = :email";

// 			Login login = con.createQuery(sql).addParameter("email", email).executeAndFetchFirst(Login.class);

// 			return email.equals(login.getEmail()) && password.equals(login.getPword());
// 		}
// 	}

	// overide the equals method with our own
	// @Override
	// public boolean equals(Object otherTask) {
	// 	if (!(otherTask instanceof Tasks)) {
	// 		return false;
	// 	}else {
	// 		Tasks newTask = (Tasks) otherTask;
	// 		return this.getName2().equals(newTask.getName2()) && this.getId() == newTask.getId();
	// 	}
// 	// }
// }