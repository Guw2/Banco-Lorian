# --- Banco Lorian - Sistema Bancário API ---

[![Java](https://img.shields.io/badge/Java-21-%23ED8B00?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-%236DB33F?logo=spring)](https://spring.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8-%234479A1?logo=mysql)](https://www.mysql.com/)
![Docker](https://img.shields.io/badge/Docker-✓-lightblue)

> Sistema bancário robusto com autenticação JWT, controle de perfis e operações financeiras completas

## ✨ ————— Funcionalidades Principais ————— ✨

- 🔐 Autenticação JWT com perfis **USER** e **ADMIN**
- 💳 Gestão completa de contas, cartões e transações
- 🔄 Operações financeiras (transferências, saques, depósitos)
- 🛡️ Validações de segurança em todas as operações
- 📑 Documentação com Swagger

## 🚀 ————— Tecnologias ————— 🚀

- Java 21
- Spring Boot 3.5.0
- Spring Security
- MySQL 8
- Docker
- Flyway
- Validation
- Swagger (OpenAPI)

## 🚀————— Executando o Projeto ————— 🚀

### 🐳 Via Docker (Recomendado)
```bash
git clone https://github.com/Guw2/Banco-Lorian.git
cd Banco-Lorian
docker build -t lorianbank .
docker-compose up --build
```
### 💻 Execução Local (Manual)

#### 1. Banco de Dados MySQL
```sql
CREATE DATABASE lorian_bank;
CREATE USER 'lorian_user'@'localhost' IDENTIFIED BY 'lorian123';
GRANT ALL PRIVILEGES ON lorian_bank.* TO 'lorian_user'@'localhost';
FLUSH PRIVILEGES;
```
#### 2. Configuração (application.properties)
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/lorian_bank?useTimezone=true&serverTimezone=UTC
spring.datasource.username=lorian_user
spring.datasource.password=lorian123
spring.jpa.hibernate.ddl-auto=none
```
#### 3. Iniciar Aplicação
```bash
./mvnw spring-boot:run
```


## 🔐————— Sistema de Autenticação —————🔐

### 👥 Perfis de Acesso

| Perfil  | Permissões                                                                 |
|---------|----------------------------------------------------------------------------|
| `USER`  | Operações pessoais (suas contas, cartões e transações)                    |
| `ADMIN` | Acesso completo ao sistema (gerenciamento de todos os clientes e dados)   |

### 🔑 Endpoints Públicos

```http
POST /auth/register
Content-Type: application/json

{
  "username": "user",
  "password": "user123",
  "role": "USER"
  "cliente": {
	  "nome": "cliente",
	  "cpf": "000.000.000-00",
	  "idade": 30,
	  "endereco": "R. Lorem Ipsum",
	  "(00) 99999-9999",
	  "email": "cliente@gmail.com"
  }
}
```

```http
POST /auth/login
Content-Type: application/json

{
  "username": "user",
  "password": "user123"
}
```


## 🔑———— Tabelas de Endpoints Que Exigem Autenticação ———— 🔑

### 👤 Usuário Autenticado (USER)
| Método | Endpoint                              | Descrição                                  |
|--------|---------------------------------------|--------------------------------------------|
| GET    | `/myuser/cliente`                     | Buscar informações do cliente              |
| GET    | `/myuser/contas`                      | Listar contas do usuário                   |
| POST   | `/myuser/contas/abrir`                | Abrir nova conta                           |
| GET    | `/myuser/cartoes`                     | Listar cartões do usuário                  |
| GET    | `/myuser/cartoes/{numero_da_conta}`   | Buscar cartões por conta                   |
| POST   | `/myuser/cartoes/emitir`              | Emitir novo cartão                         |
| POST   | `/myuser/transacoes/transferir`       | Transferência entre contas                 |
| POST   | `/myuser/transacoes/transferir-cartao`| Transferência via cartão                   |
| POST   | `/myuser/transacoes/sacar`            | Realizar saque                             |
| POST   | `/myuser/transacoes/depositar`        | Realizar depósito                          |
| POST   | `/myuser/transacoes/pagar-fatura`     | Pagar fatura do cartão                     |

### 🛠️ Administração (ADMIN)

#### Clientes
| Método | Endpoint                  | Descrição                      |
|--------|---------------------------|--------------------------------|
| GET    | `/ops/clientes`           | Listar todos os clientes       |
| POST   | `/ops/clientes`           | Cadastrar novo cliente         |
| GET    | `/ops/clientes/{email}`   | Buscar cliente por e-mail      |
| DELETE | `/ops/clientes/{email}`   | Remover cliente                |

#### Contas
| Método | Endpoint                  | Descrição                      |
|--------|---------------------------|--------------------------------|
| GET    | `/ops/contas`             | Listar todas as contas         |
| POST   | `/ops/contas`             | Criar nova conta               |
| GET    | `/ops/contas/{numero}`    | Buscar conta por número        |
| DELETE | `/ops/contas/{numero}`    | Remover conta                  |

#### Cartões
| Método | Endpoint                  | Descrição                      |
|--------|---------------------------|--------------------------------|
| GET    | `/ops/cartoes`            | Listar todos os cartões        |
| POST   | `/ops/cartoes`            | Emitir novo cartão             |
| GET    | `/ops/cartoes/{numero}`   | Buscar cartão por número       |
| DELETE | `/ops/cartoes/{numero}`   | Cancelar cartão                |
| GET    | `/ops/cartoes/ativar/{id}`| Ativar cartão                  |

#### Transações
| Método | Endpoint                          | Descrição                          |
|--------|-----------------------------------|------------------------------------|
| GET    | `/ops/transacoes`                 | Listar todas as transações         |
| GET    | `/ops/transacoes/{id}`            | Buscar transação por ID            |
| POST   | `/ops/transacoes/transferir`      | Realizar transferência             |
| POST   | `/ops/transacoes/sacar`           | Realizar saque                     |
| POST   | `/ops/transacoes/depositar`       | Realizar depósito                  |
| POST   | `/ops/transacoes/credito`         | Transferência com cartão           |
| POST   | `/ops/transacoes/credito/fatura`  | Pagamento de fatura                |

> **📚 Documentação Completa**  
> Para visualizar todos os 30+ endpoints com mais detalhes de request/response, acesse:  
> `http://localhost:8080/swagger-ui.html` (disponível após iniciar a aplicação)

 
