
*pullとgradle refreshをまず実施してください

*8081ポートなどを使いたい人はapplication.propertiesに以下を追記してください

`server.port=8081`

### DBにテーブルとデータを作ろう

/src/main/resources/schema.sql

```
CREATE TABLE IF NOT Exists employee(emp_id SERIAL, emp_no NVARCHAR(50), emp_nm NVARCHAR(50));
```


/src/main/resources/data.sql

```
INSERT INTO employee(emp_no, emp_nm) VALUES ('1','Kobayashi Taro');
INSERT INTO employee(emp_no, emp_nm) VALUES ('2','Yamamoto Jiro');
```

起動して、以下のURLにアクセス

http://localhost:8080/h2-console

```
Driver Class:org.h2.Driver
JDBC URL:jdbc:h2:mem:demodb
User Name:sa
Password:
```

click connect

### hellowoldを表示しよう

com/dojo/jdbchistoryrest/controller/web/employee/EmployeeIndexController.javaを作成

```
package com.dojo.jdbchistoryrest.controller.web.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dojo.jdbchistoryrest.domain.employee.EmployeeRepository;

@Controller
public class EmployeeIndexController {

	@GetMapping("/employee")
	public String index(Model model) {


		return "/employee/index";
	}

}
```

となれば、
src/main/resources/templates/employee/index.htmlを作成し、

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World</h1>
</body>
</html>
```

com/dojo/jdbchistoryrest/JdbcHistoryRestApplication.javaを選択し、
Run As JavaApplication

http://localhost:8080/employee

Hello WorldとでればOK

### tableを表示しよう

com.dojo.jdbchistoryrest.domain.employee.Employee.javaを作成

```
package com.dojo.jdbchistoryrest.domain.employee;

public class Employee {

	private long id;
	private String empNo;
	private String empNm;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

}


```

com/dojo/jdbchistoryrest/controller/web/employee/EmployeeIndexController.javaを編集

```
package com.dojo.jdbchistoryrest.controller.web.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dojo.jdbchistoryrest.domain.employee.Employee;
import com.dojo.jdbchistoryrest.domain.employee.EmployeeRepository;

@Controller
public class EmployeeIndexController {

	@GetMapping("/employee")
	public String index(Model model) {

+		List<Employee> employeeList = new ArrayList<Employee>();

+		Employee employee = new Employee();
+		employee.setId(1);
+		employee.setEmpNo("1");
+		employee.setEmpNm("kobayashi_yo");

+		employeeList.add(employee);

+		model.addAttribute("employeeList", employeeList);

		return "/employee/index";
	}

}
```

src/main/resources/templates/employee/index.htmlを編集

```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World</h1>
+	<table class="table">
+		<thead>
+			<tr>
+				<th>emp no</th>
+				<th>emp nm</th>
+			</tr>
+		</thead>
+		<tbody>
+			<tr th:each="employee : ${employeeList}">
+				<td th:text="${employee.empNo}"></td>
+				<td th:text="${employee.empNm}"></td>
+			</tr>
+		</tbody>
+	</table>
</body>
</html>
```

http://localhost:8080/employee

１行でればOK

### jdbcでDBからデータをロードして表示しよう

com/dojo/jdbchistoryrest/domain/employee/EmployeeRepository.javaを作成

```
package com.dojo.jdbchistoryrest.domain.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public EmployeeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Employee> findAll() {
		return jdbcTemplate.query("select * from Employee", new BeanPropertyRowMapper<Employee>(Employee.class));
	}

}
```

com/dojo/jdbchistoryrest/controller/web/employee/EmployeeIndexController.javaを編集


```
package com.dojo.jdbchistoryrest.controller.web.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dojo.jdbchistoryrest.domain.employee.EmployeeRepository;

@Controller
public class EmployeeIndexController {

+	EmployeeRepository repository;

+	@Autowired
+	public EmployeeIndexController(EmployeeRepository repository) {
+		this.repository = repository;
+	}

+	@GetMapping("/employee")
+	public String index(Model model) {

+		model.addAttribute("employeeList", repository.findAll());
+		return "/employee/index";
+	}

}
```

Autowiredは基本コンストラクタインジェクションにしょう

http://localhost:8080/employee

２行でればOK

### BootStrapをいれて画面をっぽくしよう

bootstrapは何？

ざっくりいうと、htmlにbootstrapのcssとjsするだけで、画面をきれいにしてくれるフレームワークです。
https://getbootstrap.jp/docs/4.5/about/overview/

どう入れる？

build.gradleを修正し、webjarを入れる

webjarsはJavaScript や CSS などのフロントサイドのライブラリの依存関係を、 Maven （Gradle）で管理できるようにするもの
※build.gradleに書けば他のjarファイルのように必要なフロントサイドのライブラリのファイルをバージョンを指定してダウンロードしてくれる便利なものです。

以下は既に入れています
```
	implementation 'org.webjars:webjars-locator:0.40'	
	implementation 'org.webjars:bootstrap:4.5.0'
```

build.gradle > Gradle >reflesh Gradle Project

src/main/resources/templates/employee/index.htmlを編集し、webjarを通してダウンロードした静的ファイルをインクルードする
```
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Employee List</title>
+<link th:href="@{/webjars/bootstrap/css/bootstrap.min.css}"
	rel="stylesheet" />
</head>
+<body class="container-fluid">
	<h1>Employee List</h1>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
+				<th scope="col">emp no</th>
+				<th scope="col">emp nm</th>
			</tr>
		</thead>
		<tbody>
			<tr th:each="employee : ${employeeList}">
				<td th:text="${employee.empNo}"></td>
				<td th:text="${employee.empNm}"></td>
			</tr>
		</tbody>
	</table>
+	<script th:src="@{/webjars/jquery/jquery.min.js}"></script>
+	<script th:src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>
</body>
</html>
```	

bootstrapをいれるためにした事

script/cssのインクルート

```
<link th:href="@{/webjars/bootstrap/css/bootstrap.min.css}"
	rel="stylesheet" />
```

```
	<script th:src="@{/webjars/jquery/jquery.min.js}"></script>
	<script th:src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>
```

classの指定

```
<body class="container-fluid">
```

```
<table class="table table-striped table-hover">
```

tableはいろいろと変えられるので、遊んでみて
https://getbootstrap.jp/docs/4.2/content/tables/


1回目はここまで
