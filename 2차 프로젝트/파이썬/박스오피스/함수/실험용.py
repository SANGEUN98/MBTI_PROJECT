import requests
import json
import pandas as pd
from datetime import datetime, timedelta
import cx_Oracle

access_key ='ba93000485f503f718ba1b8973bbf818'

def get_rank_request_url(url,yyyymmdd,hhmm):
    params = {'key': access_key, 'targetDt':yyyymmdd, 'itemPerPage': 10,
              'dataType': 'JSON'
              }
    print("get_rank 이상무")
    response = requests.get(url, params=params)
    # response.content # => response.content는 한글이 인코딩된 형식이므로
    #                       response.text 를 응답받기로함
    return response.text

def abc_rank_cut(raw_json):
    data=[]
    num=0
    column_list = ["rank","movieNm"]

    for record in raw_json['boxOfficeResult']['dailyBoxOfficeList']:
        # 이하 블럭을 자동화 해보세요.
        row_data = []
        for column_data in column_list:
            row_data.append(record.get(column_data))
        data.append(row_data)
    return data

def movie_rank_collector():
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    sql_delete = '''
                       DELETE FROM BOX_RANK_DATA
                       '''
    cur.execute(sql_delete)
    con.commit()
    cur.close()
    print('전날 데이터 삭제 완료')
    for rank_date in range(7):
        url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=ba93000485f503f718ba1b8973bbf818'
        request_rank_time = get_rank_update_time_info(rank_date)
        yyyymmdd = request_rank_time.strftime("%Y%m%d")
        print("movie_rank 이상무")
        hhmm = request_rank_time.strftime("%H%M")

        raw_str_json = get_rank_request_url(url,yyyymmdd,hhmm)
        print("raw_str 이상무")

        if raw_str_json:
            raw_json = json.loads(raw_str_json)
        data2 = []
        data2 = abc_rank_cut(raw_json)

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
        preprocessed_df_to_oracle_ver_rank(df3, yyyymmdd)

def get_rank_update_time_info(rank_date):
    now = datetime.now()

    if now.hour > 00:
        print(f"어제의 박스오피스 데이터를 업데이트 합니다. 오늘의 날짜: {now}")
        return now - timedelta(days=(1+rank_date))


def preprocessed_df_to_oracle_ver_rank(df, yyyymmdd):
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    sql_insert = '''
                    insert into BOX_RANK_DATA(RANK_DATE, MOVIENM, RANK) 
                    values(:yyyymmdd, :movieNm, :rank)
                    '''
    print("sql_insert 이상무")
    for n in range(0, len(df)):
        movieNm = df.iloc[n]['movieNm']
        rank = int(df.iloc[n]['rank'])
        print("암튼 여기도 이상무")

        cur.execute(sql_insert,
                    ( yyyymmdd, movieNm, rank)
                    )
        print("execute 이상무")

    con.commit()
    cur.close()
    con.close()
    print('오라클 저장 완료')

movie_rank_collector()