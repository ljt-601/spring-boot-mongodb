package com.itheima.springbootmongodb.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.List;

@Component
public class PersonDao {


    @Autowired  //导入操作mongoDB的bean
    private MongoTemplate mongoTemplate;

    //添加信息
    public void savePerson(Person person){
        this.mongoTemplate.save(person);
    }
    //通过名字查询
    public List<Person> queryPersonListByName(String name){
        Query query = Query.query(Criteria.where("name").is(name));
        return this.mongoTemplate.find(query,Person.class);
    }
    //分页查询   //mongoDB的分页是从0开始算的
    public List<Person> queryPersonListByName(Integer page,Integer rows){
        Query query = new Query().limit(rows).skip((page - 1) * rows);
        return this.mongoTemplate.find(query,Person.class);
    }

    //更新数据
    public UpdateResult update(Person person){
        Query query = Query.query(Criteria.where("id").is(person.getId()));
        Update update = Update.update("age", person.getAge());
        return this.mongoTemplate.updateFirst(query,update,Person.class);
    }

    //删除数据
    public DeleteResult deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, Person.class);
    }
}



