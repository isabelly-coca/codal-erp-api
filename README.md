# 🚀 Codal ERP API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento completo de um sistema ERP.

## 📌 Funcionalidades

* 👤 Cadastro de usuários com autenticação
* 🔐 Login com JWT (segurança stateless)
* 🛒 Controle de vendas
* 📦 Gestão de produtos e estoque
* 🏢 Cadastro de clientes e fornecedores
* 🔄 Devolução e troca de produtos
* 🏷️ Categorias de produtos
* 🔒 Controle de acesso por roles (ADMIN / USER)

## 🛠️ Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT (Json Web Token)
* Spring Data JPA
* PostgreSQL
* Maven

## 🔐 Autenticação

A API utiliza autenticação via JWT.

### Login:

```http
POST /auth/login
```

### Exemplo:

```json
{
  "username": "admin",
  "password": "123456"
}
```

### Resposta:

```json
{
  "token": "seu_token_aqui"
}
```



## 🔑 Como usar o token

Em todas as requisições protegidas:

```
Authorization: Bearer SEU_TOKEN
```



## 📦 Estrutura do Projeto

```
com.codal.erp
├── controller
├── service
├── repository
├── model
├── dto
├── security
├── exception
```



## ⚙️ Configuração

⚠️ O arquivo `application.yaml` não está versionado por segurança.

Use o modelo abaixo:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5434/erp
    username: SEU_USUARIO
    password: SUA_SENHA

  jpa:
    hibernate:
      ddl-auto: update
```



## ▶️ Como rodar o projeto

```bash
# Clonar repositório
git clone https://github.com/SEU-USUARIO/codal-erp-api.git

# Entrar na pasta
cd codal-erp-api

# Rodar aplicação
./mvnw spring-boot:run
```



## 🌐 Deploy

(Em breve)



## 📊 Futuras melhorias

* Dashboard com gráficos
* Relatórios financeiros
* Integração com frontend (React)
* Deploy em nuvem



## 👩‍💻 Autora

Isabelly Fernanda Coca Dalmagro


