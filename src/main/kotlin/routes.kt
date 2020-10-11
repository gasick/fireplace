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
        authRoutes()
    }.middlewareGroup("web")

    apiRoutes()
}

private fun RouteGroup.webRoutesGroup() {
    get("/", WelcomeController::index).name("welcome")
    // register more web routes here
    get("/projects", ProjectController::index).name("projects.list").mustBeAuthenticated()
    get("/projects/create", ProjectController::create).name("projects.create").mustBeAuthenticated()
    post("/projects", ProjectController::store).name("projects.store").mustBeAuthenticated()
}

private fun Router.apiRoutes() {
    // register API routes here
}
