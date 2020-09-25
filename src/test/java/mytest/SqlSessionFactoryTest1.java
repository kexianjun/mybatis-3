/**
 *    Copyright 2009-2020 the original author or authors.
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
package mytest;

import mytest.mapper.UserMapper;
import mytest.model.User;
import mytest.model.UserDesc;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class SqlSessionFactoryTest1 {
    public static void main(String[] args) throws IOException {
        String resource = "resources/mytest/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            User user = new User();
            user.setName("hello");
            UserDesc userDesc = new UserDesc();
            userDesc.setAddress("杭州");
            userDesc.setPhone("123456789");
            user.setUserDesc(userDesc);
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           /* mapper.insert(user);
            sqlSession.commit();
            List<User> userList = mapper.selectByName("hello");
            Optional.ofNullable(userList)
                    .ifPresent(list -> list.forEach(u -> System.out.println("user :" + u)));*/
            User hello = mapper.selectByNameAndId(1L, "hello");
            System.out.println(hello);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
