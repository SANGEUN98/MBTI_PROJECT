import threading
import time
import requests
import json
from datetime import datetime, timedelta

import pandas as pd
import cx_Oracle
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup
from selenium.webdriver import ActionChains
import re


def box_data() :
    print("ggg")
    access_key ='ba93000485f503f718ba1b8973bbf818'

    print('< 영화 박스오피스 데이터 수집기 ver1.0>')

    # ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆일일 박스오피스 데이터 수집☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
    def get_request_url(url,yyyymmdd,hhmm):
        params = {'key': access_key, 'targetDt':yyyymmdd, 'itemPerPage': 10,
                  'dataType': 'JSON'
                  }
        response = requests.get(url, params=params)
        # response.content # => response.content는 한글이 인코딩된 형식이므로
        #                       response.text 를 응답받기로함
        return response.text

    def get_update_time_info():
        now = datetime.now()

        if now.hour > 00:
            print(f"어제의 박스오피스 데이터를 업데이트 합니다. 오늘의 날짜: {now}")
            return now - timedelta(days=1)

    def abc_cut(raw_json):
        data=[]
        num=0
        column_list = ["rank","rankInten","rankOldAndNew","movieCd","movieNm","openDt","audiCnt","audiInten","audiAcc"]

        for record in raw_json['boxOfficeResult']['dailyBoxOfficeList']:
            # 이하 블럭을 자동화 해보세요.
            row_data = []
            for column_data in column_list:
                row_data.append(record.get(column_data))
            data.append(row_data)
        return data

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
        options = Options()
        options.add_experimental_option("detach", True)
        service = Service(ChromeDriverManager(version="114.0.5735.90").install())
        driver = webdriver.Chrome(service=service, options=options)
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
        options = Options()
        options.add_experimental_option("detach", True)
        service = Service(ChromeDriverManager(version="114.0.5735.90").install())
        driver = webdriver.Chrome(service=service, options=options)
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

    def preprocessed_df_to_oracle(df):
        con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
        cur = con.cursor()
        sql_delete ='''
                        DELETE FROM BOX_MOVIE
                        '''
        cur.execute(sql_delete)
        print('전날 데이터 삭제 완료')
        sql_insert = '''
                        insert into BOX_MOVIE(당일영화순위, 전일대비순위증감분, 랭킹신규진입여부, 영화대표코드, 국문영화명, 영화개봉일, 당일관객수,전일대비관객수증감,누적관객수) 
                        values(:rank, :rankInten, :rankOldAndNew, :movieCd, :movieNm, :openDt, :audiCnt,:audiInten,:audiAcc)
                        '''
        for n in range(0, len(df)):
            rank = int(df.iloc[n]['rank'])
            rankInten = int(df.iloc[n]['rankInten'])  # int 값에 대해서는 int 형으로 변환해줘야 한다.
            # movieNmEn = df.iloc[0]['movieNmEn']
            rankOldAndNew = df.iloc[n]['rankOldAndNew']  # 현재 데이터 프레임의 행인덱스가 date_time이므로 loc가 안된다.
            movieCd = df.iloc[n]['movieCd']
            movieNm = df.iloc[n]['movieNm']
            openDt = df.iloc[n]['openDt']
            audiCnt = int(df.iloc[n]['audiCnt'])
            audiInten = int(df.iloc[n]['audiInten'])
            audiAcc = int(df.iloc[n]['audiAcc'])
            # directors = df.iloc[0]['directors']
            # companys = df.iloc[0]['companys']

            cur.execute(sql_insert,
                        (rank, rankInten,  rankOldAndNew, movieCd, movieNm,
                         openDt, audiCnt, audiInten, audiAcc)
                        )

        con.commit()
        cur.close()
        con.close()
        print('오라클 저장 완료')

    def movie_info_collector():
        url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=ba93000485f503f718ba1b8973bbf818'
        request_time = get_update_time_info()
        yyyymmdd = request_time.strftime("%Y%m%d")
        hhmm = request_time.strftime("%H%M")


        raw_str_json = get_request_url(url,yyyymmdd,hhmm)

        if raw_str_json:
            raw_json = json.loads(raw_str_json)
        data2 = []
        data2 = abc_cut(raw_json)

        column_list = ["rank","rankInten","rankOldAndNew","movieCd","movieNm","openDt","audiCnt","audiInten","audiAcc"]


        df = pd.DataFrame(data2[0])
        df3 = pd.DataFrame(data2)
        print(df3)

        num = 0
        for l in column_list:
            # print(l)
            df3.rename(columns={num: l}, inplace=True)
            num += 1
        print(df3)
        preprocessed_df_to_oracle(df3)
        get_img_url_and_movie_info()
        booking_link()


    # ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆일일 박스오피스 랭크 데이터 수집☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
    def movie_rank_collector():
        url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=ba93000485f503f718ba1b8973bbf818'
        request_rank_time = get_rank_update_time_info()
        yyyymmdd = request_rank_time.strftime("%Y%m%d")
        hhmm = request_rank_time.strftime("%H%M")

        raw_str_json = get_rank_request_url(url,yyyymmdd,hhmm)

        if raw_str_json:
            raw_json = json.loads(raw_str_json)
        data2 = []
        data2 = abc_cut(raw_json)

        column_list = ["rank","movieNm"]

        df = pd.DataFrame(data2[0])
        df3 = pd.DataFrame(data2)
        print(df3)

        num = 0
        for l in column_list:
            # print(l)
            df3.rename(columns={num: l}, inplace=True)
            num += 1
        print(df3)
        preprocessed_df_to_oracle(df3)

    def get_rank_update_time_info():
        now = datetime.now()

        if now.hour > 00:
            print(f"어제의 박스오피스 데이터를 업데이트 합니다. 오늘의 날짜: {now}")
            return now - timedelta(days=1)

    def get_rank_request_url(url,yyyymmdd,hhmm):
        params = {'key': access_key, 'targetDt':yyyymmdd, 'itemPerPage': 10,
                  'dataType': 'JSON'
                  }
        response = requests.get(url, params=params)
        # response.content # => response.content는 한글이 인코딩된 형식이므로
        #                       response.text 를 응답받기로함
        return response.text

    def movie_info_scheduler():
        print('영화 일일 박스오피스 데이터 수집기 스케줄러 동작.\n')
        while True:
            print("일일 박스오피스 데이터 수집시작.")
            movie_info_collector()
            print("일일 박스오피스 데이터 수집완료.")
            print("일일 박스오피스 랭크 데이터 수집시작.")
            movie_rank_collector()
            print("일일 박스오피스 랭크 데이터 수집완료.")
            time.sleep(86400) # 24시간 주기로 데이터 수집

    def print_main_menu():
        print('\n1. 박스오피스 데이터 실시간 데이터 구축')
        print('2. 스케줄러 종료')
        print('* 엔터: 메뉴 업데이트\n')

    while True:
        print_main_menu()
        print('아래행에 메뉴입력: ')
        selection = input()
        if selection == '':  continue
        else:                menu_num = int(selection)

        if(menu_num == 1):
            t = threading.Thread(target=movie_info_scheduler, daemon=True)
            t.start()
        elif(menu_num == 2):
            break
        elif (menu_num == 0):
            continue

