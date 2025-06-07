<h1 align="center"> POO2_EstoqueUEPA </h1>

<p align="center">
  <image
  src="https://img.shields.io/github/languages/count/joaomachado27/POO2_EstoqueUEPA"
  />
  <image
  src="https://img.shields.io/github/languages/top/joaomachado27/POO2_EstoqueUEPA"
  />
  <image
  src="https://img.shields.io/github/last-commit/joaomachado27/POO2_EstoqueUEPA"
  />

</p>

## Sumário

- [Objetivos ](#objetivos-)
- [Descrição detalhada ](#descrição-detalhada-)
- [Tecnologias utilizadas ](#tecnologias-utilizadas-)
- [Ambiente de codificação ](#ambiente-de-codificação-)
- [Clonagem e instalação ](#clonagem-e-instalação-)
- [Autoria ](#autoria-)

# Objetivos <a name="id01"></a>
  
O projeto destina-se a servir como um estudo de caso prático para alunos da disciplina de Programação Orientada a Objetos 2, proporcionando uma oportunidade de aplicar conceitos de análise, design e implementação de software em um contexto realista.

# Descrição detalhada <a name="id02"></a>

  Sistema robusto e funcional que permite o controle eficiente das operações de estoque de um departamento interno da UEPA que necessita gerenciar materiais. Este sistema deve oferecer uma plataforma digital para registrar produtos, monitorar níveis de estoque em tempo real, gerenciar movimentações e fornecer informações valiosas para a tomada de decisão.
  
  A aplicação dos princípios da programação orientada a objetos será fundamental para modelar as entidades do domínio e suas interações, resultando em um software bem estruturado, manutenível e escalável.

  O sistema foi desenvolvido observando essas seguintes funcionalidades:

1. **Autenticação** por meio de **e-mail institucional**, limitado a endereços pertencentes ao domínio ou subdomínios da UEPA (neste caso apenas a emails com *@uepa.br), juntamente com uma **senha válida**,  validando as credenciais informadas pelo usuário com os dados previamente cadastrados no banco de dados.
2. **Cadastro** de novos **produtos**, **consulta** de produtos cadastrados, com **filtros** por código e/ou nome, **edição** das informações de produtos existentes, **remoção** lógica de produtos do catálogo.
3. **Registro** de **entradas e saídas** de estoque para produtos previamente cadastrados, atualizando automaticamente a quantidade em estoque e exibindo essa informação de forma acessível ao usuário, **consulta** do histórico completo de movimentações de estoque, com **filtros** por produto ou período.
4. **Gerar** um **relatório** do inventário atual, listando todos os produtos, suas quantidades e procedências.
5. **Usuário administrador** que gerencie outros usuários podendo **criar**, **editar** e **inativar** cada usuário cadastrado.
6. **Restringir** o acesso às funcionalidades apenas a **usuários autenticados**, garantindo a segurança da aplicação.
7. **Utilizar técnicas de hash** apropriadas (nesse caso foi utilizado o SHA-256) combinados com **salting** para evitar ataques de força bruta e dicionário, garantindo a segurança das informações.
8. **Utilizar** a **arquitetura MVC** (Model-View-Controller) para garantir a separação clara entre a lógica de negócio, a interface do usuário e o controle das interações, facilitando a manutenção, escalabilidade e organização do código.

# Tecnologias utilizadas <a name="id03"></a>

<div  align='center'> 

 ![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MYSQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

</div>

# Ambiente de codificação <a name="id04"></a>

<div  align='center'> 

![NETBEANS](https://img.shields.io/badge/apache%20netbeans-1B6AC6?style=for-the-badge&logo=apache%20netbeans%20IDE&logoColor=white)
![git](https://img.shields.io/badge/GIT-0D1117?style=for-the-badge&logo=git&logoColor=red)
![github](https://img.shields.io/badge/Github-0D1117?style=for-the-badge&logo=github&logoColor=fff)
</div>

# Clonagem e instalação <a name="id05"></a>

Clone este repositório usando o comando:

```bash
git clone https://github.com/joaomachado27/POO2_EstoqueUEPA.git
```
[comment]: <> (Adicione o link da implatação, se houver)

# Autoria <a name="id07"></a>

[comment]: <> (Adicione seu nome e função)

<h3 align='center'> João Machado • Gabriel Castro • Luyze Caetano | discentes de BES - UEPA Ananindeua
 </h3>

<div  align='center'>

[![Discord](https://img.shields.io/badge/Discord-0D1117?style=for-the-badge&logo=discord&logoColor=blue)](https://discord.com/invite/B63yvMcy)
<a href = "mailto:jvjumachado@gmail.com">
![Gmail](https://img.shields.io/badge/Gmail-0D1117?style=for-the-badge&logo=gmail&logoColor=red)</a>
[![Github](https://img.shields.io/badge/Github-0D1117?style=for-the-badge&logo=github&logoColor=fff)](https://www.github.com/joaomachado27)
</div>
