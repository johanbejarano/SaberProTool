CREATE TABLE Contexto (
  cont_id BIGSERIAL NOT NULL,
  modu_id int8 NOT NULL,
  nombre varchar(255) NOT NULL,
  descripcion varchar(1200000) NOT NULL, 
  fecha_creacion     timestamp NOT NULL, 
  fecha_modificacion timestamp, 
  usu_creador        int8 NOT NULL, 
  usu_modificador    int8, 
  estado_registro    char(1) NOT NULL, 
  PRIMARY KEY (cont_id));
 
ALTER TABLE contexto ADD CONSTRAINT FKcontexto111 FOREIGN KEY (modu_id) REFERENCES Modulo(modu_id);
ALTER TABLE pregunta ADD COLUMN cont_id int8;
ALTER TABLE pregunta ADD CONSTRAINT FKpregunta111 FOREIGN KEY (cont_id) REFERENCES Contexto(cont_id);

ALTER TABLE modulo ADD COLUMN igual_valor varchar(1);
ALTER TABLE pregunta ADD COLUMN complejidad numeric(5);
ALTER TABLE pregunta ADD COLUMN valor_pregunta numeric(3);
ALTER TABLE usuario ADD COLUMN token varchar(255);