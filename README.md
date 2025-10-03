Socket CRUD Worker

Projeto em Java para gerenciar empregados (`Employee`) utilizando **ServerSocket** e **Socket**.  
Permite operações de **CRUD** (Create, Read, Delete, Find All) via requisições JSON enviadas pelo cliente.

---

## Funcionalidades

- **Adicionar funcionário** (`create`)
- **Buscar funcionário por nome e email** (`findOne`)
- **Listar todos os funcionários** (`findAll`)
- **Deletar funcionário por email** (`delete`)

---

## Estrutura do Projeto
src/  
└─ main/java/br/study/  
├─ Employee.java  
├─ Factory.java  
├─ Main.java  
├─ RequestDTO.java  
├─ ResponseDTO.java  
└─ Server.java  
pom.xml  
.gitignore  
README.md  

---

## JSON de exemplo

### Adicionar funcionário

```json
{
  "action": "create",
  "data": {
    "name": "Diego Maglia",
    "email": "diego.maglia@email.com",
    "salary": 3500.0
  }
}
```

### Buscar funcionário

```json
{
  "action": "findOne",
  "data": {
    "name": "Diego Maglia",
    "email": "diego.maglia@email.com"
  }
}
```
### Deletar funcionário

```json
{
  "action": "delete",
  "data": {
    "name": "Diego Maglia",
    "email": "diego.maglia@email.com"
  }
}
```

### Listar todos os funcionários

```json
{
  "action": "findAll"
}
```