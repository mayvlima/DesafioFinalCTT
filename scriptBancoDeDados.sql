CREATE DATABASE desafiofinalctt;

USE desafiofinalctt;

CREATE TABLE usuarios(ID INT AUTO_INCREMENT PRIMARY KEY, Nome VARCHAR(60) NOT NULL, Email VARCHAR(130) NOT NULL, Senha VARCHAR(50) NOT NULL, Data_Cadastro DATETIME);

INSERT INTO usuarios(Nome, Email, Senha, Data_Cadastro) VALUES ("Mayara Lima", "mayara@email.com", "1010", "2021-03-13");

SELECT * FROM usuarios;


