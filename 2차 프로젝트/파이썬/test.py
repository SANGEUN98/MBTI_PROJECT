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
options = Options()
options.add_experimental_option("detach", True)
service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)
driver.maximize_window()




# for문으로 돌리기
number = 0
for number in range(len(column_list)) :
    url = "https://www.naver.com"

    driver.get(url)

    time.sleep(1)
    search_input = driver.find_element(By.CLASS_NAME, "search_input")
    search_input.send_keys('영화 ', column_list[number])
    search_input.submit()
    time.sleep(1)
    soup=BeautifulSoup(driver.page_source,'html.parser')
    soup
    table = soup.find('img', {'class' : '_img'})
    table
    table1 = str(table)
    table1
    p = re.compile('\S+://\S+')
    tableRegex = p.findall(table1)
    image_df = pd.DataFrame({'URL':tableRegex})
    image_df.iloc[0]['URL']
    image_df.URL = image_df.URL.str.replace('src="', '')
    image_df.URL = image_df.URL.str.replace('"', '')
    image_df.iloc[0]['URL']
    con = cx_Oracle.connect('mbti/1111@localhost:1521/xe')
    cur = con.cursor()
    sql = "update movie set URL  = :1  WHERE 국문영화명 =:2"
    URL = image_df.iloc[0]['URL']

    cur.execute(sql,(URL,column_list[number]))



    con.commit()
    cur.close()
    con.close()