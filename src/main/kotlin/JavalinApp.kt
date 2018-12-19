import io.javalin.Javalin
import java.lang.Exception

class JavalinApp(private val port: Int) {

    //val controller = UserController(users)

    fun init() : Javalin {
        val app = Javalin.create().apply {
            port(port)
            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        }.start()

        app.get("/") { ctx -> ctx.json(mapOf("message" to "ola, mundo")) }

        /*app.routes{
            ApiBuilder.path("api") {
                ApiBuilder.path("users") {
                    ApiBuilder.path(":id") {
                        ApiBuilder.get { ctx -> controller.getUser(ctx) }

                    }
                }
            }

        }*/

        return app
    }

}

fun main(args: Array<String>){
    JavalinApp(7000).init()
}