## SQLite

### 简介

SQLite是一个进程内的轻量级嵌入式数据库，它的数据库就是一个文件，实现了自给自足、无服务器、零配置的、事务性的SQL数据库引擎。它是一个零配置的数据库，这就体现出来SQLite与其他数据库的最大的区别：SQLite不需要在系统中配置，直接可以使用。且SQLite不是一个独立的进程，可以按应用程序需求进行静态或动态连接。SQLite可直接访问其存储文件

### SQLite 命令

SQLite 编程人员所使用的简单却有用的命令。这些命令被称为 SQLite 的点命令，这些命令的不同之处在于它们不以分号 ; 结束。

	.backup ?DB? FILE	备份 DB 数据库（默认是 "main"）到 FILE 文件。
	.bail ON|OFF	发生错误后停止。默认为 OFF。
	.databases	列出数据库的名称及其所依附的文件。
	.dump ?TABLE?	以 SQL 文本格式转储数据库。如果指定了 TABLE 表，则只转储匹配 LIKE 模式的 TABLE 表。
	.echo ON|OFF	开启或关闭 echo 命令。
	.exit	退出 SQLite 提示符。
	.explain ON|OFF	开启或关闭适合于 EXPLAIN 的输出模式。如果没有带参数，则为 EXPLAIN on，即开启 EXPLAIN。
	.header(s) ON|OFF	开启或关闭头部显示。
	.help	显示消息。
	.import FILE TABLE	导入来自 FILE 文件的数据到 TABLE 表中。
	.indices ?TABLE?	显示所有索引的名称。如果指定了 TABLE 表，则只显示匹配 LIKE 模式的 TABLE 表的索引。
	.load FILE ?ENTRY?	加载一个扩展库。
	.log FILE|off	开启或关闭日志。FILE 文件可以是 stderr（标准错误）/stdout（标准输出）。
	.mode MODE	设置输出模式，MODE 可以是下列之一：
		csv 逗号分隔的值

		column 左对齐的列

		html HTML 的 <table> 代码

		insert TABLE 表的 SQL 插入（insert）语句

		line 每行一个值

		list 由 .separator 字符串分隔的值

		tabs 由 Tab 分隔的值

		tcl TCL 列表元素

	.nullvalue STRING	在 NULL 值的地方输出 STRING 字符串。
	.output FILENAME	发送输出到 FILENAME 文件。
	.output stdout	发送输出到屏幕。
	.print STRING...	逐字地输出 STRING 字符串。
	.prompt MAIN CONTINUE	替换标准提示符。
	.quit	退出 SQLite 提示符。
	.read FILENAME	执行 FILENAME 文件中的 SQL。
	.schema ?TABLE?	显示 CREATE 语句。如果指定了 TABLE 表，则只显示匹配 LIKE 模式的 TABLE 表。
	.separator STRING	改变输出模式和 .import 所使用的分隔符。
	.show	显示各种设置的当前值。
	.stats ON|OFF	开启或关闭统计。
	.tables ?PATTERN?	列出匹配 LIKE 模式的表的名称。
	.timeout MS	尝试打开锁定的表 MS 毫秒。
	.width NUM NUM	为 "scolumn" 模式设置列宽度。
	.timer ON|OFF	开启或关闭 CPU 定时器。


### SQLite 语法

关系数据库进行交互的标准SQLite命令类似于SQL。命令包括CREATE, SELECT, INSERT, UPDATE, DELETE和DROP。

 - 1.创建库

 sqlite3 test.db

 - 2.创建表

 CREATE TABLE user(
   id INT PRIMARY KEY     NOT NULL,
   name           TEXT    NOT NULL,
   age            INT     NOT NULL
);

 CREATE TABLE user_score(
   id INT PRIMARY KEY     NOT NULL,
   user_Id          TEXT    NOT NULL,
   score            INT     NOT NULL
);
 - 3.删除表

DROP TABLE COMPANY;


 - 3.插入数据

INSERT INTO user (id,name, age) VALUES (1,'张三', 16);

INSERT INTO user (id,name, age) VALUES (2,'王二', 17);

INSERT INTO user (id,name, age) VALUES (3,'李四',18);

INSERT INTO user (id,name, age) VALUES (4,'李五',19);

INSERT INTO user_score (id,user_Id, score) VALUES (1,1, 7);

INSERT INTO user_score (id,user_Id, score) VALUES (2,2, 6);

INSERT INTO user_score (id,user_Id, score) VALUES (3,3,5);

INSERT INTO user_score (id,user_Id, score) VALUES (4,3,9);

 - 4.查询数据

select * from user;
select count(id) from user where age>=18;

 - 5.连表查询

内连接 - INNER JOIN

select * from user u inner join user_score us on u.id=us.user_Id;

左连接 - OUTER JOIN

select * from user u left join user_score us on u.id=us.user_Id;

 - 6.修改数据

update user set age=18 where id=4;

 - 7. 删除数据

DELETE FROM user_score;

 - 8.事务

BEGIN;

COMMIT;
ROLLBACK;

 - 9.索引

CREATE INDEX index_user_id ON user_score(user_Id);

DROP INDEX index_user_id;

 - 10.java中使用


建立链接、关闭连接：

```
//建立链接 url=/Users/liangyuchun/Desktop/test.db
public static synchronized Connection createConnection(String url) {
        Connection c = connectionMap.get(url);
        if (c == null) {
            try {
                c = DriverManager.getConnection("jdbc:sqlite:" + url);
            } catch (Exception e) {
                System.out.println("获取sqlite连接错误：" + e.getMessage());
            }
            connectionMap.put(url, c);
        }
        return c;
    }
	//关闭连接 *
    public static synchronized boolean closeConnection(Connection c) {
        boolean isClose = true;
        try {
            if (c != null && !c.isClosed()) {
                c.isClosed();
                c = null;
            }
        } catch (SQLException e) {
            isClose = false;
            System.out.println("关闭sqlite连接错误：" + e.getMessage());
        }
        return isClose;
    }

```

执行sql：

```
public static List<Map<String, Object>> query(String url, String sql, List<Object> params) {
        if (sql == null || sql.isEmpty()) {
            return null;
        }
        //建立连接
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connection = SqLiteConnector.createConnection(url);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //sql装备 入参
            ps = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            //执行sql
            rs = ps.executeQuery();
            //返回参数装填集合
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int j = 1; j <= columnCount; j++) {
                    map.put(metaData.getColumnLabel(j), rs.getObject(j));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println("sql执行异常 --> " + e.getMessage() + " \n sql=" + sql + "\n params =" + JSON.toJSONString(params));
        } catch (Exception e) {
            System.out.println("未知错误" + e.getMessage());
            SqLiteConnector.closeConnection(connection);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                System.out.println("数据执行关闭异常 --> " + e.getMessage());
            }
        }
        return list;
    }

```








