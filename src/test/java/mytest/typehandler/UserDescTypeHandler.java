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
package mytest.typehandler;

import com.alibaba.fastjson.JSON;
import mytest.model.UserDesc;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDescTypeHandler extends BaseTypeHandler<UserDesc> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserDesc parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public UserDesc getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String rsString = rs.getString(columnName);
        if (StringUtils.isBlank(rsString)) {
            return null;
        }
        return JSON.parseObject(rsString, UserDesc.class);
    }

    @Override
    public UserDesc getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return JSON.parseObject(string, UserDesc.class);
    }

    @Override
    public UserDesc getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        if (StringUtils.isBlank(string)) {
            return null;
        }
        return JSON.parseObject(string, UserDesc.class);
    }
}
