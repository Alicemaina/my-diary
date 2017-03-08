import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

	@Override
  protected void before() {
  	DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/my_dairy_test","J.EL", "1");
  }

  @Override
  protected void after() {
  	try(Connection con = DB.sql2o.open()) {
       String deleteClientsQuery = "DELETE FROM tasks *;";
       con.createQuery(sql).executeUpdate();
    }
  }

}
