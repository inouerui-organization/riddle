package jp.mentor.app.api.request

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

/**
 * サンプル用のリクエストデータクラス
 *
 * @author rui.inoue
 */
data class SampleRequest(
    @field:NotBlank(message = "名前は必須です")
    val name: String,

    @field:Email(message = "メールアドレスの形式が正しくありません")
    val mail: String?,

    @field:Min(value = 0, message = "年齢は0歳以上である必要があります")
    @field:Max(value = 120, message = "年齢は120歳以下である必要があります")
    val age: Int?
) {
    @AssertTrue(message = "メールか年齢のどちらかの入力は必須です")
    fun isMailOrAge(): Boolean {
        return !mail.isNullOrBlank() || age != null;
    }
}
