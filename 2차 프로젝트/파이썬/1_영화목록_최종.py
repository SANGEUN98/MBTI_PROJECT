import threading
import time
from datetime import datetime, timedelta
import pandas as pd
import cx_Oracle
import requests
import json
# access_key = 'HVgh0GJtfha0bssIG8/oBb7dkuTWWgOnt3o47r4Wa1/SrD6VRDqJ0cOzT/6T4vL3KX4JV0bKzNZl9WqYpOdLJg=='
access_key ='ba93000485f503f718ba1b8973bbf818'
def get_request_url():
    print(2)
    url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=ba93000485f503f718ba1b8973bbf818'
    params = {'key': access_key, 'curPage': 20, 'itemPerPage': 100,
              'dataType': 'JSON', 'openStartDt': '2001', 'openEndDt': '2023'
              }
    print(3)
    response = requests.get(url, params=params)
    return response.text

def abc_cut(j):
    data=[]
    num=0
    column_list = ["movieCd","movieNm","prdtYear","openDt","prdtStatNm","nationAlt","genreAlt","repNationNm","repGenreNm"]


    for record in raw_json['movieListResult']['movieList']:
        # 이하 블럭을 자동화 해보세요.
        row_data = []
        for column_data in column_list:
            row_data.append(record.get(column_data))
        data.append(row_data)


    return data

def preprocessed_df_to_oracle(df):
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    sql_insert = '''
                    insert into movie(영화코드, 국문영화명, 제작연도, 개봉일, 제작상태, 제작국가, 영화장르,대표제작국가명,대표장르명) 
                    values(:movieCd, :movieNm, :prdtYear, :openDt, :prdtStatNm, :nationAlt, :genreAlt,:repNationNm,:repGenreNm)
                    '''
    for n in range(0, len(df)):
        movieCd = df.iloc[n]['movieCd']
        movieNm = df.iloc[n]['movieNm']  # int 값에 대해서는 int 형으로 변환해줘야 한다.
        # movieNmEn = df.iloc[0]['movieNmEn']
        prdtYear = df.iloc[n]['prdtYear']  # 현재 데이터 프레임의 행인덱스가 date_time이므로 loc가 안된다.
        openDt = df.iloc[n]['openDt']
        prdtStatNm = df.iloc[n]['prdtStatNm']
        nationAlt = df.iloc[n]['nationAlt']
        genreAlt = df.iloc[n]['genreAlt']
        repNationNm = df.iloc[n]['repNationNm']
        repGenreNm = df.iloc[n]['repGenreNm']
        # directors = df.iloc[0]['directors']
        # companys = df.iloc[0]['companys']

        cur.execute(sql_insert,
                    (movieCd, movieNm,  prdtYear, openDt, prdtStatNm,
                     nationAlt, genreAlt, repNationNm, repGenreNm)
                    )

    con.commit()
    cur.close()
    con.close()

raw_str_json = get_request_url()
# print(raw_str_json)
# print('디버그용')

if raw_str_json:
    raw_json = json.loads(raw_str_json)

data2=[]
data2=abc_cut(raw_json)

column_list = ["movieCd", "movieNm",  "prdtYear", "openDt", "prdtStatNm", "nationAlt", "genreAlt",
               "repNationNm", "repGenreNm"]

df = pd.DataFrame(data2[0])
df3= pd.DataFrame(data2)
print(df3)
# for p in range(1,len(data2)+1):
#     df.append(data2[p])




# print(data2)
# df2=df3.transpose()
num=0
for l in column_list:
    # print(l)
    df3.rename(columns={num:l} , inplace=True)
    num+=1

# print(df2['so2Grade'])

# df3=df2.set_index(column_list)
# df = pd.DataFrame(data2, columns=column_list)
print(df3)
preprocessed_df_to_oracle(df3)
# print()
#



















