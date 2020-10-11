package dev.alpas.fireplace

import dev.alpas.auth.authRoutes
import dev.alpas.fireplace.controllers.ProjectController
import dev.alpas.fireplace.controllers.WelcomeController
import dev.alpas.routing.RouteGroup
import dev.alpas.routing.Router

// https://alpas.dev/docs/routing
fun Router.addRoutes() = apply {
    group {
        webRoutesGroup()
        authRoutes(addHomeRoute = false)
    }.middlewareGroup("web")

    apiRoutes()
}

private fun RouteGroup.webRoutesGroup() {
    get("/", WelcomeController::index).name("welcome")
    // register more web routes here
    group("/projects"){
        addProjectRoutes()
    }.name("projects").mustBeAuthenticated()
}


private fun RouteGroup.addProjectRoutes(){
    get("/", ProjectController::index).name("list")
    get("/create", ProjectController::create).name("create")
    post("/", ProjectController::store).name("store")
}

private fun Router.apiRoutes() {
    // register API routes here
}
