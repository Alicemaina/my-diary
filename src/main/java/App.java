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
      // model.put("my-diary", my-diary.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

//   get("/about us", (request, response) -> {
//     Map<String, Object> model = new HashMap<String, Object>();
//     model.put("view", "templates/about us.vtl");
//     // model.put("my-diary", my-diary.all());
//     return new ModelAndView(model, layout);
//   }, new VelocityTemplateEngine());
//
//
}
