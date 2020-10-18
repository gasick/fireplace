package dev.alpas.fireplace.database.migrations

import dev.alpas.fireplace.entities.Activities
import dev.alpas.ozone.migration.Migration

class CreateActivitiesTable : Migration() {
    override var name = "2020_10_18_110933_create_activities_table"
    
    override fun up() {
        createTable(Activities)
    }
    
    override fun down() {
        dropTable(Activities)
    }
}