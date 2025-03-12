package jp.mentor.app.domain.model

import jakarta.persistence.*
import jp.mentor.app.domain.`object`.Age
import jp.mentor.app.domain.`object`.Email

/**
 * サンプルのエンティティ.
 *
 * @author rui.inoue
 */
@Entity
@Table(name = "samples", schema = "app")
data class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "mail")
    val mail: Email? = null,
    @Column(name = "age")
    val age: Age? = null
)
