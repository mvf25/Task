--CREATE TABLE IF NOT EXISTS MY_TASKS(
	--id INT AUTO_INCREMENT PRIMARY KEY,
	--title VARCHAR(100) NOT NULL,
	--description VARCHAR(256),
	--hecho BOOLEAN NOT NULL
--);

INSERT INTO MY_TASKS (id, state,description) VALUES
	(1, 'Pendiente','La descripcion de la prueba 1'),
	(2, 'Realizado','La descripcion de la prueba 2'),
	(3, 'En proceso','Esta descripcion si esta a TRUE el campo de done');
	
