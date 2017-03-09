import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    // setPort(getHerokuPort());
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // indexpeg
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      
      model.put("view", "templates/index.vtl");

      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());


    get("/tasks", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("tasks", request.session().attribute("tasks"));
      model.put("tasks", Task.all());
      model.put("view", "templates/tasks-form.vtl");
      return new ModelAndView(model, layout);
  },new VelocityTemplateEngine());

    post("/tasks", (request, response)->{
      HashMap<String, Object> model = new HashMap<String,Object>();
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      String location = request.queryParams("location");
      // int location = Integer.parseInt(request.queryParams("location"));
      Task task = new Task(name, description, location);
      task.save();
      response.redirect("/tasks");
      return null;
    });

    get("/tasks/:id", (request, response)-> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task existingTask = Task.find(Integer.parseInt(request.params(":id")));
      model.put("task", existingTask);
      model.put("templates", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  
//     
//     get("/tasks", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();

//       model.put("tasks", Task.all());
//       model.put("view", "templates/tasks.vtl");

//       return new ModelAndView(model, layout);
//     },new VelocityTemplateEngine());

//     get("/tasks/:id", (request, response) -> {
//       Map<String, Object> model = new HashMap<String, Object>();

//       Task tasks = Task.find(Integer.parseInt(request.params(":id")));
            
//       model.put("tasks", tasks);
//       model.put("view", "templates/tasks.vtl");

//       return new ModelAndView(model, layout);
//     },new VelocityTemplateEngine());
   

// heroku application

    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }

    setPort(port);
 }
}