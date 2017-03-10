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

    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }

    setPort(port);

    // indexpeg
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      
      model.put("view", "templates/index.vtl");

      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/sign-in", (request, response) -> {
      Map<String, Object>model = new HashMap<String, Object>();
      model.put("view", "templates/loginF.vtl");
      return new ModelAndView(model, layout);
    },  new VelocityTemplateEngine());

// lonig peg
    // post("/", (request, response) -> {
    //         Map<String, Object> model = new HashMap<String, Object>();

    //         String email = request.queryParams("email");
    //         String pword = request.queryParams("password");

    //         Login login = new Login(email, pword);

    //         if (Login.login() == true) {
    //             model.put("view", "templates/tasks.vtl");   
    //         }else {
    //             model.put("view", "templates/index.vtl");

    //         //     String url = String.format("view", "templates/index.vtl", ());
    //         // response.redirect(url);
    //         }

    //         model.put("view", "templates/admin.vtl");

    //         return new ModelAndView(model,layout);
    //     }, new VelocityTemplateEngine());

    //   get("/login", (request, response) -> {
    //     Map<String, Object> model = new HashMap<String, Object>();

    //     model.put("tasks", Tasks.getTasks());
    //     model.put("view", "templates/loginF.vtl");

    //     return new ModelAndView(model,layout);
    //   }, new VelocityTemplateEngine());



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

    // get("/tasks", (request, response)-> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Task existingTask = Task.find(Integer.parseInt(request.params(":id")));
    //   model.put("task", existingTask);
    //   model.put("templates", "templates/tasks.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());


    /**/
    

    get("/tasks/:id",(request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params(":id")));
      model.put("task", task);

      model.put("view", "templates/updateTask.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    post("/tasks/:id",(request, response)->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params("id")));

      String newName = request.queryParams("name");
      String newDescription = request.queryParams("description");
      String newLocation = request.queryParams("location");
    
      task.update(newName);
      task.update(newLocation);
      task.update(newDescription);
      model.put("task", task);
      String url = String.format("/tasks/%d",task.getId());
      response.redirect(url);
      return null;

    });


    post("/tasks/:id/delete", (request, response)->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task tasks =Task.find(Integer.parseInt(request.params("id")));
      tasks.delete();
      model.put("tasks", tasks);
      model.put("view", "templates/success.vtl");
      return new ModelAndView(model, layout);
      // response.redirect("/tasks");
      // return null;
    },  new VelocityTemplateEngine());

  
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

 }
}