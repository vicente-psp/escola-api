-- criar tabela de alunos

CREATE SEQUENCE IF NOT EXISTS public.aluno_seq START 1;

CREATE TABLE public.aluno (
	id INT4 NOT NULL DEFAULT NEXTVAL('aluno_seq'),
	nome VARCHAR(60) NOT NULL,
	matricula VARCHAR(20),
	CONSTRAINT pk_parametro PRIMARY KEY (id)
);


-- insere alunos que serão utilizados no teste
INSERT INTO public.aluno (nome) VALUES ('Pedro'), ('Maria'), ('José');