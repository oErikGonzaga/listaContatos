# listaContatos

### Aplicação de Lista de Contatos.

---

_Utilizando Java 17 com SpringBoot e Maven 3.8.6.
<br>SpringData, SpringWeb, Lombok e MySQL.
<br>Arquitetura de Software padrão MVC._

---

>Este projeto Java implementa exemplos de requisições 
_HTTP_ utilizando os métodos _GET e POST, PUT E DELETE_ 
via _REST API_.

><p> A aplicação consiste em um sistema de criação de lista de Contatos.
><br> Aonde os dados de Nome e Documentação do Usuário são:
>Inseridos, tratados e armazenado no Banco de Dados.

>Uma divisão de Classes é feita entre pacotes com suas respectivas funcionalidades.

---

* **Controllers:**
  * _ContatosController_
    
* **Models:**
  * _Contatos_

* **Repositories:**
  * _ContatosRepository_

* **Services:**
  * _ContatosService_
  * _ContatosServiceImpl_

---

### Classe Controller
* #### ContatosController

> A Classe Controller _ContatosController_, fica responsável pela nossa entrada de dados.
<br>Ela é a API _(Application Programming Interface)_, que gerencia nossas
requisições _(REQUESTS)_, através dos verbos _GET, POST, PUT, PATCH e DELETE_,
aos quais são utilizados para operações de _CRUD (CREATE, READ, UPDATE e DELTE)_
no Banco de Dados. Juntos suas respectivas regras e requerimentos de URL,
tais como: _PathParam, QueryParams, Headers e Body_.
> 
> Para isso foi utilizado a Interface @RestController para marcamos a Classe
como um EndPoint e mapeamos a entrada da classe com:

<br>

```@RequestMapping("contatos")```
* Ele adiciona um path fixo antecedente aos demais paths.

```@GetMapping(value = "check")```
* Utiliza o verbo GET para checar nossa conexão com o EndPoint.

```@PostMapping("cadastrar")```
* Responsável por receber um Body com nossos dados através de @RequestBody
e armazena-los em nossa Classe Modelo: Contato.

```@GetMapping("listar")```
* Lista todos dos contatos armazenados em nosso Banco de Dados com filtro,
checando contatos ativos e inativos.

```@GetMapping("{id}")```
* Consulta um contato por um id especifico que é gerado automaticamente
no cadastro do contato.

```@PatchMapping("inativar/{id}") e @PatchMapping("ativar/{id}")```
* Inativa e Ativa um contato através de um ID do fornecido do mesmo.

```@DeleteMapping("{id}")```
* Exclui um contato de nosso Banco de Dados através do ID fornecido.

```@PutMapping("{id}")```
* Atualiza todos ou parcialmente os dados de contato através de um contato.
---

### Classe Model
* #### Contato

>A Classe model _Contato_ é responsável por armazenar os dados que recebemos de
nosso Controller. Aqui mapeamos nossas variáveis conforme as especificações
passadas do nosso Banco de Dados.

<br>

```@Data```
* É utilizado para gerar de forma implícita nossos métodos modificadores de
acesso, os getter e setters.

```@Entity``` 
* Indica que nossa classe é uma entidade do banco de dados.

```@Table(name = "contato")```
* Indica que a classe é uma tabela do nosso banco de dados que representa

```@Id```
* Acima do atributo indica que ele representa um PrimaryKey (PK) na Tabela

```@Column(name = "nome", nullable = false)```
* Indica que nosso atributo corresponde ao coluna que recebe o nome indicado
acima em nossa tabela.
<br>Nullable indica que o campo não pode ser nulo (NOT NULL).
---

### Classes Services

> São divididas em duas Classes, uma Interface e uma Implementação.

<br>

* #### Interface ContatosService

> Aqui estão as nossas Regras de Negócios, sendo que as classes que a
implementarem terão que seguir estas regras.

<br>

- ```List<Contato> listar(Boolean ativo);```
<br>```Contato cadastrar(Contato contato);```
<br>```Contato buscarPorId(String id); ```
<br>```boolean inativar(String id); ```
<br>```boolean ativar(String id);```
<br>```boolean deletar(String id);```
<br>```boolean atualizar(String id, String nome, String documento);```

<br>


* #### Classe ContatosServiceImpl

> Contém todos os nossos métodos e lógicas utilizados para o
tratamento de dados. Aqui recebemos e recuperamos nossas informações
de nossos REQUESTS e do Banco de Dados



 
---

### Classe Repository