# Swing_ListaDeTarefas_Status

Este projeto é uma aplicação simples de Lista de Tarefas (To-Do List) desenvolvida em **Java** utilizando a biblioteca **Swing** para a interface gráfica e **JDBC** para a conexão com um banco de dados MySQL. Ele permite **criar**, **visualizar**, **atualizar** e **excluir tarefas**, além de **alterar o status** (Pendente ou Concluído) de cada tarefa.

---

## Funcionalidades

- Cadastro de novas tarefas
- Exibição de todas as tarefas em uma tabela (JTable)
- Atualização de descrição e status da tarefa
- Exclusão de tarefas

---

## Tecnologias utilizadas

- **Java** (JDK 17 ou superior recomendado)
- **Swing** (Interface gráfica)
- **JDBC** (Conexão com banco de dados)
- **MySQL** (Sistema de gerenciamento de banco de dados)

---

## Estrutura do banco de dados

A tabela utilizada no MySQL segue o seguinte modelo:

```sql
CREATE TABLE tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(45),
    descricao VARCHAR(255) NOT NULL,
    status_atividade VARCHAR(20) NOT NULL,
    data_atividade DATE
);
````

---

## Como executar o projeto

1. **Clone o repositório:**

```bash
git clone https://github.com/c0mcod/Swing_ListaDeTarefas_Status.git
```

2. **Configure o banco de dados:**

* Crie um banco de dados no MySQL
* Execute o script acima para criar a tabela `tarefas`

3. **Atualize as credenciais do banco no código Java:**

No arquivo responsável pela conexão (ex: `ConnectionFactory.java`), atualize com seu usuário, senha e nome do banco:

```java
String url = "jdbc:mysql://localhost:3306/seu_banco";
String user = "seu_usuario";
String password = "sua_senha";
```

4. **Compile e execute a aplicação:**

Você pode abrir o projeto no Eclipse ou VS Code com as configurações corretas do Java e executar a aplicação diretamente pela interface.

---

## Estrutura do Projeto

```bash
Swing_ListaDeTarefas_Status/
│
├── conexao/                 # Conexão com o banco de dados
│   └── ConnectionFactory.java
│
├── controller/                 # Metodos usados na interface
│   └── ListController.java
│
├── dao/                     # Camada de acesso a dados (DAO)
│   └── TarefaDAO.java
│
├── table/                 # atualização automatica da tabela
│   └── TableMethod.java
│
├── model/                   # Classe de modelo Tarefa
│   └── Tarefa.java
│
├── view/                    # Interface gráfica Swing
│   └── TelaTarefas.java
│
```

---


## Licença

Este projeto é de uso livre para fins de aprendizado.

---

## Autor

Desenvolvido por [@c0mcod](https://github.com/c0mcod)
