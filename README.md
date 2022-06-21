# Ingresso-Facil-API
Olá! Esta API tem o objetivo de simular um sistema de cadastro e compra de ingressos para eventos.
Você precisa ter o LOMBOK instalado na sua IDE para conseguir rodar.

Você pode acessar a todos endpoints através do Swagger. O caminho de teste que sugiro é o mesmo que descrevi as funcionalidades (primeiro sem autenticacao, depois como admin e depois como cliente)

Após iniciar o servidor da API acesse o swagger através do link abaixo:

http://localhost:8080/swagger-ui.html

#### Você pode utilizar os JSON pré-configurados no swagger.

## Tecnologias utilizadas
  - Java com Spring Boot
  - Spring Security com token JWT
  - Spring Data JPA
  - Spring Validation
  - Banco de dados MySQL
  - FlyWay para versionamento do banco de dados (cria e popula o banco de dados ao iniciar o sistema)
  - Documentação com SWAGGER
  - Java Mail Sender para envio de email (ao cadastrar um cliente, utilize seu email para dar certo o envio de emails)

### Validações
#### • Em nenhum formulário podem haver dados em branco ou nulos
#### • O mesmo email não pode ser cadastrado novamente
#### • Senha criada precisa ter de 8 a 30 caracteres, pelo menos 1 letra maiuscula, 1 minuscula, 1 numero, 1 caractere especial
#### • Não pode haver nomes de Locais repetidos
#### • Não é no carrinho aceito IDs de cliente ou Evento que não existam
#### • Se não houver estoque suficiente para compra, ela não é efetuada

## Funcionalidades que não precisam de autenticação
•	Visualizar eventos disponíveis

•	Fazer cadastro de cliente

•	Cliente recebe email sinalizando que seu cadastro deu certo

•	Autenticar-se na plataforma
## Funcionalidades administrador
•	Cadastro local de evento (CRUD)

•	Cadastro de evento (CRUD)
## Funcionalidades cliente
•	Colocar eventos no carrinho de compras (para retirar um item, basta você zerar a quantidade dele)

•	Visualizar carrinho de compras

•	Comprar todos itens dentro do carrinho de compras

•	Recebe email após compra ser concluida

•	Visualizar histórico de compras


