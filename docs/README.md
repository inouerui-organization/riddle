# docs

このアプリケーションはレイヤードアーキテクチャを採用します。以下でクイズアプリを例にChatGPTでの具体例

## 1. DDDの基本構成
   クイズ投稿アプリをドメイン駆動設計（DDD）で開発する場合、以下の4つのレイヤーに分けて設計します。

### ✅ 主要なレイヤー
1. ドメイン層（Domain Layer） 
    - エンティティ（Quiz, User, Answer など） 
    - 値オブジェクト（Choice など） 
    - 集約（アグリゲート）（Quiz が Answer を持つ など） 
    - ドメインサービス（ビジネスルールを扱う）

2. アプリケーション層（Application Layer） 
    - ユースケース（クイズの投稿、編集、削除、回答の登録 など） 
    - サービスクラス（QuizService, UserService, AnswerService）

3. インフラストラクチャ層（Infrastructure Layer）
    - データベースのリポジトリ（DBアクセスの実装）
    - 外部サービスとの連携（認証、メール送信 など）

4. インターフェース層（Interface Layer）
    - REST API（Controller）
    - DTO（Data Transfer Object）を用いたデータ変換

## 2. 各レイヤーの詳細
### 📌 ① ドメイン層（Domain Layer）
ドメイン層はビジネスルールをカプセル化するレイヤー で、DDDの中心となる部分です。

#### (1) エンティティ
エンティティは、一意な識別子（ID）を持つオブジェクト です。

##### ✅ Quiz（クイズ）エンティティ
```kotlin
package com.example.quizapp.domain.model

import java.util.UUID

data class Quiz(
   val id: UUID = UUID.randomUUID(),
   val title: String,
   val choices: List<Choice>,
   val correctAnswer: Choice
)
```

##### ✅ User（ユーザー）エンティティ
```kotlin
package com.example.quizapp.domain.model

import java.util.UUID

data class User(
   val id: UUID = UUID.randomUUID(),
   val name: String,
   val email: String
)
```

##### ✅ Answer（回答）エンティティ
```kotlin
package com.example.quizapp.domain.model

import java.util.UUID

data class Answer(
   val id: UUID = UUID.randomUUID(),
   val userId: UUID,
   val quizId: UUID,
   val selectedChoice: Choice
)
```

#### (2) 値オブジェクト
値オブジェクトはIDを持たず、等価性で判断されるオブジェクト です。

##### ✅ Choice（選択肢）値オブジェクト
```kotlin
package com.example.quizapp.domain.model

data class Choice(
   val text: String
)
```

#### (3) ドメインサービス
ドメインロジックが 単一のエンティティに属さない場合、ドメインサービスに処理を移します。

##### ✅ クイズの正解チェック
```kotlin
package com.example.quizapp.domain.service

class QuizDomainService {
   fun isCorrectAnswer(quiz: Quiz, answer: Choice): Boolean { 
       return quiz.correctAnswer == answer
   }
}
```

### 📌 ② アプリケーション層（Application Layer）
アプリケーション層では、ユースケースの流れを定義 します。

#### (1) QuizService（クイズ投稿や取得）
```kotlin
package com.example.quizapp.application.service

import com.example.quizapp.domain.model.Quiz
import com.example.quizapp.domain.repository.QuizRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class QuizService(private val quizRepository: QuizRepository) {

    fun createQuiz(title: String, choices: List<String>, correctChoice: String): Quiz {
        val choiceObjects = choices.map { Choice(it) }
        val correctAnswer = Choice(correctChoice)
        val quiz = Quiz(title = title, choices = choiceObjects, correctAnswer = correctAnswer)
        return quizRepository.save(quiz)
    }

    fun getQuiz(id: UUID): Quiz? {
        return quizRepository.findById(id)
    }
}
```

### 📌 ③ インフラストラクチャ層（Infrastructure Layer）
このレイヤーでは、データの永続化や外部サービスとの連携 を行います。

#### (1) QuizRepository（リポジトリ）
```kotlin
package com.example.quizapp.domain.repository

import com.example.quizapp.domain.model.Quiz
import java.util.UUID

interface QuizRepository {
    fun save(quiz: Quiz): Quiz
    fun findById(id: UUID): Quiz?
}
```

#### (2) QuizRepositoryの実装
```kotlin
package com.example.quizapp.infrastructure.repository

import com.example.quizapp.domain.model.Quiz
import com.example.quizapp.domain.repository.QuizRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class QuizRepositoryImpl : QuizRepository {
private val quizzes = mutableMapOf<UUID, Quiz>()

    override fun save(quiz: Quiz): Quiz {
        quizzes[quiz.id] = quiz
        return quiz
    }

    override fun findById(id: UUID): Quiz? {
        return quizzes[id]
    }
}
```

### 📌 ④ インターフェース層（Interface Layer）
このレイヤーでは、ユーザーとのやり取りを担当するAPIを提供 します。

#### (1) QuizController
```kotlin
package com.example.quizapp.interface.controller

import com.example.quizapp.application.service.QuizService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/quizzes")
class QuizController(private val quizService: QuizService) {

    @PostMapping
    fun createQuiz(@RequestBody request: QuizRequest): ResponseEntity<QuizResponse> {
        val quiz = quizService.createQuiz(request.title, request.choices, request.correctChoice)
        return ResponseEntity.ok(QuizResponse(quiz.id, quiz.title))
    }

    @GetMapping("/{id}")
    fun getQuiz(@PathVariable id: UUID): ResponseEntity<QuizResponse> {
        val quiz = quizService.getQuiz(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(QuizResponse(quiz.id, quiz.title))
    }
}
```

### (2) DTO（データ変換用）
```kotlin
data class QuizRequest(
    val title: String,
    val choices: List<String>,
    val correctChoice: String
)

data class QuizResponse(
    val id: UUID,
    val title: String
)
```