# challenge-literalura
Projeto desenvolvido no desafio Alura + Oracle ONE, com o objetivo de praticar lógica de programação, consumo de APIs externas e persistência de dados utilizando Java e Spring Boot.
### O que o projeto faz?
O LiterAlura é uma aplicação de console que funciona como um catálogo de livros, consumindo dados da API Gutendex (Project Gutenberg) e armazenando as informações em um banco de dados relacional.
A aplicação permite que o usuário interaja via terminal, escolhendo opções em um menu textual para consultar, armazenar e listar livros e autores.
O sistema permite:
- Buscar livros por título utilizando a API Gutendex.
- Salvar livros e autores no banco de dados.
- Listar todos os livros cadastrados.
- Listar livros por idioma.
- Listar todos os autores cadastrados.
- Listar autores vivos em um determinado ano.

**1.** Pré-requisitos

- Java 17.
- Spring Boot 3.2.x.
- Spring Data JPA.
- Maven.
- MySQL.
- Jackson.
- API Gutendex.

**2.** Configuração do banco de dados

Crie o banco de dados no MySQL:
```text
CREATE DATABASE literalura;
```
Em seguida, configure o arquivo src/main/resources/application.properties:
```text
spring.datasource.url=jdbc:mysql://localhost:3306/literalura
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
Substitua SEU_USUARIO e SUA_SENHA pelas credenciais do seu MySQL.

**3.** Estrutura do projeto

Certifique-se de que o projeto está organizado assim:
```text
LiterAlura
└─ src/
   └─ main/
      ├─ java/
      │  └─ br/com/literalura/
      │     ├─ model/
      │     │  ├─ Autor.java
      │     │  └─ Livro.java
      │     ├─ repository/
      │     │  ├─ AutorRepository.java
      │     │  └─ LivroRepository.java
      │     ├─ service/
      │     │  ├─ ConsumoApi.java
      │     │  └─ LivroService.java
      │     ├─ Menu.java
      │     └─ LiterAluraApplication.java
      └─ resources/
         └─ application.properties
└─ pom.xml
```

**4.** Execução da aplicação

No terminal, dentro da pasta do projeto:
```text
mvn clean compile
mvn spring-boot:run
```
O programa exibirá o menu interativo no terminal, onde você poderá selecionar as opções disponíveis.

### Observações
- A API Gutendex não requer chave de acesso.
- O projeto utiliza persistência automática com JPA/Hibernate.
- A aplicação foi desenvolvida para rodar exclusivamente via terminal.
- O ambiente de desenvolvimento utilizado foi o Visual Studio Code.
- O projeto atende integralmente aos requisitos do desafio proposto pela Alura.
