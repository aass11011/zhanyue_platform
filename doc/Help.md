## PostgreSQL
数据导出

pg_dump -h localhost -p 5432 -U postgres -d platform_admin --clean --if-exists -f 存放路径\platform_admin_posgresql.sql
docker exec -t postgres pg_dump -U postgres -d platform_admin > /opt/project/backup/backup.sql
数据导入：
psql.exe -U postgres platform_admin -f "E:\dev\fast_platform\sql\platform_admin_posgresql.sql"

二级制导出
docker exec postgres pg_dump -U postgres -d platform_admin -F c -f /tmp/backup.dump
docker cp postgres:/tmp/backup.dump /opt/project/backup/backup.dump
对应的导入
chcp 65001
set PGCLIENTENCODING=UTF8
set PGPASSWORD=密码
pg_restore -U postgres -d platform_admin --clean backup.dump

主键自增语句
```sql
CREATE SEQUENCE 表名_id_seq START WITH 1;
ALTER TABLE 表名
    ALTER COLUMN id SET DEFAULT nextval('表名_id_seq');
```
创建表语句

```sql
CREATE SEQUENCE 表名_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS 表名 (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('表名_id_seq'::regclass),
    group_id BIGINT,
    stock_code VARCHAR(20),
    remark VARCHAR(255),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_time TIMESTAMP,
    updated_by VARCHAR(255)
);

COMMENT ON TABLE 表名 IS '股票收藏项表';
COMMENT ON COLUMN 表名.id IS '收藏项ID';
COMMENT ON COLUMN 表名.group_id IS '分组ID';
COMMENT ON COLUMN 表名.stock_code IS '股票代码';
COMMENT ON COLUMN 表名.remark IS '备注';
COMMENT ON COLUMN 表名.created_time IS '收藏时间';

```
修改主键起始值
```sql
ALTER SEQUENCE 表名_id_seq RESTART WITH 起始值;
或者
SELECT setval('public.表名_id_seq', 起始值);
```
查看序列当前值
SELECT last_value FROM 表名_id_seq;
```sql
SELECT currval('public.表名_id_seq');
```
修改序列（Sequence）名称
```sql
ALTER SEQUENCE 原序列名 RENAME TO 新序列名;
```