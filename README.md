# challenge-literalura
Projeto desenvolvido no desafio Alura + Oracle One para praticar lógica de programação em Java.
### O que o projeto faz?
Este programa é um catálogo de livros que utiliza a API Gutendex para buscar informações de livros e autores do Project Gutenberg.
Ele permite:
- Buscar livro por título e salvar no banco de dados.
- Listar todos os livros cadastrados.
- Listar livros por idioma.
- Listar todos os autores.
- Listar autores vivos em determinado ano.
O usuário interage pelo terminal, escolhendo opções no menu e digitando os dados solicitados.
### Como rodar o projeto
**1.** Pré-requisitos
- Java 17+ instalado.
- Maven instalado.
- PostgreSQL instalado e rodando.
**2.** Configuração do banco
No arquivo src/main/resources/application.properties, configure:
```text
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
Substitua seu_usuario e sua_senha pelas credenciais do seu PostgreSQL.
**3.** Estrutura do projeto
Certifique-se de que o projeto está organizado assim:
```text
LiterAlura
└─ src/
   └─ main/java/com/literalura/
       ├─ model/
       │   ├─ Autor.java
       │   └─ Livro.java
       ├─ repository/
       │   ├─ AutorRepository.java
       │   └─ LivroRepository.java
       ├─ service/
       │   └─ GutendexService.java
       └─ LiteraluraApplication.java
└─ src/main/resources/
   └─ application.properties
└─ pom.xml
```
**4.** No terminal, dentro da pasta do projeto:
```text
mvn clean compile
mvn spring-boot:run
```
O programa exibirá o menu interativo no terminal, onde você poderá selecionar as opções disponíveis.
### Observações
- O programa utiliza a API Gutendex, que não requer chave de acesso.
- Apenas o primeiro idioma e o primeiro autor de cada livro são considerados, conforme especificação do desafio.
- O banco de dados PostgreSQL será preenchido automaticamente ao buscar livros no menu.
- Você pode adicionar mais funcionalidades, como estatísticas ou filtros avançados, editando os métodos existentes.
- Utilizei o Visual Studio Code como ambiente de desenvolvimento.
