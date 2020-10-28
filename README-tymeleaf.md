
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

