# [2A] SpringBoot課題

### 詳細
https://docs.google.com/document/d/1E-Qku7WpZ9S93D8mOy5O0EuY2CLUcQVRxUanhOvlzVU/edit?tab=t.0

### 目的  
この課題は、Spring Boot と Thymeleaf を使用して、書籍の管理ができるシンプルなアプリケーションを作成することを目的としています。実際の業務でも活用できるように、データベースとの連携、認証・認可、テストなど、基本的な機能を段階的に学びながら実装します。

### 目標

- 実装されているコードが理解でき、軽微な修正対応ができる  
- 詳細設計とコードを元にテスト項目書を作成し、テストコードが書ける  
- チーム開発できる知見  
- GitHub・SourceTree・pgAdmin が使える  
- 設計書が読める  
- コードが読める（何の処理をしているのか説明できる）

---

## チュートリアル編（4週間）

基本的な CRUD 機能を持つ書籍管理アプリを作成します。Spring Boot と Thymeleaf を使用して、バックエンドとフロントエンドをシンプルに実装します。

#### 1週目：画面表示  
- Spring Boot プロジェクトをセットアップ  
- 書籍一覧を表示するページを作成  
- Thymeleaf を使ってサーバーサイドでページをレンダリング

#### 2週目：データベース連携  
- PostgreSQL と連携  
- 書籍の登録・編集・削除機能を実装  
- Thymeleaf フォームを使って書籍情報を管理

#### 3週目：認証/認可  
- Spring Security を導入  
- ユーザー認証機能を実装  
- ログインしたユーザーのみが書籍情報を管理できるように制限

#### 4週目：単体テスト（JUnit）  
- JUnit を使用して、サービス層やコントローラ層の単体テストを実施  
- サーバーサイドの処理が正しく動作することを確認

---

## 実践編（2週間）

仕様に基づいて、さらに高度な機能を追加します。ユーザー管理や書籍の貸し出し管理など、実務に即した機能を実装することで、業務で役立つスキルを養います。

#### 追加機能

- **検索機能**：書籍タイトルや著者で絞り込み検索  
- **貸し出し管理**：書籍の貸し出しと返却の機能を追加
