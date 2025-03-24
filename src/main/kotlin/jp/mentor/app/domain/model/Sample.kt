package jp.mentor.app.domain.model

import jakarta.persistence.*
import jp.mentor.app.domain.value.Age
import jp.mentor.app.domain.value.Email
import java.util.*

/**
 * サンプルのエンティティ.
 *
 * @author rui.inoue
 */
@Entity
@Table(name = "samples", schema = "app")
data class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "mail")
    val mail: Email? = null,
    @Column(name = "age")
    val age: Age? = null
)
