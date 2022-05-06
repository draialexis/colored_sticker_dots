package com.uca.dao;

import com.uca.entity.StudentEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO extends _Generic<StudentEntity>
{
    @Override
    protected StudentEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        StudentEntity entity = new StudentEntity();
        entity.setId(resultSet.getLong("id_student"));
        entity.setFirstName(resultSet.getString("firstname"));
        entity.setLastName(resultSet.getString("lastname"));
        return entity;
    }

    @Override
    public StudentEntity create(StudentEntity obj)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Student(id, lastName, firstName) VALUES(?, ?, ?);");
            statement.setLong(1, obj.getId());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getFirstName());
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public ArrayList<StudentEntity> readAll()
    {
        ArrayList<StudentEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Student ORDER BY id_student;");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                entities.add(getFullEntity(resultSet));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public StudentEntity readById(long id)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Student WHERE id_student = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return getFullEntity(resultSet);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null; //TODO decide if we can let this return null... probably not
    }

    @Override
    public StudentEntity update(StudentEntity obj, long id)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE Sticker SET lastname = ?, firstname = ? WHERE id_student = ?;");
            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setLong(3, id);
            statement.executeUpdate();

            ensureIdIsSet(obj, id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(StudentEntity obj)
    {
        this.deleteById(obj.getId());
        //Todo if we want to use this then we have to change deleteByID to private ?
        // --> no, but maybe we should get rid of this delete() function (if we don't use it later)
    }

    @Override
    public void deleteById(long id)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM Student WHERE id_student = ? ;");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
