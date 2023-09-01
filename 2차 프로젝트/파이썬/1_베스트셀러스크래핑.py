import threading
import time
import requests
import json
from datetime import datetime, timedelta

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

from bs4 import BeautifulSoup
import pandas as pd
import cx_Oracle
import re

print('< 책 베스트 셀러 데이터 수집기 ver1.0>')


def get_request_url(url,yyyymmdd,hhmm):
    params = {'targetDt':yyyymmdd, 'itemPerPage': 10}
    response = requests.get(url, params=params)
    # response.content # => response.content는 한글이 인코딩된 형식이므로
    #                       response.text 를 응답받기로함
    return response.text

def get_update_time_info():
    now = datetime.now()

    if now.hour > 00:
        print(f"어제의 네이버 데이터를 업데이트 합니다. 오늘의 날짜: {now}")
        return now - timedelta(days=1)

def abc_cut(raw_json):
    data=[]
    print(raw_json)
    num=0
    columns_list=["title","author","company","price","img","link"]

    for record in raw_json['bestResult']['bestList']:
        # 이하 블럭을 자동화 해보세요.
        row_data = []
        for column_data in columns_list:
            row_data.append(record.get(column_data))
        data.append(row_data)


    return data

def best():
    options = Options()
    options.add_experimental_option("detach", True)
    service = Service(ChromeDriverManager(version="latest").install())

    driver = webdriver.Chrome(service=service, options=options)

    url = "https://search.shopping.naver.com/book/search?bookTabType=BEST_SELLER&catId=50005542&pageIndex=1&pageSize=40&query=%EB%B2%A0%EC%8A%A4%ED%8A%B8%EC%85%80%EB%9F%AC&sort=REL"

    driver.get(url)



    soup = BeautifulSoup(driver.page_source, 'html.parser')
    soup1 = soup.find_all('ul', {"class": "list_book"})
    soup2 = soup1[0].find_all('li')

    chart_dates=[]
    for i in range(len(soup2)) :
        title = soup2[i].find_all('span')[3].get_text()
        author = soup2[i].find_all('span')[5].get_text()
        company = soup2[i].find_all('span')[7].get_text()
        price = soup2[i].find_all('em')[0].get_text()
        img = soup2[i].find_all('img')[0].attrs['src']
        link = soup2[i].find_all('a')[0].attrs['href']

        # best_date = []
        best_date = title,author, company, price, img, link

        chart_dates.append(best_date)
    print(chart_dates)

    columns_list=["title","author","company","price","img","link"]

    df_book_best_date = pd.DataFrame(columns=columns_list, data=chart_dates)

    return  best_df_to_oracle(df_book_best_date)

def best_df_to_oracle(df):
    con = cx_Oracle.connect('mbti/1111@192.168.30.28/xe')
    con
    cur = con.cursor()
    sql_delete = '''
                            DELETE FROM best_book
                            '''
    cur.execute(sql_delete)
    print('전날 데이터 삭제 완료')
    sql_insert = '''
                                       insert into best_book(제목, 작가, 출판사, 가격, 사진, 링크)
                                       values(:title,:author,:company, :price, :img, :link)
                                       '''
    for i in range(len(df)):
        title = df.iloc[i]['title']
        author = df.iloc[i]['author']
        company = df.iloc[i]['company']
        price = df.iloc[i]['price']
        img = df.iloc[i]['img']
        link = df.iloc[i]['link']

        cur.execute(sql_insert,
                    (title,author, company, price, img, link)
                    )
    con.commit()
    cur.close()
    con.close()

def book_info_collector():
    url = "https://search.shopping.naver.com/book/search?bookTabType=BEST_SELLER&catId=50005542&pageIndex=1&pageSize=40&query=%EB%B2%A0%EC%8A%A4%ED%8A%B8%EC%85%80%EB%9F%AC&sort=REL"
    request_time = get_update_time_info()
    yyyymmdd = request_time.strftime("%Y%m%d")
    hhmm = request_time.strftime("%H%M")

    raw_str_json = get_request_url(url, yyyymmdd, hhmm)

    if raw_str_json:
        raw_json = json.loads(raw_str_json)
    data2 = []
    data2 = abc_cut(raw_json)

    columns_list=["title","author","company","price","img","link"]

    df = pd.DataFrame(data2[0])
    df3 = pd.DataFrame(data2)
    print(df3)

    num = 0
    for l in columns_list:
        # print(l)
        df3.rename(columns={num: l}, inplace=True)
        num += 1

    # print(df2['so2Grade'])

    # df3=df2.set_index(column_list)
    # df = pd.DataFrame(data2, columns=column_list)
    print(df3)
    best_df_to_oracle(df3)
    best()


def book_info_scheduler():
    print('책 일일 네이버 데이터 수집기 스케줄러 동작.\n')
    while True:
        book_info_collector()
        print("수집완료.")
        time.sleep(86400) # 24시간 주기로 데이터 수집

def print_main_menu():
    print('\n1. 네이버(교보문구) 데이터 실시간 데이터 구축')
    print('2. 스케줄러 종료')
    print('* 엔터: 메뉴 업데이트\n')

while True:
    print_main_menu()
    print('아래행에 메뉴입력: ')
    selection = input()
    if selection == '':  continue
    else:                menu_num = int(selection)

    if(menu_num == 1):
        t = threading.Thread(target=book_info_scheduler, daemon=True)
        t.start()
    elif(menu_num == 2):
        break
    elif (menu_num == 0):
        continue
