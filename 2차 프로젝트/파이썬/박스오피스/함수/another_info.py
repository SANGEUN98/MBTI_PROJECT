import cx_Oracle
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service as ChromeService
from selenium import webdriver
import time
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
import re
import pandas as pd
from selenium.webdriver import ActionChains
import requests
def get_img_url_and_movie_info():
    conn = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    conn
    cur = conn.cursor()
    my_sql = """
    select * from BOX_MOVIE
    """
    my_sql = """
    SELECT 국문영화명 from BOX_MOVIE
    """
    cur.execute(my_sql)

    column_list = [record[0] for record in cur]
    column_list
    # options = Options()
    options = webdriver.ChromeOptions()
    options.add_experimental_option("detach", True)
    # options.binary_location = r"ADD_YOUR_PATH\chrome.exe"
    # 버전 자동 변경
    # release = "https://chromedriver.storage.googleapis.com/LATEST_RELEASE"
    # version = requests.get(release).text
    # service = Service(ChromeDriverManager(version="version").install())
    # driver = webdriver.Chrome(service=service, options=options)
    # 버전 직접?
    # service = ChromeService(ChromeDriverManager().install())
    # driver = webdriver.Chrome(service=service, options=options)
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service, options=options)
    # 버전 ㄹㅇ 직접 입력
    # service = Service(ChromeDriverManager(version="116.0.5845.96").install())
    # driver = webdriver.Chrome(service=service, options=options)
    driver.maximize_window()

    # for문으로 영화 정보 돌리기
    number = 0
    for number in range(len(column_list)):
        print(number)
        try:
            url = "https://www.naver.com"

            driver.get(url)

            time.sleep(1)

            search_input = driver.find_element(By.CLASS_NAME, "search_input")

            search_input.send_keys('영화 ', column_list[number], ' 정보')
            search_input.submit()
            time.sleep(1)
            soup = BeautifulSoup(driver.page_source, 'html.parser')
            table = soup.find('p', {'class': 'text _content_text'})
            table1 = str(table)
            p = re.compile('<[^>]+>([^<]+)<\/[^>]+>')
            tableRegex = p.findall(table1)
            txt_df = pd.DataFrame({'영화정보': tableRegex})
            print(txt_df)
            txt_df.iloc[0]['영화정보']
            con = cx_Oracle.connect('mbti/1111@localhost:1521/xe')
            cur = con.cursor()
            sql = "update BOX_MOVIE set 영화정보  = :1  WHERE 국문영화명 =:2"
            영화정보 = txt_df.iloc[0]['영화정보']

            print(영화정보)
            print(column_list[number])

            cur.execute(sql, (영화정보, column_list[number]))

            con.commit()
            cur.close()
            con.close()

        except:
            continue
    # for문으로 영화 이미지 url 돌리기
    number = 0
    for number in range(len(column_list)):
        try:
            url = "https://www.naver.com"

            driver.get(url)

            time.sleep(1)

            search_input = driver.find_element(By.CLASS_NAME, "search_input")

            search_input.send_keys('영화 ', column_list[number])
            search_input.submit()
            time.sleep(1)
            soup = BeautifulSoup(driver.page_source, 'html.parser')
            table = soup.find('img', {'class': '_img'})
            table1 = str(table)
            p = re.compile('\S+://\S+')
            tableRegex = p.findall(table1)
            image_df = pd.DataFrame({'URL': tableRegex})
            image_df.iloc[0]['URL']
            image_df.URL = image_df.URL.str.replace('src="', '')
            image_df.URL = image_df.URL.str.replace('"', '')
            image_df.iloc[0]['URL']
            con = cx_Oracle.connect('mbti/1111@localhost:1521/xe')
            cur = con.cursor()
            sql = "update BOX_MOVIE set URL  = :1  WHERE 국문영화명 =:2"
            URL = image_df.iloc[0]['URL']

            cur.execute(sql, (URL, column_list[number]))

            con.commit()
            cur.close()
            con.close()

        except:
            continue


def booking_link():
    conn = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    conn
    cur = conn.cursor()
    my_sql = """
    SELECT 국문영화명 from box_movie
    """
    cur.execute(my_sql)

    column_list = [record[0] for record in cur]
    column_list
    # options = Options()
    options = webdriver.ChromeOptions()
    options.add_experimental_option("detach", True)
    # options.binary_location = r"ADD_YOUR_PATH\chrome.exe"
    # 버전 자동 변경
    # release = "https://chromedriver.storage.googleapis.com/LATEST_RELEASE"
    # version = requests.get(release).text
    # service = Service(ChromeDriverManager(version="version").install())
    # driver = webdriver.Chrome(service=service, options=options)
    # 버전 직접?
    # service = ChromeService(ChromeDriverManager().install())
    # driver = webdriver.Chrome(service=service, options=options)
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service, options=options)
    # 버전 ㄹㅇ 직접 입력
    # service = Service(ChromeDriverManager(version="116.0.5845.96").install())
    # driver = webdriver.Chrome(service=service, options=options)
    driver.maximize_window()
    # for문으로 돌리기
    number = 0
    for number in range(len(column_list)):
        try:
            url = "https://www.cgv.co.kr"
            driver.get(url)
            time.sleep(2)
            search_input = driver.find_element(By.ID, "header_keyword")
            # 검색 입력 필드를 클릭하여 활성화합니다.
            ActionChains(driver).move_to_element(search_input).click().perform()
            search_input.send_keys(column_list[number])
            search_button = driver.find_element(By.ID, "btn_header_search")
            search_button.click()
            time.sleep(2)
            soup = BeautifulSoup(driver.page_source, 'html.parser')
            table = soup.find('a', {'class': 'btn_style1'})
            table1 = str(table)
            p = re.compile('href="([^"]*)"')
            tableRegex = p.findall(table1)
            tableRegex = ['http://www.cgv.co.kr' + tableRegex[0]]
            txt_df = pd.DataFrame({'예매URL': tableRegex})
            txt_df.예매URL = txt_df.예매URL.str.replace('amp;', '')
            txt_df.iloc[0]['예매URL']
            con = cx_Oracle.connect('mbti/1111@localhost:1521/xe')
            cur = con.cursor()
            sql = "update box_movie set 예매URL  = :1  WHERE 국문영화명 =:2"
            예매URL = txt_df.iloc[0]['예매URL']

            cur.execute(sql, (예매URL, column_list[number]))

            con.commit()
            cur.close()
            con.close()

        except:
            continue