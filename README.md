# DesafioFinalCTT

Desafio final proposto pela trilha Java - Assertiva no programa Campinas Tech Talents.

O desafio consiste em criar um CRUD de usuários com os seguintes campos:

id - auto incremento
Nome - not null
Email - not null
Data de Cadastro - recuperando a data do sistema

Criar um menu onde o usuário irá informar qual a atividade ele deseja realizar um um banco de dados no MySQL:
1. Cadastrar (Metódo INSERT)
2. Procurar (Metódo SELECT)
3. Alterar (Metódo UPDATE)
4. Excluir (Metódo DELETE)

Além disso criar testes Junit para testar a maior parte do código

Para testar o código na sua máquina você deve:
1. Ir na classe Conexa, do pacote usuario.conexao
2. Colocar o username do seu banco de dados na variável USERNAME (ex: "root")
3. Colocar a password do seu banco de dados na variável PASSWORD (ex: "1234")
4. Colocar o url do seu banco de dados na variável URL (ex: "jdbc:mysql://localhost")
5. Rode a classe Conexão para saber se a conexão ocorreu com sucesso

6. Por fim, você deve rodar a classe Programa do pacote usuario.aplicacao
