--CREATE TABLE IF NOT EXISTS MY_TASKS(
	--id INT AUTO_INCREMENT PRIMARY KEY,
	--title VARCHAR(100) NOT NULL,
	--description VARCHAR(256),
	--hecho BOOLEAN NOT NULL
--);

INSERT INTO MY_TASKS (id, title,description,hecho) VALUES
	(1, 'Prueba1','La descripcion de la prueba 1',FALSE),
	(2, 'Prueba2','La descripcion de la prueba 2',FALSE),
	(3, 'Prueba3','Esta descripcion si esta a TRUE el campo de done',TRUE);
	
