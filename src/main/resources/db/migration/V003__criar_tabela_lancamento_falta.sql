-- criar tabela para lançamentos das faltas do aluno

CREATE SEQUENCE IF NOT EXISTS public.lancamento_falta_seq START 1;

CREATE TABLE public.lancamento_falta (
	id INT4 NOT NULL DEFAULT NEXTVAL('lancamento_falta_seq'),
	aluno_id INT4 NOT NULL,
	quantidade SMALLINT NOT NULL,
	ano_letivo CHAR(20) NOT NULL,
	CONSTRAINT fk_lancamento_falta_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
	CONSTRAINT pk_lancamento_falta PRIMARY KEY (id)
);