-- criar tabela para frequências do aluno

CREATE SEQUENCE IF NOT EXISTS public.frequencia_seq START 1;

CREATE TABLE public.frequencia (
	id INT4 NOT NULL DEFAULT NEXTVAL('frequencia_seq'),
	aluno_id INT4 NOT NULL,
	ano_letivo CHAR(20) NOT NULL,
	presenca bool NOT NULL,
	CONSTRAINT fk_frequencia_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
	CONSTRAINT pk_frequencia PRIMARY KEY (id)
);