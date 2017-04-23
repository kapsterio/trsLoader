# trsLoader
TrsLoader is a small tool that can load the .trs files to mysql database, I wrote it for a friend who was working on his PHD thesis about patent analysis, and I think it may be also useful for those people not major in CS but facing the similar problem.

# build
You need to build trsloader with [maven](https://maven.apache.org/),  just type following command: ` mvn clean package`, and you will get a excutable jar in ./target directory

# how to use
Your should have java8+ and mysql properly installed, and cd to the directory where the excutable jar file (trsLoader-0.0.1-SNAPSHOT.jar) you place, then just type following command: 

`java -jar trsLoader-0.0.1-SNAPSHOT.jar --spring.datasource.username=your_mysql_username --spring.datasource.password=your_mysql_password --spring.datasource.url=your_database_url /path/to/your/trs/file /path/to/your/trs/file ... /path/to/your/trs/file`

`--spring.datasource.username=your_mysql_username` tells the program your mysql username, if you don't specify it, 'root' will be used as default value.

`--spring.datasource.password=your_mysql_password` tells the program your mysql password, if haven't set password for your mysql account, you don't need to specify this argument.

`--spring.datasource.url=your_database_url` tells the program your mysql database url, the default value for this arguemnt is 'jdbc:mysql://127.0.0.1:3306/mydb', means mysql server was provided locally on port 3306 and the used database name was 'mydb'. If have a different configuration, you need to sepecify it properly.

You can provide multiple trs file to trsloader in one shot, and let it import all the data in your files to mysql.

Note that you don't need to create a patent table, trsLoader do that for you automatically, if you already have a table names 'patent', please drop it first.

# Implementation
TrsLoader is a multi-thread application based on springboot and mybatis, the main thread is responsible for parse the trs file, and create batch insert tasks, then submit them to a thread pool that save the data to mysql, so it is pretty fast.





