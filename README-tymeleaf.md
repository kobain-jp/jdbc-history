
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

	EmployeeRepository repository;

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

SpringBootを起動し、HelloWoldとでれば準備完了

### tableを表示しよう

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

	EmployeeRepository repository;

	@Autowired
	public EmployeeIndexController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/employee")
	public String index(Model model) {

		List<Employee> employeeList = new ArrayList<Employee>();

		Employee employee = new Employee();
		employee.setId(1);
		employee.setEmpNo("1");
		employee.setEmpNm("kobayashi_yo");

		employeeList.add(employee);

		model.addAttribute("employeeList", employeeList);

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
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">emp no</th>
				<th scope="col">emp nm</th>
			</tr>
		</thead>
		<tbody>
			<tr th:each="employee : ${employeeList}">
				<td th:text="${employee.empNo}"></td>
				<td th:text="${employee.empNm}"></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
```

### お得意のDBからロードしよう




