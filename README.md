# jdbc-history-rest

### download project via Spring Initializr or via git clone
https://start.spring.io/

Dependencies
- Spring Boot DevTools DE
- Spring Web
- JDBC API 
- H2 Database

`git clone https://github.com/kobain-jp/jdbc-history-rest.git`

### import files to your eclipse
1. open your eclipse
2. File > import > Existing Gradle Project 
3. Next > Browse and select downloaded folder and Next > check override workspace settings and Finish
4. select build.gradle and right click and Gradle > refresh Gradle project

if the error occured, restart your eclipse.

### init H2 Database

src/main/resources/application.properties

```

# DB Config
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:demodb
spring.datasource.username=sa　
spring.datasource.password=dm
spring.h2.console.enabled=true
# init sql is kicked if always
spring.datasource.initialization-mode=always

```

create src/main/resources/schema.sql

```

CREATE TABLE IF NOT Exists book(id SERIAL,cover blob, title NVARCHAR(50), author NVARCHAR(50), note NVARCHAR(255));

```

create src/main/resources/data.sql

```

INSERT INTO book(title, author, release_date, note) VALUES ('Slum Dunk 1巻','井上雄彦','1991-02-08','バスケ漫画');
INSERT INTO book(title, author, release_date, note) VALUES ('ドラゴンボール　1巻','鳥山明','1985-09-10','悟空の成長物語');
INSERT INTO book(title, author, release_date, note) VALUES ('BECK　1巻','ハロルド作石',null,'バンド漫画');

```

RunAs > Spring Boot App

open http://localhost:8080/h2-console

click Connect



spring.datasource.initialization-mode=alwaysを指定すると、schem.sql,data.sqlが起動時に毎回実行される
