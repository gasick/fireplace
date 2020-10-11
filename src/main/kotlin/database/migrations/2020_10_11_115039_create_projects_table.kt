package dev.alpas.fireplace.database.migrations

import dev.alpas.fireplace.entities.Projects
import dev.alpas.ozone.migration.Migration

class CreateProjectsTable : Migration() {
    override var name = "2020_10_11_115039_create_projects_table"
    
    override fun up() {
        createTable(Projects)
    }
    
    override fun down() {
        dropTable(Projects)
    }
}