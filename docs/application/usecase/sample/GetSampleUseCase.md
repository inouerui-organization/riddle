# GetSampleUseCase

## Overview

- Sampleを取得するユースケースです

## Field Params

| 型                | 説明                      |
|:-----------------|:------------------------|
| SampleRepository | `samples`テーブルを操作するリポジトリ |

## Method Params

| 名前 | 型   | 説明        |
|:---|:----|:----------|
| id  | Int | sampleのid |

## Return

| 型            | 説明                             |
|:-------------|:-------------------------------|
| SampleResult | サンプルエンティティの取得結果を表すデータクラス       |

## シーケンス図

```mermaid
sequenceDiagram
  autonumber
  participant Caller
  participant usecase as CreateSampleUseCase
  participant repository as SampleRepository
  
  Caller ->>+ usecase: Callerからの呼び出し
  usecase ->>+ repository: サンプルの取得
  repository -->>- usecase: サンプルの受け取り
  usecase -->>- Caller: サンプルを返す
```