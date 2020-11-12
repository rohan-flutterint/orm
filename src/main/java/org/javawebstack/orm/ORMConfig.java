package org.javawebstack.orm;

import org.javawebstack.orm.mapper.DefaultMapper;
import org.javawebstack.orm.mapper.TypeMapper;

import java.util.ArrayList;
import java.util.List;

public class ORMConfig {
    private String tablePrefix = "";
    private boolean camelToSnakeCase = true;
    private int defaultSize = 0;
    private boolean idPrimaryKey = true;
    private final List<TypeMapper> typeMappers = new ArrayList<>();
    public ORMConfig(){
        typeMappers.add(new DefaultMapper());
    }
    public ORMConfig setTablePrefix(String tablePrefix){
        this.tablePrefix = tablePrefix;
        return this;
    }
    public ORMConfig setCamelToSnakeCase(boolean camelToSnakeCase){
        this.camelToSnakeCase = camelToSnakeCase;
        return this;
    }
    public ORMConfig setDefaultSize(int defaultSize){
        this.defaultSize = defaultSize;
        return this;
    }
    public ORMConfig addTypeMapper(TypeMapper typeMapper){
        typeMappers.add(typeMapper);
        return this;
    }
    public ORMConfig setIdPrimaryKey(boolean idPrimaryKey){
        this.idPrimaryKey = idPrimaryKey;
        return this;
    }
    public boolean isCamelToSnakeCase() {
        return camelToSnakeCase;
    }
    public String getTablePrefix() {
        return tablePrefix;
    }
    public int getDefaultSize(){
        return defaultSize;
    }
    public List<TypeMapper> getTypeMappers(){
        return typeMappers;
    }
    public boolean isIdPrimaryKey() {
        return idPrimaryKey;
    }
    public TypeMapper getTypeMapper(Class<?> type, int size){
        for(TypeMapper mapper : getTypeMappers()){
            SQLType sqlType = mapper.getType(type, size);
            if(sqlType != null)
                return mapper;
        }
        return null;
    }
    public SQLType getType(Class<?> type, int size){
        for(TypeMapper mapper : getTypeMappers()){
            SQLType sqlType = mapper.getType(type, size);
            if(sqlType != null)
                return sqlType;
        }
        return SQLType.TEXT;
    }
    public String getTypeParameters(Class<?> type, int size){
        for(TypeMapper mapper : getTypeMappers()){
            SQLType sqlType = mapper.getType(type, size);
            if(sqlType != null)
                return mapper.getTypeParameters(type, size);
        }
        return null;
    }
}
