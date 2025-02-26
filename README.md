# Blood Bank API

A Blood Bank API é uma API desenvolvida para gerenciar doadores de sangue e questões relacionadas a doações. Ela oferece operações CRUD (Create, Read, Update, Delete) para gerenciar doadores e questões, além de fornecer endpoints para monitoramento e interação com o sistema através do Spring Boot Actuator.
## Documentação da API

A API está documentada usando o padrão OpenAPI 3.1.0. Abaixo estão os principais endpoints e funcionalidades disponíveis.
Base URL

A API está disponível em:
```
    http://localhost:8080
```

Endpoints Principais

### 1. Gerenciamento de Doadores

   ****GET**** /v1/donor: Retorna uma lista de todos os doadores.

   **POST** /v1/donor: Cria um novo doador.

   **GET** /v1/donor/{documentId}: Retorna os detalhes de um doador específico com base no documentId.

   **PUT** /v1/donor/{documentId}: Atualiza os detalhes de um doador específico.

   **DELETE** /v1/donor/{documentId}: Remove um doador específico.

   **GET** /v1/donor/paging: Retorna uma lista paginada de doadores.

2. ### Gerenciamento de Questões

   **GET** /v1/question: Retorna uma lista de todas as questões.

   **POST** /v1/question: Cria uma nova questão.

   **GET** /v1/question/{code}: Retorna os detalhes de uma questão específica com base no code.

   **PUT** /v1/question/{code}: Atualiza os detalhes de uma questão específica.

   **PUT** /v1/question/activate/{code}: Ativa ou desativa uma questão específica.

   **GET** /v1/question/paging: Retorna uma lista paginada de questões.

3. ### Monitoramento e Interação (Actuator)

   **GET** /actuator: Retorna links para todos os endpoints do Actuator.

   **GET** /actuator/health: Retorna o status de saúde da aplicação.

   **GET** /actuator/info: Retorna informações personalizadas sobre a aplicação.

   **GET** /actuator/metrics: Retorna métricas da aplicação.

   **GET** /actuator/loggers: Retorna a configuração dos loggers.

   **POST** /actuator/loggers/{name}: Configura o nível de um logger específico.

   **GET** /actuator/threaddump: Retorna um dump das threads em execução.

   **GET** /actuator/heapdump: Retorna um dump da memória heap.

## Modelos de Dados

### 1. QuestionDTO

   code: Código único da questão (string).

   questionText: Texto da questão (string).

   mandatory: Indica se a questão é obrigatória (boolean).

   active: Indica se a questão está ativa (boolean).

### 2. DonorDTO

   name: Nome do doador (string).

   documentId: Documento de identificação do doador (string).

   email: E-mail do doador (string).

   contactNumber: Número de contato do doador (string).

   gender: Gênero do doador (string).

   emergencyContactName: Nome do contato de emergência (string).

   emergencyContactNumber: Número do contato de emergência (string).

   observation: Observações sobre o doador (string).

   isTwins: Indica se o doador é gêmeo (boolean).

   twinsNames: Lista de nomes dos gêmeos (array de strings).

### 3. Link

   href: URL do link (string).

   templated: Indica se o link é um template (boolean).

### Como Usar

- Instalação: Certifique-se de ter o Java e o Spring Boot instalados.

- Execução: Execute o projeto Spring Boot para iniciar o servidor.

- Testes: Utilize ferramentas como Postman ou cURL para testar os endpoints.

- Documentação: Acesse a documentação Swagger em http://localhost:8080/swagger-ui.html para explorar a API de forma interativa.

### Exemplos de Requisições

Criar um Doador

**POST** /v1/donor
```json

{
    "name": "João Silva",
    "documentId": "123456789",
    "email": "joao.silva@example.com",
    "contactNumber": "11987654321",
    "gender": "Male",
    "emergencyContactName": "Maria Silva",
    "emergencyContactNumber": "11987654322",
    "observation": "Doador regular",
    "isTwins": false,
    "twinsNames": []
}
```
Criar uma Questão

**POST** /v1/question
```json

{
    "code": "Q001",
    "questionText": "Você já doou sangue antes?",
    "mandatory": true,
    "active": true
}
```

**_Contribuição_**

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorar a API.
