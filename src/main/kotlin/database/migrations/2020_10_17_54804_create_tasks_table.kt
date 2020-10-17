package dev.alpas.fireplace.database.migrations

import dev.alpas.fireplace.entities.Tasks
import dev.alpas.ozone.migration.Migration

class CreateTasksTable : Migration() {
    override var name = "2020_10_17_54804_create_tasks_table"
    
    override fun up() {
        createTable(Tasks)
    }
    
    override fun down() {
        dropTable(Tasks)
    }
}