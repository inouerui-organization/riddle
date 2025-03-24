package jp.mentor.app.infra.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import jp.mentor.app.domain.value.Age

/**
 * Ageオブジェクトのコンバーター.
 * @author rui.inoue
 */
@Converter(autoApply = true)
class AgeConverter: AttributeConverter<Age, Int> {

    override fun convertToDatabaseColumn(age: Age?): Int? {
        return age?.value
    }

    override fun convertToEntityAttribute(value: Int?): Age? {
        return value?.let { (Age(it)) }
    }
}