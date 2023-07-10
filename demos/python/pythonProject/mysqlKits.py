from time import sleep
from unittest import TestCase
import pymysql
from sqlalchemy import create_engine, Column, Integer, String
from sqlalchemy.orm import sessionmaker
from sqlalchemy.ext.declarative import declarative_base

# 创建基类
Base = declarative_base()


# 定义映射类
class Monster(Base):
    __tablename__ = 'monster'
    id = Column(Integer, primary_key=True)
    name = Column(String(255))
    age = Column(Integer)


class MysqlKits:
    @classmethod
    def abc(cls):
        print(1)
        # 建立与 MySQL 数据库的连接
        connection = pymysql.connect(
            host='h131',  # 数据库主机地址
            user='root',  # 用户名
            password='123456',  # 密码
            database='hsp_mybatis',  # 数据库名称
            port=3306  # 数据库端口（默认为3306）
        )
        print(1)
        # 创建游标对象
        cursor = connection.cursor()

        # 执行 SQL 查询或操作
        sql_query = "update monster set age = 15 where age = 10";
        cursor.execute(sql_query)

        # 获取查询结果
        results = cursor.fetchall()

        # 处理查询结果
        for row in results:
            # 根据具体表结构获取相应的字段值
            field1_value = row[0]
            field2_value = row[1]
            print(field2_value)
            # ...

        # 关闭游标和数据库连接
        cursor.close()
        connection.close()

    @classmethod
    def sql_entity(cls):
        # 创建与数据库的连接
        engine = create_engine('mysql+pymysql://root:123456@h131/hsp_mybatis')
        # 创建会话工厂
        Session = sessionmaker(bind=engine)

        # 其他字段...

        # 创建会话对象
        session = Session()

        # 执行查询
        results = session.query(Monster).all()

        # 处理查询结果
        for monster in results:
            # 访问属性获取相应的字段值
            field1_value = monster.id
            field2_value = monster.name
            print('-------')
            print(monster.id)
            print(monster.name)
            # ...

        # 关闭会话
        session.close()

    @classmethod
    def update_one(cls):
        engine = create_engine('mysql+pymysql://root:123456@h131/hsp_mybatis')
        Session = sessionmaker(bind=engine)
        session = Session()
        # 要加锁以确保在更新期间其他用户不能修改相同的记录，可以使用 with_for_update() 方法。
        # 你需要在使用数据库时启用相应的隔离级别，例如 Repeatable Read 或 Serializable 已测试可用
        monster_to_update = session.query(Monster).filter_by(age=11).with_for_update().first()

        # 更新记录
        if monster_to_update:
            print(monster_to_update.name)
            monster_to_update.name = "python"
            monster_to_update.age = 29
            # 继续设置其他需要更新的列
            sleep(50)
            # 提交事务
            session.commit()

            print("记录已更新成功！")
        else:
            print("未找到要更新的记录。")


class TestA(TestCase):
    def testaa(self):
        MysqlKits.abc()

    def test_entity_sql(self):
        MysqlKits.sql_entity()

    def test_update_one(self):
        MysqlKits.update_one()

    def test_update_one2(self):
        MysqlKits.update_one()
