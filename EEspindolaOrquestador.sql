CREATE TABLE Usuario(
    IdUsuario NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Folio VARCHAR(1000),
    Nombre VARCHAR(50) NOT NULL,
    ApellidoPaterno VARCHAR(50) NOT NULL,
    ApellidoMaterno VARCHAR(50) NOT NULL,
    FechaNacimiento DATE NOT NULL,
    Username VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    Status CHAR(1)
);

ALTER TABLE Usuario
MODIFY Status DEFAULT 1 NOT NULL;

ALTER TABLE Usuario
MODIFY Password VARCHAR(100);

INSERT INTO Usuario(Folio, Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Username, Email, Password) 
VALUES('07868985','Samantha','Parra','Fuentes',TO_DATE('2000-12-12', 'yyyy-MM-dd'),'Sam','samantha@hotmail.com','1234');

INSERT INTO Usuario(Folio, Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Username, Email, Password) 
VALUES('87645234','Valentina','Lopez','Ramirez',TO_DATE('2001-11-11', 'yyyy-MM-dd'),'Vale','valentina@hotmail.com','qwerty');

INSERT INTO Usuario(Folio, Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Username, Email, Password) 
VALUES('LJSGDSDG0874Q0425','Dante','Lopez','Vazquez',TO_DATE('2002-10-10', 'yyyy-MM-dd'),'Danonino','dante@hotmail.com','0000');

INSERT INTO Usuario(Folio, Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Username, Email, Password) 
VALUES('LKJSFGLK12359869','Cacho','Alcantara','Fuentes',TO_DATE('2003-09-09', 'yyyy-MM-dd'),'Enano','cachito@hotmail.com','1234');

INSERT INTO Usuario(Folio, Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Username, Email, Password) 
VALUES('LKJG234LYG467G23K','Mechas','Espindola','Alcantara',TO_DATE('1999-01-01', 'yyyy-MM-dd'),'Estopas','mechas@hotmail.com','1111');


// ALTER TABLE Usuario
// ADD Rol VARCHAR(20) DEFAULT 'USER' NOT NULL;


SELECT * FROM ViewUsuario;

CREATE VIEW ViewUsuario AS
    SELECT IdUsuario, Folio, Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Username, Email, Password, Status
    FROM Usuario;


SELECT IDUSUARIO, FOLIO, NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, FECHANACIMIENTO, USERNAME, EMAIL, PASSWORD, STATUS 
FROM USUARIO
WHERE FOLIO = '';

CREATE OR REPLACE PROCEDURE SP_UsuarioInsert(
    pFolio IN VARCHAR2,
    pNombre IN VARCHAR2,
    pApellidoPaterno IN VARCHAR2,
    pApellidoMaterno IN VARCHAR2,
    pFechaNacimiento IN VARCHAR2,
    pUsername IN VARCHAR2,
    pEmail IN VARCHAR2,
    pPassword IN VARCHAR2
)
AS
BEGIN
    INSERT INTO USUARIO (Folio, Nombre, ApellidoPaterno, ApellidoMaterno, FechaNacimiento, Username, Email, Password)
    VALUES (pFolio, pNombre, pApellidoPaterno, pApellidoMaterno, TO_DATE(pFechaNacimiento, 'yyyy-MM-dd'), pUsername, pEmail, pPassword);
END SP_UsuarioInsert;


CALL SP_UsuarioInsert('384UTV9W8YT0WVT4HES','Test-SP_INSERT','SP','INSERT','1999-01-20','POST','post@hotmail.com','qwerty');


CREATE OR REPLACE PROCEDURE SP_UsuarioUpdate(

	pFolio IN VARCHAR2,
	pNombre IN VARCHAR2,
	pApellidoPaterno IN VARCHAR2, 
	pApellidoMaterno IN VARCHAR2,
	pFechaNacimiento IN VARCHAR2,
	pUsername IN VARCHAR2,
	pEmail IN VARCHAR2,
	pPassword IN VARCHAR2,
	pStatus IN VARCHAR2
	
)AS
BEGIN
	
	UPDATE USUARIO 
	SET
		NOMBRE  = pNombre,
		APELLIDOPATERNO  = pApellidoPaterno,
		APELLIDOMATERNO  = pApellidoMaterno,
		FECHANACIMIENTO  = TO_DATE(pFechaNacimiento, 'yyyy-MM-dd'),
		USERNAME  = pUsername,
		EMAIL = pEmail,
		PASSWORD  = pPassword,
		STATUS  = pStatus
	
	WHERE Usuario.FOLIO  = pFolio;
	
END SP_UsuarioUpdate;

CALL SP_UsuarioUpdate('ee953ba7-9916-4c2e-a003-05162b054e8f-2024-12-31T17:42:25', 'SP_UPDATE', 'SP', 'UDPATE', '1950-07-28','PUT', 'put@hotmail.com', '1234', '1');

CREATE OR REPLACE PROCEDURE SP_UsuarioDelete(

 pFolio IN VARCHAR2

)
AS
BEGIN
	
	DELETE FROM USUARIO WHERE Usuario.FOLIO = pFolio;
	
END SP_UsuarioDelete;

CALL SP_UsuarioDelete('bc3b9e8f-9ce7-4ab0-9b74-0676523509f5-2024-12-31T17:12:17');





