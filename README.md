# QuizQuiz

#### Tecnologias necessárias
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

Uma API para um app de quiz, onde poderiam ser criados quizzes para o usuário. A proposta é baseada em quizzes avaliativos do próprio usuário, ou seja, não são perguntas que existem respostas certas ou erradas mas perguntas cuja resposta o usuário irá escolher a que mais se identifica com ele.
Ex: "Qual nota você daria a ...?"

## Visão Geral

QuestionCategory: Categoria da questão.
Question: Questão que o usuário irá responder

## Recursos
Os recursos foram pensados com base em um levantamento de requisitos:

- QuestionCategory
  - Criar categoria;
  - Retornar todas as categoria;
  - Retornar categoria por nome;
  - Retornar categoria por Id;
  - Atualizar nome da categoria;
  - Deletar categoria;

- Question:
    - Post: Criar questão (Precisa ser passado no JSON uma categoria já existente);
    - Post: Responder Questão;
    - Atualizar questão;
    - Deletar questão.

- Usuário
    - Criar usuário (Uma espécie de SignUp);
    - Retornar todos os usuários; 
    - Retornar um usuário por id;
    - Atualizar email;
    - Atualizar avatar
    - Deletar dados do usuário.

## Instalação
- Clone o projeto com:
  ``git clone https://github.com/joaogdantas/QuizQuizAPI``

- Executar com o comando:
  ``mvn spring-boot:run``
  ou
  ``./mvnw spring-boot:run``

Após isso a documentação em swagger ficará disponível no link abaixo, podendo ser acessada e testada:
``http://localhost:8080/swagger-ui/index.html``

## Próximos passos

Alguns pontos que ainda preciso adicionar que poderiam ser úteis, mas como se tratava de uma criação de API simples para demonstração e estudo, acabei não adicionando ainda.

- Por motivos de facilitar o acesso a API, acabei não adicionando nada de spring security, isso seria algo a adicionar futuramente, como aumentar a complexidade dessa classe usuário, criando SignUp e Login com JWT Token;
- Ainda necessário serem adicionados testes unitários com cumcuber;
- Adicionar novas entidades para aumentar a complexidade;
- Como desenvolvedor backend, não tenho experiência e conhecimento sólido em tecnologias de frontend, estou estudando android e swift para desenvolver os apps para diversas plataformas e dar uma interface gráfica à API.

## Meu Linkedin:

https://www.linkedin.com/in/joaogdantas/

### FIQUE À VONTADE PARA CONTRIBUIR