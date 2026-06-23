# Baseball Boot

棒球隊成績管理系統

這是一個使用 Spring Boot 開發的棒球成績管理網站，提供球隊紀錄與管理球員成績，並整合 MLB 排行榜資料。

---

## 功能介紹

### 使用者管理

- 球隊註冊
- 球隊登入
- BCrypt 密碼加密
- JWT 身分驗證

### 球員成績管理

#### 投手

- 新增成績
- 查詢成績
- 修改成績
- 刪除成績

#### 打者

- 新增成績
- 查詢成績
- 修改成績
- 刪除成績

### 權限控制

- 每支球隊只能管理自己的資料
- JWT Token 驗證
- 防止未授權操作

### MLB 排行榜

- 本季排行榜
- 生涯排行榜

排行榜資料由 MLB Stats API 取得。

---

## 使用技術

### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- Liquibase
- JUnit 5
- Mockito

### Database

- MariaDB

### Frontend

- HTML
- CSS
- JavaScript

### Other

- Git
- GitHub
- Eclipse
- Postman

## 單元測試

本專案使用 JUnit 5 與 Mockito 撰寫單元測試。

已測試功能：

### ClubServiceImpl
- 註冊
- 登入

### BatterStatsServiceImpl
- 新增打者資料
- 查詢打者資料
- 修改打者資料
- 刪除打者資料
---

## 專案架構

```text
src
├─ main
│  ├─ java
│  ├─ resources
│  │  ├─ static
│  │  │  ├─ html
│  │  │  ├─ css
│  │  │  └─ js
│  │  └─ db
│  │     └─ changelog
```
---

## 系統畫面

後續可加入畫面截圖。

---

## 未來規劃

- 使用者介面優化
- 排行榜頁面美化
- 球員資料統計分析
- 即時更新MLB球員紀錄
- 雲端部署

---

## 作者

GitHub：

https://github.com/ohtatin
