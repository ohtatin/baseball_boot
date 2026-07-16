# Baseball Boot

棒球隊成績管理系統

Baseball Boot 是一個使用 **Java 21 / Spring Boot** 開發的棒球成績管理網站，提供球隊註冊登入、球員成績 CRUD 管理、JWT 身分驗證與資料權限控制，並整合 MLB 球員排行榜資料。

專案已部署至 Render，並使用 Neon PostgreSQL 雲端資料庫。

---

## 線上網站

https://baseball-boot.onrender.com/html/index.html

> Render 使用免費方案，閒置後服務可能進入休眠，因此首次開啟網站時請稍等約一分鐘。

---

## 專案特色

* 使用 Spring Boot 建立 RESTful API
* 使用 Spring Security + JWT 實作身分驗證
* 使用 BCrypt 加密使用者密碼
* 使用 Jakarta Validation 驗證使用者輸入資料
* 使用 Global Exception Handler 統一處理 API 錯誤
* 使用 SLF4J Logger 記錄系統執行資訊
* 限制各球隊只能管理自己的球員資料
* 使用 Spring Data JPA 操作資料庫
* 使用 Liquibase 管理資料庫 Schema
* 使用 Python 取得與處理 MLB 球員資料
* 使用 JUnit 5 + Mockito 撰寫單元測試
* 部署至 Render + Neon PostgreSQL

---

## 核心功能

### 使用者管理

* 球隊註冊
* 球隊登入
* JWT Token 驗證
* BCrypt 進行密碼雜湊

### 球員成績管理

#### 投手

* 新增
* 查詢
* 修改
* 刪除

#### 打者

* 新增
* 查詢
* 修改
* 刪除

### 資料權限控制

系統依照 JWT Token 中的球隊資訊識別使用者，讓各球隊只能管理自己的球員資料，避免未授權操作。

### 資料驗證

使用 Jakarta Validation 驗證 API 輸入內容，例如：

* 必填欄位檢查
* 不可為負數
* 日期不可為未來日期

### 例外處理

使用 @ControllerAdvice 建立全域 Exception Handler，統一回傳 API 錯誤訊息，避免 Controller 重複撰寫錯誤處理程式。

### Logging

使用 SLF4J Logger 記錄系統重要事件，例如：

* 使用者登入
* 新增球員資料
* 修改球員資料
* 刪除球員資料
* 系統錯誤

### MLB 排行榜

* 本季排行榜
* 生涯排行榜

排行榜資料來源為 MLB Stats API。

---

## 使用技術

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* JWT
* BCrypt
* Jakarta Validation
* Global Exception Handler (@ControllerAdvice)
* SLF4J Logger
* Liquibase
* Maven

### Database

* PostgreSQL
* Neon PostgreSQL
* MariaDB

### Frontend

* HTML
* CSS
* JavaScript
* Fetch API

### Testing

* JUnit 5
* Mockito

### Other

* Python
* MLB Stats API
* Git
* GitHub
* Eclipse
* Postman
* Render

---

## 單元測試

本專案使用 JUnit 5 與 Mockito 撰寫 Service Layer 單元測試。

已測試：

### ClubServiceImpl

* 註冊
* 登入

### BatterStatsServiceImpl

* 新增打者資料
* 查詢打者資料
* 修改打者資料
* 刪除打者資料

---

## 系統架構

```text
Browser
   │
   ▼
HTML / CSS / JavaScript
   │
   ▼
Spring Boot REST API
   │
   ├── Spring Security + JWT
   ├── Service Layer
   └── Spring Data JPA
            │
            ▼
    Neon PostgreSQL
```

---

## 雲端部署

```text
Eclipse
   │
   │ Commit / Push
   ▼
GitHub
   │
   │ Auto Deploy
   ▼
Render
   │
   │ Database Connection
   ▼
Neon PostgreSQL
```

程式碼 Push 至 GitHub 後，由 Render 自動重新建置與部署，並連線至 Neon PostgreSQL 雲端資料庫。

---

## 未來規劃

* 增加 API 文件（Swagger）
* 優化使用者介面
* 增加球員數據分析功能
* 自動排程更新 MLB 球員資料


---

## 作者

GitHub：

https://github.com/ohtatin
