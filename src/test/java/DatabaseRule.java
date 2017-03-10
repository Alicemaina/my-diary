import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

	@Override
  protected void before() {
  	DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/my_diary_test","alice", "nikita");
  }

  @Override
  protected void after() {
  	try(Connection con = DB.sql2o.open()) {
       String deleteTasksQuery = "DELETE FROM tasks *;";
       String deleteLoginsQuery = "DELETE FROM logins *;";
       con.createQuery(deleteTasksQuery).executeUpdate();
       con.createQuery(deleteLoginsQuery).executeUpdate();
    }
  }

}
