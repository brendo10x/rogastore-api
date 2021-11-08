# API de Loja de Aplicativos (Desafio da Roga Labs)

## Requisitos
Seu desafio será desenvolver uma API de loja de aplicativo e, como parte dele, veremos como você estrutura as camadas de aplicação, variáveis de ambiente, cache, testes unitários, logs e documentação.
* Cadastro de apps
* Busca de aplicativos por nome e tipo
* Busca de app mais barato por tipo
## Solução

### Para rodar a aplicação
Requisitos
* Java 11
* Docker e Docker Compose
* Maven 3.6.3

Basta executar os seguintes comandos dentro da pasta raiz do projeto:
```bash
git clone git@github.com:brendo10x/rogastore-api.git
```
```bash
mvn clean install
```
```bash
docker-compose up --build --force-recreate
```

### Documentação e acesso a todos os recursos da API
#### Documentação completa
```bash
http://localhost:8080/swagger-ui/
```
#### Cadastro de apps
```bash
http://localhost:8080/api/v1/apps/
```
#### Busca de aplicativos por nome e tipo
```bash
http://localhost:8080/api/v1/apps?name=Pomodoro&categoryId=1
```
```bash
http://localhost:8080/api/v1/apps?categoryId=1
```
```bash
http://localhost:8080/api/v1/apps?name=Pomodoro
```
#### Busca de app mais barato por tipo
```bash
http://localhost:8080/api/v1/apps/cheapest-by-category/2
```

### Ferramentas utilizadas
#### Kit de Desenvolvimento
* Java 11
* Spring Boot
* Maven
* PostgreSQL
* Docker
* Flyway
* ModelMapper
* JUnit
* Rest assured
* Lombok
* Spring Data JPA
* Swagger2
* Postman
* Spring Tools Suite

#### Padrões e boas práticas adotados no projeto
- Padrões de projeto: Strategy, Facade, Singleton, DTO, TDD, BDD e etc.
- Ágil: Kanban e Pomodoro
