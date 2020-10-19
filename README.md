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

CREATE TABLE IF NOT Exists book(book_id SERIAL, isbn Long, title NVARCHAR(50), author NVARCHAR(50), release_date DATE);

```

create src/main/resources/data.sql

```

INSERT INTO book(title, isbn,  author, release_date) VALUES ('SLAM DUNK 1',9784088716114,'井上雄彦','1991-02-08');
INSERT INTO book(title, isbn,  author, release_date) VALUES ('SLAM DUNK 2',9784088716121,'井上雄彦','1991-06-10');
INSERT INTO book(title, isbn,  author, release_date) VALUES ('リアル 1',9784088761435,'井上雄彦','2001-3-19');

```

RunAs > Spring Boot App

open http://localhost:8080/h2-console

click Connect
spring.datasource.initialization-mode=alwaysを指定すると、schem.sql,data.sqlが起動時に毎回実行される

open http://localhost:8080/swagger-ui/


### 0 はじめに

TestCaseを動かそう
test/java/com/dojo/jdbchistoryrest/domain/repository/BookRepositoryTest.java

JavaでSQLを実行するコードの歴史を辿っていこう

DriverManager時代 -> DataSource -> JdbcTemplate -> Jpa
さぁDriverManager時代からはじめよう

### 1 DriverManager時代のfindAll(select * from Book)

com/dojo/jdbchistoryrest/domain/repository/DriverManagerRepository.java

```

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;


        @Override
	public List<Book> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Book> bookList = new ArrayList<Book>();

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, userName, password);
			ps = con.prepareStatement("select * from book");
			rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setIsbn(rs.getLong("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setReleaseDate(rs.getDate("release_date"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se2) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return bookList;
	}

```


### 2 DataSource時代のfindAll(select * from Book)

com/dojo/jdbchistoryrest/domain/book/repository/DataSourceRepository.java

```
private DataSource dataSource;

	@Autowired
	public DataSourceRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Book> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Book> bookList = new ArrayList<Book>();

		try {
			con = DataSourceUtils.getConnection(dataSource);
			ps = con.prepareStatement("select * from book");
			rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setBookId(rs.getLong("book_id"));
				book.setTitle(rs.getString("title"));
				book.setIsbn(rs.getLong("isbn"));
				book.setAuthor(rs.getString("author"));
				book.setReleaseDate(rs.getDate("release_date"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}
		return bookList;
	}
	}


```

### 3 JdbcTemplate時代のfindAll(select * from Book)

com/dojo/jdbchistoryrest/domain/book/repository/JdbcTplRepository.java

```

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTplRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Book> findAll() {

		return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<Book>(Book.class));

	}


```

### 4 練習 IBookReporistoryの実装クラスを参考にIUseRepositoryを実装しよう

1. DriveManagerBookReposirotyを参考にDriveManagerUserReposirotyを実装する

2. DataSourceUserRepositoryを実装する

3. JdbcTplUserRepositoryを実装する

テストの仕方

以下を修正し、UserRepositoryTest.javaを実行

driverManagerUserRepositoryをテストする場合

```
        //@Qualifier("jdbcTplUserRepository")
	@Qualifier("driverManagerUserRepository")
	//@Qualifier("dataSourceUserRepository")

```









