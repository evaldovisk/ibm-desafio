# Projeto de Sistema Bancário
Este projeto é uma proposta para um desafio técnico solicitado pela IBM, com o objetivo de desenvolver um sistema bancário simples.
## Requisitos da Aplicação
- **Cadastro de Clientes** com os campos: nome, idade, endereço de email e número da conta.
- **Cadastro de Débito e Crédito** nas contas dos clientes.
- **Exibição do Extrato** da conta do cliente com saldo total (no rodapé ou topo da página).
## Requisitos de Tecnologias
- **FrontEnd**: Angular
- **BackEnd**: Java (Spring Boot)
- **Banco de Dados**: MySQL, SQL Server, MongoDB ou PostgreSQL, ou ainda arquivo JSON.
## Documentação
Toda a documentação da API foi gerada automaticamente pelo Swagger e fica disponível após a subida da aplicação. Acesse a documentação na URL:
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
## Instalação
### Maquina Local
Siga os passos abaixo para rodar o projeto na sua máquina local.
#### 1. Clone o repositório
```bash
git clone https://github.com/evaldovisk/ibm-desafio.git
```
#### 2. Instale as dependências
Execute o seguinte comando para instalar as dependências necessárias:
```bash
./mvnw clean install
```
#### 3. Banco de Dados
Crie um banco de dados no PostgreSQL com as seguintes configurações:
- **Nome do Banco**: banco_ibm
- **Usuário**: user
- **Senha**: password
Ou altere o arquivo `application.yml` para utilizar o banco de dados, usuário e senha que você configurou no seu ambiente.
#### 4. Suba a aplicação
Na raiz do repositório, execute o seguinte comando para subir a aplicação:
```bash
./mvnw spring-boot:run
```
A aplicação estará disponível na porta **8080**. Certifique-se de que a porta esteja disponível em sua máquina.
### Docker
Se preferir, você pode rodar a aplicação e o banco de dados utilizando Docker para facilitar o processo de instalação.
#### Passo a Passo para Docker:
1. Navegue até o diretório raiz do projeto.
2. Execute o seguinte comando para subir a aplicação e o banco de dados:
```bash
docker-compose up -d
```
Esse comando irá criar os containers necessários para a aplicação e o banco de dados.
- O banco de dados será exposto na porta **5432**.
- A aplicação será exposta na porta **8080**.
Caso as portas não estejam disponíveis, você pode alterar as configurações no arquivo `docker-compose.yaml`.
## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Maven**
- **PostgreSQL 17**
- **Swagger** para documentação da API
- **Lombok** para simplificar o código
- **Docker** para containerização
## Agradecimentos
Obrigado por conferir este projeto! Se tiver dúvidas ou sugestões, fique à vontade para entrar em contato.
