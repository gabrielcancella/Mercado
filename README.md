# 🛠️ Sistema de Gerenciamento de Estoque - Mercado

Este é um projeto em andamento de um **sistema de gerenciamento de estoque** . O sistema está sendo desenvolvido com base em boas práticas de arquitetura em camadas (Model-DAO-Controller-View).

---

## 🎯 Objetivo

Criar uma aplicação de controle de estoque local para mercados, permitindo:

- Cadastro de produtos, categorias
- Entradas e saídas de estoque
- Registro de vendas com múltiplos produtos
- Relatórios básicos (ex: total vendido, estoque atual)

---

## 📦 Tecnologias

- Java 23+
- Java Swing (GUI)
- JDBC (conexão com banco de dados)
- Git + GitHub

---

## 🗂️ Estrutura do Projeto

```plaintext
src/
├── models/        
    ├── entity/      # Classes do domínio (entidades)
    └── dao/         # Acesso ao banco (Data Access Objects)
├── controllers/   # Lógica de negócio
├── view/          # Telas em Java Swing
├── util/          # Utilitários (ex: conexão com o banco)
└── Main.java      # Ponto de entrada
```
---

## ✅ Funcionalidades previstas

- Estrutura de pastas
- Conexão com banco SQL Server
- Modelos: Produto, Venda, ItemVenda, etc.
- DAOs para acesso ao banco
- Controllers com regras de negócio
- Telas Swing (cadastro, vendas, estoque)
- Relatórios

## 📄 Licença

Licença a definir.

---


