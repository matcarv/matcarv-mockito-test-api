
# MatCarv - Mockito Test API

API completa de exemplo utilizando Spring Boot, Mockito, OpenAPI, validações, cobertura de testes (JaCoCo), banco H2/MySQL, e Docker.

## Tecnologias e Features
- Java 17
- Spring Boot 3.x (Web, Data JPA, Security, Validation, Actuator)
- OpenAPI/Swagger UI (springdoc)
- Banco de dados H2 (dev/test) e MySQL (prod)
- Mockito e JUnit 5 para testes unitários
- JaCoCo para cobertura de testes
- Lombok
- Docker

## Estrutura do Projeto
```
src/main/java/com/matcarv/app/
	Application.java
	entities/        # Entidades: Pessoa, Cliente, Vendedor, Endereco
	dtos/            # DTOs: ClienteDTO, VendedorDTO, PedidoDTO
	business/        # Regras de negócio: ClienteBusiness, VendedorBusiness, Impl
	repository/      # Repositórios JPA
	converters/      # Conversores DTO <-> Entity
	resources/       # Controllers REST: ClienteResource, VendedorResource
	enums/           # Enums auxiliares
src/main/resources/
	application.yml  # Configuração do Spring Boot
src/test/java/com/matcarv/app/
	business/        # Testes unitários das regras de negócio
	resources/       # Testes unitários dos controllers
	converters/      # Testes unitários dos converters
```


## Documentação Javadoc
Após rodar o comando `mvn javadoc:javadoc` e copiar a pasta gerada para `src/main/resources/static/apidocs`, a documentação Javadoc estará disponível via navegador em:

```
http://localhost:8080/apidocs/index.html
```

## Endpoints Principais
Todos os endpoints estão documentados via OpenAPI/Swagger em `/mockito/v1/swagger-ui.html`.

- `POST   /mockito/v1/cliente`         - Cria cliente
- `PUT    /mockito/v1/cliente`         - Atualiza cliente
- `DELETE /mockito/v1/cliente/{id}`    - Remove cliente
- `GET    /mockito/v1/cliente/{id}`    - Busca cliente por ID
- `GET    /mockito/v1/cliente/cpf/{cpf}` - Busca cliente por CPF
- `POST   /mockito/v1/vendedor`        - Cria vendedor
- `PUT    /mockito/v1/vendedor`        - Atualiza vendedor
- `DELETE /mockito/v1/vendedor/{id}`   - Remove vendedor
- `GET    /mockito/v1/vendedor/{id}`   - Busca vendedor por ID
- `GET    /mockito/v1/vendedor/cpf/{cpf}` - Busca vendedor por CPF

## Como rodar localmente
1. Gere o JAR:
	 ```sh
	 mvn clean package
	 ```
2. Execute:
	 ```sh
	 java -jar target/*.jar
	 ```
3. Acesse: http://localhost:8080/mockito/v1/swagger-ui.html

## Docker
1. Gere o JAR:
	 ```sh
	 mvn clean package
	 ```
2. Build da imagem:
	 ```sh
	 docker build -t matcarv-mockito-test-api .
	 ```
3. Execute o container:
	 ```sh
	 docker run -p 8080:8080 matcarv-mockito-test-api
	 ```

## Configuração (application.yml)
Exemplo de configuração para MySQL:
```yaml
spring:
	datasource:
		url: jdbc:mysql://host.docker.internal:3306/mockito_db
		username: root
		password: mockito123
```
Altere conforme necessário para seu ambiente.

## Testes e Cobertura
- Execute todos os testes:
	```sh
	mvn clean test
	```
- Geração do relatório de cobertura:
	```sh
	mvn jacoco:report
	# Abra target/site/jacoco/index.html
	```

## Estrutura dos Testes
- Testes de business: `src/test/java/com/matcarv/app/business/`
- Testes de resource: `src/test/java/com/matcarv/app/resources/`
- Testes de converter: `src/test/java/com/matcarv/app/converters/`

## Observações
- O projeto cobre validações, tratamento de erros, autenticação básica, documentação automática e exemplos de testes unitários com Mockito.
- Para ambiente Docker, utilize o host `host.docker.internal` para acessar o MySQL local.

---
Desenvolvido por MatCarv
