/**
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package mytest.jdbc;

import java.sql.*;

/**
 * @date 2020/9/26 5:24 下午
 */
public class JdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_test?serverTimezone=Asia/Shanghai",
                "root", "123456");
        PreparedStatement statement = connection.prepareStatement("select * from user where id = ?");
        statement.setLong(1, 5L);
        boolean execute = statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String userDesc = resultSet.getString(3);
            System.out.println("id:" + id + ",name:"+ name+ ",userDesc:" + userDesc);
        }
    }
}
