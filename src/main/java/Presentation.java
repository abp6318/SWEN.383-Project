import static spark.Spark.*;

public class Presentation {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }

}
