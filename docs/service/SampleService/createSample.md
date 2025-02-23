# createSample

## Overview

- sampleを作成するサービス。

## Params

| 引数      | 型 | 説明 |
|:--------| :--- | :---|
| sample  | Sample | sampleエンティティ|

## Return

- 更新したsampleエンティティ

## 処理の流れ
### シーケンス図

```mermaid
sequenceDiagram
    autonumber
    participant Caller
    participant service as SampleService
    participant repository as SampleRepository
    
    Caller ->>+ service: createSample(sample)
    service ->>+ repository: sampleエンティティを渡す
    repository -->>- service: 更新したsampleエンティティを返す
    service -->>- Caller: sampleエンティティを返す
```

#### 補足