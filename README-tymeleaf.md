
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






