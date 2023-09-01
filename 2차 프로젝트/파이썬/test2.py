from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time
from bs4 import BeautifulSoup
from selenium.webdriver import ActionChains
import cx_Oracle
import re
import pandas as pd




conn = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
conn
cur = conn.cursor()
my_sql="""
select * from movie
"""
my_sql="""
SELECT 국문영화명 from movie
"""
cur.execute(my_sql)

column_list = [record[0] for record in cur]
column_list

# for문으로 돌리기
number = 0
for number in range(len(column_list)) :
        con = cx_Oracle.connect('mbti/1111@localhost:1521/xe')
        cur = con.cursor()
        sql = "update movie set IDX  = :1  WHERE 국문영화명 =:2"

        # cur.execute(sql,(number,column_list[number]))
        con.commit()
        cur.close()
        con.close()