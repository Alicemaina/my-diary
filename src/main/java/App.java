import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      
      model.put("view", "templates/index.vtl");

      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/tasks/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      
      model.put("view", "templates/tasks-form.vtl");
 
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      String location = request.queryParams("location");
      // int location = Integer.parseInt(request.queryParams("location"));
      Task task = new Task(name, description, location);
      task.save();
      model.put("view", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/tasks/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Task task = Task.find(Integer.parseInt(request.params(":id")));
            
      model.put("task", task);
      model.put("view", "templates/task.vtl");

      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

  } 

}
