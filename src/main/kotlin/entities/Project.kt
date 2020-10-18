package dev.alpas.fireplace.entities

import dev.alpas.ozone.*
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.entity.Entity
import me.liuwj.ktorm.entity.findList
import me.liuwj.ktorm.schema.*
import java.time.Instant

interface Project : OzoneEntity<Project> {
    var id: Long
    var title: String?
    val description: String
    val notes: String?
    val owner: User
    val tasks get() = hasMany(Tasks)
    val activities get() = hasMany(Activities)
    var createdAt: Instant?
    var updatedAt: Instant?

    companion object : OzoneEntity.Of<Project>()
}

object Projects : OzoneTable<Project>("projects") {
    val id by bigIncrements()
    val title by mediumText("title").bindTo { it.title}
    val description by text("description").bindTo { it.description}
    val notes by text("notes").nullable().bindTo { it.notes}
    val ownerId by long("owner_id").belongsTo(Users) { it.owner}

    val createdAt by timestamp("created_at").useCurrent().bindTo { it.createdAt }
    val updatedAt by timestamp("updated_at").useCurrent().bindTo { it.updatedAt }
}




