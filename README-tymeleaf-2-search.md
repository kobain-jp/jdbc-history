### 今日やること

社員番号検索機能を実装しよう

フロント
- 検索入力欄と検索ボタンの追加(form)
- 検索結果の表示（ヒットすれば表示、ヒットしなければメッセージを表示

バックエンド
- Controllerに検索リクエスト用のメソッドの実装
- Repositoryにselect * from employee where emp_nm = ? を投げるロジック追加

### セットアップ
*pullとgradle refreshをまず実施してください

*8081ポートなどを使いたい人はapplication.propertiesに以下を追記してください

`server.port=8081`

起動して、前回のページを確認しよう

com/dojo/jdbchistoryrest/JdbcHistoryRestApplication.javaを選択し、
Run As JavaApplication

http://localhost:8080/employee

Employee Listと２行でればセットアップ完了

### 検索入力欄と検索ボタンを追加しよう

src/main/resources/templates/employee/index.htmlを編集

Postリクエストを投げるので、formタグを追加し、その中にinput,buttonタグを追加しよう

```
<body class="container-fluid">
	<h1>Employee List</h1>
+	<form role="form" th:action="@{/employee/search-by-empnm}"
+		method="post">
+		<input type="text" name="searchValue"
+			placeholder="search by name" />
+		<button type="submit">Search</button>
+	</form>
	<table class="table table-striped table-hover">
		<thead>
```

保存して、リロードすると、入力項目とSearchボタンが表示される

http://localhost:8080/employee

Searchボタンをクリックしてみると、以下のURLに遷移し404 Not Foundになるので、次のセクションにすすもう
http://localhost:8080/employee/search-by-empnm


以下解説

```
th:action="@{/employee/search-by-empnm}　method="post"

```

actionのURLにPostリクエストを送るということ


https://developer.mozilla.org/ja/docs/Web/HTML/Element/form


### /employee/search-by-empnmのURLに対するPostリクエスtに対するメソッドをControllerに追加しよう

com/dojo/jdbchistoryrest/controller/web/employee/EmployeeIndexController.javaにメソッドを追加


```
+	@PostMapping("/employee/search-by-empnm")
+	public String findByTitle(Model model) {
+		return "/employee/index";
+	}
```

以前は@GetMappingにしましたが、今回はPostリクエストをうけとるので、今回は@PostMapping

もう一回、http://localhost:8080/employee/からSearchボタンを押下すると空のリストが表示される

ちなみに、ブラウザのURLで直接http://localhost:8080/employee/search-by-empnmを叩くとGETリクエストになるので、
404になります。ブラウザのURLのリクエストは全てGETリクエスト

###　入力値（リクエストパラメータ）を取得して、コンソールにだしてみよう


```

	<form role="form" th:action="@{/employee/search-by-empnm}"
		method="post">
		<input type="text" name="searchValue" 
			placeholder="search by name" />
		<button type="submit">Search</button>
	</form>
	
```

以下のリクエストパラメータの値をとりたい

`<input type="text" name="searchValue" placeholder="search by name" />`


com/dojo/jdbchistoryrest/controller/web/employee/EmployeeIndexController.javaに追加したメソッドを修正

```
	@PostMapping("/employee/search-by-empnm")
U	public String findByTitle(@RequestParam("searchValue") String searchValue,Model model) {
+		System.out.println(searchValue);
		return "/employee/index";
	}

```

inputタグのnameの`@RequestParam("searchValue") String searchValue`を引数に追加

http://localhost:8080/employee
入力し、検索ボタンを押下

コンソールに入力した値が表示されればOK

### DBからデータをロードして表示しよう

com/dojo/jdbchistoryrest/domain/employee/EmployeeRepository.javaにメソッドを追加

```

	public List<Employee> findByEmpName(String empName) {
		return jdbcTemplate.query("select * from Employee where emp_nm like '%'||?||'%'", new Object[] { empName }, new BeanPropertyRowMapper<Employee>(Employee.class));
	}

```

com/dojo/jdbchistoryrest/controller/web/employee/EmployeeIndexController.javaのメソッドを編集

```

	@PostMapping("/employee/search-by-empnm")
	public String findByTitle(@RequestParam("searchValue") String searchValue,Model model) {
-		System.out.println(searchValue);
+		model.addAttribute("employeeList", repository.findByEmpName(searchValue));
		return "/employee/index";
	}

```

http://localhost:8080/employee
入力し、検索ボタンを押下

2回目はここまで、お疲れ様でした。
